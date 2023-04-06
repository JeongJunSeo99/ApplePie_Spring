package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    Object save(BoardSaveDto boardSaveDto, List<MultipartFile> files) throws Exception;
    List<Board> findAllByBoard();
}
