package rs.hnp.inventory.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorCode errorCode;

    public ApiException(Throwable cause, HttpStatus httpStatus, ErrorCode errorCode, String errorMessage) {
        super(errorMessage, cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public ApiException(HttpStatus httpStatus, ErrorCode errorCode, String errorMessage) {
        this(null, httpStatus, errorCode, errorMessage);
    }

}
