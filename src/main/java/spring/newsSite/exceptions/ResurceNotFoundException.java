package spring.newsSite.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Data
@AllArgsConstructor
public class ResurceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final String resourceField;
    private final Object object;
}
