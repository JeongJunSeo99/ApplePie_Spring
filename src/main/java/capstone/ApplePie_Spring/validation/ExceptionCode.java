package capstone.ApplePie_Spring.validation;

import java.util.Arrays;

import static capstone.ApplePie_Spring.validation.HttpStatus.*;

public enum ExceptionCode {
    /**
     * 회원가입 및 로그인
     */
    SIGNUP_CREATED_OK(SUCCESS, "A000", "회원가입 성공"),
    SIGNUP_DUPLICATED_EMAIL(NOT_FOUND_VALUE, "A002", "중복된 EMAIL"),
    SIGNUP_DUPLICATED_NICKNAME(NOT_FOUND_VALUE, "A003", "중복된 NICKNAME"),
    WITHDRAW_USER_OK(SUCCESS, "A004", "회원 탈퇴 성공"),

    LOGIN_OK(SUCCESS, "B000", "로그인 성공"),
    LOGIN_NOT_FOUND_EMAIL(NOT_FOUND_VALUE, "B001", "잘못된 EMAIL"),
    LOGIN_NOT_FOUND_PW(NOT_FOUND_VALUE, "B002", "잘못된 PASSWORD"),

    USER_ERROR(NOT_FOUND_VALUE, "C000", "처리 실패"),
    USER_FIND_OK(SUCCESS, "C001", "회원 조회 성공"),
    USER_FIND_NOT(INVALID_ACCESS, "C002", "회원 조회 실패"),
    USER_UPDATE_OK(SUCCESS, "C003", "회원정보 수정 성공"),

    PROFILE_CREATED_OK(SUCCESS, "D000", "PROFILE 생성"),
    PROFILE_FIND_NOT(SUCCESS, "D001", "PROFILE 없음"),
    PROFILE_CREATED_ERROR(DUPLICATED_VALUE, "D002", "PROFILE 존재"),
    PROFILE_FIND_OK(SUCCESS, "D003", "PROFILE 조회 성공"),

    BOARD_CREATED_OK(SUCCESS, "H00", "BOARD 생성"),
    BOARD_FIND_NOT(NOT_FOUND_VALUE, "H001", "BOARD 찾을 수 없음"),
    BOARD_FIND_OK(SUCCESS, "H002", "BOARD 조회 성공"),
    BOARD_UPDATE_OK(SUCCESS, "H03", "BOARD 수정 성공"),
    BOARD_DELETE_OK(SUCCESS, "H04", "BOARD 삭제 성공"),

    FILE_SAVE_NOT(INVALID_ACCESS, "I000", "FILE 저장 실패"),

    TEAM_CREATED_OK(SUCCESS, "J00", "TEAM 생성"),
    TEAM_FIND_NOT(NOT_FOUND_VALUE, "J001", "TEAM 찾을 수 없음"),
    TEAM_FIND_OK(SUCCESS, "J002", "TEAM 조회 성공"),
    TEAM_COMPLETE(SUCCESS, "J03", "TEAM 마감"),
    TEAM_UPDATE_OK(SUCCESS, "J04", "TEAM 수정 성공"),
    TEAM_DELETE_OK(SUCCESS, "J05", "TEAM 삭제 성공"),

    VOLUNTEER_OK(SUCCESS, "H00", "지원자 저장"),
    VOLUNTEER_CANCEL(SUCCESS, "H01", "지원자 취소"),
    VOLUNTEER_FIND_NOT(NOT_FOUND_VALUE, "H01", "지원자 조회 실패"),

    MEMBER_OK(SUCCESS, "I000", "팀원으로 처리 성공"),
    MEMBER_FIND_OK(SUCCESS, "I001", "팀원 조회 성공"),

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
