package capstone.ApplePie_Spring.Board.resposne;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;

import javax.annotation.Nullable;

public class ResponseBoard extends ResponseNoBoard {

    private Long boardId;

    public ResponseBoard(ExceptionCode exceptionCode, Long boardId) {
        super(exceptionCode);
        this.boardId = boardId;
    }
}
