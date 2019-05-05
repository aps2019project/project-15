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
    SHOW_MENU("^show menu$"),


    //battleMenu,
    SELECT_USER("^select user (\\w+)$"),
    SINGLE_PLAYER("^single player$"),
    MULTI_PLAYER("^multi player$"),
    GAME_INFO("^game info$"),
    SHOW_MY_MINIONS("^show my minions$"),
    SHOW_OPP_MINIONS("^$show opponent minions"),
    SHOW_CARD_INFO("^show card info (\\w+)$"),
    SELECT_CARD("^select (\\w+)$"),
    MOVE_TO("^move to (\\d+),(\\d+)$"),
    ATTACK_OPP("^attack (\\w+)$"),
    ATTACH_COMBO("^attack combo (\\w+)+$"),
    USE_SPECIAL_POWER("^use special power (\\w+),(\\w+)$"),
    SHOW_HAND("^show hand$"),
    INSERT_CARD_IN_BLOCK("^insert (\\w+) in (\\w+),(\\w+)$"),
    END_TURN("^end turn$"),
    SHOW_COLLECTABLES("^show collectables$"),
    SELECT_COLLECTABLE("^select (\\w+)$"),
    SHOW_INFO("^show info$"),
    SHOW_INFO_CARD_ID("^show info (\\w+)$"),
    USE_LOCATION("^use location (\\w+),(\\w+)$"),
    SHOW_NEXT_CARD("^show next card$"),
    ENTER_GRAVEYARD("^enter graveyard$"),
    END_GAME("^end game$"),
    SHOW_CARDS_GRAVEYARD("^show cards$"),
    STORY_MODE("^story mode$"),
    CUSTOM_GAME("^custom game$"),
    KILL_OPPONENT("^kill opponent$"),
    GET_FLAG("^get flag$"),
    COLLECT_FlAG("^collect flag$"),

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
    VALIDATE("^validate deck (\\w+)$"),
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
