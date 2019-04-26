package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestType {

    //main menu
    ENTER_COLLECTION("^enter collection$"),
    ENTER_SHOP("^enter shop$"),
    ENTER_BATTLE("^enter battle$"),

    //accountMenu
    CREATE_ACCOUNT("^create account (\\w+)$"),
    LOGIN("^login (\\w+)$"),
    HELP("^help$"),
    SHOW_LEADER_BOARD("^show leaderboard$"),
    SAVE("^save$"),
    LOGOUT("^logout$"),
    EXIT("^exit$"),


    //battleMenu,
    SELECT_USER("^select user (\\w+)$"),
    SINGLE_PLAYER("^single player$"),
    MULTI_PLAYER("^multi player$");


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
