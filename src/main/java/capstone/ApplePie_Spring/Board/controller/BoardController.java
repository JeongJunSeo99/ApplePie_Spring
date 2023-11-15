package capstone.ApplePie_Spring.Board.controller;

import capstone.ApplePie_Spring.Board.dto.BoardUserDto;
import capstone.ApplePie_Spring.Board.dto.FindBoardDto;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.dto.BoardUpdateDto;
import capstone.ApplePie_Spring.Board.service.BoardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "boards API", description = "boards 관련된 API")
@RequestMapping("/boards")
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    // 글 생성
    @PostMapping
    @Operation(summary = "글 생성 : file로 인해 swagger에 test 불가")
    public ResponseEntity<Object> createBoard(
            @RequestPart(value = "board") BoardSaveDto boardSaveDto,
            @RequestPart(value = "file", required = false) List<MultipartFile> files) throws Exception {
        return new ResponseEntity<>(boardService.save(boardSaveDto, files), HttpStatus.OK);
    }

    // 단일 글 조회
    @GetMapping("/{bid}")
    @Operation(summary = "단일 글 조회")
    public ResponseEntity<Object> getBoardOne(@PathVariable long bid) {
        return new ResponseEntity<>(boardService.findOne(bid), HttpStatus.OK);
    }


    // 전체 글 조회
    @PostMapping("/all") // get
    @Operation(summary = "전체 글 조회")
    public ResponseEntity<Object> getBoards(@RequestBody FindBoardDto findBoardDto) {
        return new ResponseEntity<>(boardService.BoardPagesBy(findBoardDto), HttpStatus.OK);
    }


    // 수정
    @PutMapping("/{bid}")
    @Operation(summary = "글 수정")
    public ResponseEntity<Object> getBoardOne(@PathVariable long bid,
                                              @RequestPart(value = "board") BoardUpdateDto boardUpdateDto,@RequestPart(value = "file", required = false) List<MultipartFile> files) throws Exception {
        return new ResponseEntity<>(boardService.update(bid, boardUpdateDto, files), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{bid}")
    @Operation(summary = "글 삭제")
    public ResponseEntity<Object> delete(@PathVariable long bid, @RequestBody BoardUserDto boardUserDto) {
        return new ResponseEntity<>(boardService.delete(bid, boardUserDto.getUserId()), HttpStatus.OK);
    }
}
