package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.dto.BoardFindDto;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.dto.FindBoardListDto;
import capstone.ApplePie_Spring.Board.dto.FindOneBoardDto;
import capstone.ApplePie_Spring.Board.repository.BoardRepository;
import capstone.ApplePie_Spring.Board.resposne.ResponseBoard;
import capstone.ApplePie_Spring.Board.resposne.ResponseBoardList;
import capstone.ApplePie_Spring.Board.resposne.ResponseNoBoard;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.repository.UserRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final FileService fileService;

    private static final Integer STATUS = 1;

    @Override
    public Object save(BoardSaveDto boardSaveDto, List<MultipartFile> files) throws Exception {

        Optional<User> findUser = userRepository.findByEmailAndStatus(boardSaveDto.getEmail(), STATUS);
        if (findUser.isEmpty()) {
            return new ResponseNoBoard(ExceptionCode.USER_FIND_NOT);
        }

        Board board = Board.builder()
                .boardSaveDto(boardSaveDto)
                .user(findUser.get())
                .build();
        boardRepository.save(board);

        boolean result;
        if (!files.isEmpty()) { // 파일 크기는 front에서 처리
            for (int i = 0; i < files.size(); i++) {
                MultipartFile multipartFile = files.get(i);
                result = fileService.save(board, i+1, files.get(i));
                if (!result) {
                    return new ResponseNoBoard(ExceptionCode.FILE_SAVE_NOT);
                }
            }
        }

        return new ResponseNoBoard(ExceptionCode.BOARD_CREATED_OK);
    }

    @Override
    public Object findOne(long boardId) {
        Optional<Board> findBoard = boardRepository.findByIdAndStatus(boardId, STATUS);
        if (findBoard.isEmpty()) {
            return new ResponseNoBoard(ExceptionCode.BOARD_FIND_NOT);
        }

        FindOneBoardDto findOneBoardDto = FindOneBoardDto.builder()
                .board(findBoard.get())
                .fileList(findBoard.get().getFiles())
                .build();
        return new ResponseBoard(ExceptionCode.BOARD_FIND_OK, findOneBoardDto);
    }

    @Override
    public Object BoardPagesBy(BoardFindDto boardFindDto) {
        List<Board> boardList = fetchPages(boardFindDto);

        List<FindBoardListDto> findBoardListDtoList = new ArrayList<>();
        for (Board board : boardList) {
            //Optional<File> file = fileService.findOne(board.getId(), 1);
            findBoardListDtoList.add(FindBoardListDto.builder()
                    .board(board).fileList(board.getFiles()).build());
        }

        return new ResponseBoardList(ExceptionCode.BOARD_FIND_OK, findBoardListDtoList);
    }

    private List<Board> fetchPages(BoardFindDto boardFindDto)  {
        PageRequest pageRequest = PageRequest.of(0, boardFindDto.getSize());
        Board.Category category = Board.Category.getValue(boardFindDto.getCategoryId());

        Long checkId = boardFindDto.getId();
        if (checkId == null) { // 처음 조회
            if (boardFindDto.getKeyword() == null)
                return boardRepository.findAllByCategoryAndStatusOrderByIdDesc(category, STATUS, pageRequest);
            return boardRepository.findAllByCategoryAndStatusAndTitleContainingOrderByIdDesc(category, boardFindDto.getKeyword(), STATUS, pageRequest);
        }
        else { // 불러오기
            if (boardFindDto.getKeyword() == null)
                return boardRepository.findAllByIdLessThanAndCategoryAndStatusOrderByIdDesc(boardFindDto.getId(), category, STATUS, pageRequest);
            return boardRepository.findAllByIdLessThanAndCategoryAndStatusAndTitleContainingOrderByIdDesc(boardFindDto.getId(), category, STATUS, boardFindDto.getKeyword(), pageRequest);
        }
    }

}
