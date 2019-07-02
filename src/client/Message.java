package client;

import com.google.gson.Gson;

public class Message {
    private String sender;
    private String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Message fromJson(String string) {
        return new Gson().fromJson(string, Message.class);
    }

    @Override
    public String toString() {
        return sender + ": " + message + "\n";
    }
}
