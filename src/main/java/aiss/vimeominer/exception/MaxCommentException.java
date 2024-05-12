package aiss.vimeominer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "The value of max comments needs to be an integer greater or equal to 0")
public class MaxCommentException extends Exception{
}
