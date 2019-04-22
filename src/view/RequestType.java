package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RequestType {

    //accountMenu

    CREATE_ACCOUNT("^create account (\\w+)$"),
    LOGIN("^login (//w+)$"),
    HELP("help$"),
    SHOW_LEADER_BOARD("^show leader board$"),
    SELECT_USER("^select user (\\w+)$") ,



    ;
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
