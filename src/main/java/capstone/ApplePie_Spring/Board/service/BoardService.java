package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.dto.BoardFindDto;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.dto.BoardUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    Object save(BoardSaveDto boardSaveDto, List<MultipartFile> files) throws Exception;
    Object findOne(long boardId);
    Object BoardPagesBy(BoardFindDto boardFindDto);
    Object update(Long id, BoardUpdateDto boardUpdateDto, List<MultipartFile> files) throws Exception;
    Object delete(Long id);
}
