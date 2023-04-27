package capstone.ApplePie_Spring.User.response;

import capstone.ApplePie_Spring.validation.ExceptionCode;
import lombok.Getter;

@Getter
public class ResponseLogin extends ResponseUser {
    private Long id;

    public ResponseLogin(ExceptionCode exceptionCode, Long id) {
        super(exceptionCode);
        this.id = id;
    }
}
