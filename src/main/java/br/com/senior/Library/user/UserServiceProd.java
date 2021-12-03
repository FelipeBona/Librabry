package br.com.senior.Library.user;

import br.com.senior.Library.exception.LoginNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("!dev")
@Service
public class UserServiceProd implements UserService{

    @Value("${br.com.senior.Library.user}")
    private String user;

    @Value("${br.com.senior.Library.password}")
    private String password;

    public static final List<String> tokens = new ArrayList<>();

    public boolean verifyLogin(String user, String password) {

        if(user != null && password != null){
            if(user.equals(this.user) && password.equals(this.password)) {
                return true;
            } else {
                return false;
            }
        } else{
            throw new LoginNotFoundException();
        }
    }
    public void addToken(String token){
        tokens.add(token);
    }
}
