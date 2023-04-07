package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
import capstone.ApplePie_Spring.Board.dto.FileDto;
import capstone.ApplePie_Spring.Board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;
    public final FileRepository fileRepository;

    private static final Integer STATUS = 1;

    @Override
    public File save(Board board, int fileNumber, MultipartFile multipartFile) {
        try {
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

            String name = FilenameUtils.getBaseName(multipartFile.getName())
                    + board.getId() + '-' + fileNumber; // 중복 이름 방지
            java.io.File saveFile = new java.io.File(uploadPath + name + '.' + extension);
            String url = saveFile.getPath();
            //System.out.println("saveFile.getPath() = " + saveFile.getPath());

            multipartFile.transferTo(saveFile);
            long size = Files.size(Path.of(saveFile.getPath()));

            FileDto fileDto = FileDto.builder()
                    .name(name)
                    .size(size)
                    .extension(extension)
                    .number(fileNumber)
                    .url(url)
                    .build();
            return fileRepository.save(fileDto.toFile(board));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<File> findOne(Long boardId, int number) {
        return fileRepository.findByBoardIdAndNumberAndStatus(boardId, number, STATUS);
    }


    @Override
    public List<File> findByBoardId(Long boardId) {
        return fileRepository.findAllByBoardIdAndStatus(boardId, STATUS);
    }
}
