package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.domain.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileService {
    boolean save(Board board, int fileNumber, MultipartFile file) throws Exception;
    Optional<File> findOne(Long boardId, int number);
    //List<Board> findByBoardId(Long boardId);
}
