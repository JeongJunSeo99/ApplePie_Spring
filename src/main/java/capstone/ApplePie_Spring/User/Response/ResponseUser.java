package capstone.ApplePie_Spring.User.Response;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;

public class ResponseUser extends ResponseType {
    public ResponseUser(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
