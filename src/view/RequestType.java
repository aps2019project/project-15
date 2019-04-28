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
    MULTI_PLAYER("^multi player$"),

    //shopMenu
    SHOW_COLLECTION_IN_SHOP("^show collection$"),
    SEARCH("^search (\\w+)$"),
    SEARCH_COLLECTION_IN_SHOW("^search collection (\\w+)$"),
    BUY("^buy (\\w+)$"),
    SELL("^sell (\\w+)$"),
    SHOW("^show$"),
    EXIT_SHOP("^exit$"),

    //collectionMenu
    EXIT_COLLECTION("^exit$"),
    SHOW_COLLECTION("^show$"),
    SEARCH_COLLECTION("^search (\\w+)$"),
    SAVE_COLLECTION("^save$"),
    CREATE_DECK("^create deck (\\w+)$"),
    DELETE_DECK("^delete deck (\\w+)$"),
    ADD_COLLECTION("^add (\\w+) to deck (\\w+)$"),
    REMOVE_COLLECTION("^remove (\\w+) from deck (\\w+)$"),
    VALIDATE("^validate (\\w+)$"),
    SELECT_DECK("^select deck (\\w+)$"),
    SHOW_ALL_DECKS("^show all decks$"),
    SHOW_DECK("^show deck (\\w+)$");

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
