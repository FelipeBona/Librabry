package br.com.senior.Library.user;

import br.com.senior.Library.exception.WrongLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping
    public ResponseEntity<String> logar(@RequestBody UserDto user) {

        String userName = user.user;
        String password = user.password;

        if(userService.verifyLogin(userName, password)) {
            String token = UUID.randomUUID().toString();
            userService.addToken(token);
            return ResponseEntity.ok().body(token);
        } else {
            throw new WrongLoginException();
        }
    }
}
