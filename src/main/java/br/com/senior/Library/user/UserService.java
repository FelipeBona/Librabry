package br.com.senior.Library.user;

public interface UserService {

    public boolean verifyLogin(String user, String password);

    public void addToken(String token);
}
