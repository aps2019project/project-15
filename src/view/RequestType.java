package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestType {

    //accountMenu

    CREATE_ACCOUNT("^create account (\\w+)$"),
    LOGIN("^login (\\w+)$"),
    HELP("^help$"),
    SHOW_LEADER_BOARD("^show leader board$"),
    SAVE("^save$"),
    LOGOUT("^logout$"),
    ENTER_COLLECTION("^enter collection$"),
    ENTER_SHOP("^enter shop$"),
    ENTER_BATTLE("^enter battle$"),
    EXIT("^exit$"),


    //battleMenu,
    SELECT_USER("^select user (\\w+)$");
    private Pattern pattern;
    private Matcher matcher;

    public Matcher setMatcher(String command) {
        this.matcher = pattern.matcher(command);
        return matcher;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    RequestType(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
}
