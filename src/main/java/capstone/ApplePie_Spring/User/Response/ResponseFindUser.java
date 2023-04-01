package capstone.ApplePie_Spring.User.Response;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseFindUser extends ResponseType {
    private Object user;

    public ResponseFindUser(ExceptionCode exceptionCode, Object user) {
        super(exceptionCode);
        this.user = user;
    }
}
