package capstone.ApplePie_Spring.Board.controller;

import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/boards")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    // 글 생성
    @PostMapping
    public ResponseEntity<Object> createBoard(
            @RequestPart(value = "board") BoardSaveDto boardSaveDto,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws Exception {
        return new ResponseEntity<>(boardService.save(boardSaveDto, files), HttpStatus.OK);
    }
}
