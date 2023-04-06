package capstone.ApplePie_Spring.Board.resposne;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;

import javax.annotation.Nullable;

public class ResponseBoard extends ResponseType {

    @Nullable
    private Long boardId;

    public ResponseBoard(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ResponseBoard(ExceptionCode exceptionCode, @Nullable Long boardId) {
        super(exceptionCode);
        this.boardId = boardId;
    }
}
