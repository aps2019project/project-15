package Model;

import Model.Map;
import View.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Control {
    static View view = new View();

    private static Map map = new Map();
    ArrayList<Account> players;

    public void startGame() {
        while (true) {

        }
    }

    public void sortRankings() {
        Collections.sort(players, Comparator.comparing(Account::getNumOfWins).reversed());
    }

    public static Map getMap() {
        return map;
    }

    public void save() {

    }
}
