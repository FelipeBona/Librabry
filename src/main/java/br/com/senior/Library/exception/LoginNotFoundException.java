package br.com.senior.Library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Login not found")
public class LoginNotFoundException extends RuntimeException{

    public LoginNotFoundException(){
        super("Login not found");
    }
}
