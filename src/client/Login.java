package client;

public class Login implements ToJson {
    String username;
    String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
