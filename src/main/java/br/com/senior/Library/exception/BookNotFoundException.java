package br.com.senior.Library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "The book was not found")
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(){
        super("The book was not found");
    }
}
