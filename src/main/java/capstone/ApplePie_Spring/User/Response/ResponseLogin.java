package capstone.ApplePie_Spring.User.Response;

import capstone.ApplePie_Spring.config.ResponseType;
import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseLogin extends ResponseType {
    private Long id;

    public ResponseLogin(ExceptionCode exceptionCode, Long id) {
        super(exceptionCode);
        this.id = id;
    }
}
