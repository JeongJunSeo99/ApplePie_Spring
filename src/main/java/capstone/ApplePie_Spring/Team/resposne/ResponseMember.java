package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseMember extends ResponseType {

    private List<Integer> count;

    public ResponseMember(ExceptionCode exceptionCode, List<Integer> count) {
        super(exceptionCode);
        this.count = count;
    }
}
