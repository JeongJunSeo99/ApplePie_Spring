package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;

public class ResponseMember extends ResponseType {
    public ResponseMember(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
