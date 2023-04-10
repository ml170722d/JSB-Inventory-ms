package rs.hnp.inventory.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class ApiExceptionFactory {

    public static ApiException articleNotFound() {
        return new ApiException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, "Article not found");
    }

}
