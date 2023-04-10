package rs.hnp.inventory.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionFactory {

    public static ApiException articleNotFound() {
        return new ApiException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, "Article not found");
    }

    public static ApiException articlePresent() {
        return new ApiException(HttpStatus.CONFLICT, ErrorCode.CONFLICT, "Article present in the DB");
    }

}
