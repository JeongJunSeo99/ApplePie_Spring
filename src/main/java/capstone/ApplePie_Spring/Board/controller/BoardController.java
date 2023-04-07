package capstone.ApplePie_Spring.Board.controller;

import capstone.ApplePie_Spring.Board.dto.BoardFindDto;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.dto.BoardUpdateDto;
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

    // 단일 글 조회
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBoardOne(@PathVariable long id) {
        return new ResponseEntity<>(boardService.findOne(id), HttpStatus.OK);
    }


    // 전체 글 조회
    @GetMapping
    public ResponseEntity<Object> getBoards(@RequestBody BoardFindDto boardFindDto) {
        return new ResponseEntity<>(boardService.BoardPagesBy(boardFindDto), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Object> getBoardOne(@PathVariable long id,
                                              @RequestPart(value = "board") BoardUpdateDto boardUpdateDto,
                                              @RequestPart(value = "file", required = false) List<MultipartFile> files) throws Exception {
        return new ResponseEntity<>(boardService.update(id, boardUpdateDto, files), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id, @RequestBody String email) {
        return new ResponseEntity<>(boardService.delete(id, email), HttpStatus.OK);
    }
}
