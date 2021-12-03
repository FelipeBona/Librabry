package br.com.senior.Library;

import br.com.senior.Library.exception.BookNotFoundException;
import br.com.senior.Library.exception.LoginNotFoundException;
import br.com.senior.Library.exception.WrongLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NullPointerException.class, BookNotFoundException.class, LoginNotFoundException.class, WrongLoginException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception ex, WebRequest request){

        if(ex instanceof NullPointerException){
            return new ResponseEntity<>("Internal system failure: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else if(ex instanceof BookNotFoundException){
            return new ResponseEntity<>("The book was not informed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else if(ex instanceof LoginNotFoundException){
            return new ResponseEntity<>("The login was not informed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else if(ex instanceof WrongLoginException){
            return new ResponseEntity<>("Wrong Login: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else if(ex instanceof IllegalArgumentException){
            return new ResponseEntity<>("Parameters are not right: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>("Internal system failure: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
