package aiss.vimeominer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason = "The value of max videos needs to be an integer greater or equal to 0")
public class MaxVideoException extends Exception{
}
