package client;

import com.google.gson.Gson;

public class CreateAccount implements ToJson{
    String username;
    String password;
    public CreateAccount(String username, String password){
        this.username = username;
        this.password = password;
    }
}
