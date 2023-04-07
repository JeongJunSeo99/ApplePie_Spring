package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.dto.BoardFindDto;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.resposne.ResponseBoard;
import capstone.ApplePie_Spring.config.ResponseType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    Object save(BoardSaveDto boardSaveDto, List<MultipartFile> files) throws Exception;
    Object findOne(long boardId);
    Object BoardPagesBy(BoardFindDto boardFindDto);
}
