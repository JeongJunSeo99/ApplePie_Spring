package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    boolean save(Board board, long fileNumber, MultipartFile file) throws Exception;
    //List<Board> findByBoardId(Long boardId);
}
