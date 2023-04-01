package capstone.ApplePie_Spring.validation;

import java.util.Arrays;

import static capstone.ApplePie_Spring.validation.HttpStatus.*;

public enum ExceptionCode {
    /**
     * 회원가입 및 로그인
     */
    SIGNUP_CREATED_OK(CREATED, "A000", "회원가입 성공"),
    SIGNUP_DUPLICATED_EMAIL(NOT_FOUND_VALUE, "A002", "중복된 EMAIL"),
    SIGNUP_DUPLICATED_NICKNAME(NOT_FOUND_VALUE, "A003", "중복된 NICKNAME"),
    WITHDRAW_USER_OK(SUCCESS, "A004", "회원 탈퇴 성공"),

    LOGIN_OK(SUCCESS, "B000", "로그인 성공"),
    LOGIN_NOT_FOUND_EMAIL(NOT_FOUND_VALUE, "B001", "잘못된 EMAIL"),
    LOGIN_NOT_FOUND_PW(NOT_FOUND_VALUE, "B002", "잘못된 PASSWORD"),

    USER_ERROR(NOT_FOUND_VALUE, "C000", "처리 실패"),
    USER_FIND_OK(SUCCESS, "C001", "회원정보 조회 성공"),
    USER_UPDATE_OK(SUCCESS, "C002", "회원정보 수정 성공"),

    PROFILE_FIND_OK(SUCCESS, "D000", "PROFILE 조회 성공"),
    PROFILE_FIND_NOT(SUCCESS, "D001", "PROFILE 없음"),

    /**
     * 잘못된 ExceptionCode
     */
    EMPTY(null, "Z000", "값이 없습니다."),
    WRONG_APPROACH(INVALID_ACCESS, "Z001", "잘못된 접근");

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
