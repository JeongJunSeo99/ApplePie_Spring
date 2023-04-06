package capstone.ApplePie_Spring.Board.service;

import capstone.ApplePie_Spring.Board.domain.Board;
import capstone.ApplePie_Spring.Board.dto.BoardSaveDto;
import capstone.ApplePie_Spring.Board.repository.BoardRepository;
import capstone.ApplePie_Spring.Board.resposne.ResponseBoard;
import capstone.ApplePie_Spring.Board.resposne.ResponseNoBoard;
import capstone.ApplePie_Spring.User.domain.User;
import capstone.ApplePie_Spring.User.repository.UserRepository;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final FileService fileService;

    private static final Integer STATUS = 1;

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

        return new ResponseBoard(ExceptionCode.BOARD_CREATED_OK, board.getId());
    }



}
