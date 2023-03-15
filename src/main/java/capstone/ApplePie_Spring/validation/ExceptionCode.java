package capstone.ApplePie_Spring.validation;

import java.util.Arrays;

import static capstone.ApplePie_Spring.validation.HttpStatus.*;

public enum ExceptionCode {
    /**
     * 회원가입 및 로그인
     */
    SIGNUP_CREATED_OK(HttpStatus.CREATED, "A000", "회원가입 성공"),

    LOGIN_OK(SUCCESS, "B000", "로그인 성공"),


    /**
     * 잘못된 ExceptionCode
     */
    EMPTY(null, "", "");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ExceptionCode findExceptionCodeByCode(String code) {
        return Arrays.stream(ExceptionCode.values())
                .filter(x -> x.getCode().equals(code))
                .findFirst()
                .orElse(EMPTY);
    }
}
