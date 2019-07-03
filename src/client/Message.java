package client;

import com.google.gson.Gson;

public class Message implements ToJson{
    private String sender;
    private String message;
    private CreateAccount createAccount;
    private Login login;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public static Message fromJson(String string) {
        return new Gson().fromJson(string, Message.class);
    }

    @Override
    public String toString() {
        return sender + ": " + message + "\n";
    }
}
