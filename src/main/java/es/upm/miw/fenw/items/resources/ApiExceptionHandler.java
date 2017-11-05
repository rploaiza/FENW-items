package es.upm.miw.fenw.items.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.upm.miw.fenw.items.resources.exceptions.ErrorMessage;
import es.upm.miw.fenw.items.resources.exceptions.ItemIdNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ItemIdNotFoundException.class})
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request,Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception,request.getRequestURI().toString());
        return errorMessage;
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ExceptionHandler({})
    // @ResponseBody
    public ErrorMessage badRequest(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception,"");
        return errorMessage;
    }

    // @ResponseStatus(HttpStatus.CONFLICT)
    // @ExceptionHandler({})
    // @ResponseBody
    public ErrorMessage conflictRequest(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception,"");
        return errorMessage;
    }

}
