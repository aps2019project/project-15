package client;


public class CreateAccount implements ToJson {
    String username;
    String password;

    public CreateAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
