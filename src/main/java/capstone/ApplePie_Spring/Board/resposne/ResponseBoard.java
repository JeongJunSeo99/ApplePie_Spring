package capstone.ApplePie_Spring.Board.resposne;

import capstone.ApplePie_Spring.Board.dto.FindOneBoardDto;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseBoard extends ResponseNoBoard {

    private Object data;

    public ResponseBoard(ExceptionCode exceptionCode, FindOneBoardDto findOneBoardDto) {
        super(exceptionCode);
        this.data = findOneBoardDto;
    }
}
