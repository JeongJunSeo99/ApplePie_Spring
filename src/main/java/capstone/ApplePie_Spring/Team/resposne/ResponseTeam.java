package capstone.ApplePie_Spring.Team.resposne;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;

public class ResponseTeam extends ResponseType {
    public ResponseTeam(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
