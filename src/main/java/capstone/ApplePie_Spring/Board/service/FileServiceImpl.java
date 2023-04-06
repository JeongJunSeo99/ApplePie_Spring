package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.dto.FileDto;
import capstone.ApplePie_Spring.Board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;
    public final FileRepository FileRepository;

    public boolean save(Board board, long fileNumber, MultipartFile multipartFile) throws IOException {
        try {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            String name = FilenameUtils.getBaseName(multipartFile.getName())
                    + board.getId() + '-' + fileNumber; // 중복 이름 방지
            java.io.File saveFile = new java.io.File(uploadPath + name + '.' + extension);
            //System.out.println("saveFile.getPath() = " + saveFile.getPath());

            multipartFile.transferTo(saveFile);
            long size = Files.size(Path.of(saveFile.getPath()));

            FileDto fileDto = FileDto.builder()
                    .name(name)
                    .size(size)
                    .extension(extension)
                    .build();
            FileRepository.save(fileDto.toFile(board));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
