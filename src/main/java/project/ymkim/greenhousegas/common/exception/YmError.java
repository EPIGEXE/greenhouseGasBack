package project.ymkim.greenhousegas.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YmError {
    
    SUCCESS(200, "0000", null),
    
    // 알수없는 (관리자에게 문의)
    UNKNOWN(500, "CX1000", "UNKNOWN"),

    // 올바르지 않은 요청
    BAD_REQUEST(400, "CX1001", "BAD_REQUEST"),

    // 인증안됨
    //UNAUTHORIZED(401, "CX1002", "UNAUTHORIZED"),

    // 권한없음
    //FORBIDDEN(403, "CX1003", "FORBIDDEN"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
