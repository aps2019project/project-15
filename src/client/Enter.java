package client;

public class Enter implements ToJson {
    String message;

    public Enter(String message) {
        this.message = "Enter " + message;
    }
}
