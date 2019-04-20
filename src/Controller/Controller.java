package Controller;

import Model.Account;
import Model.Map;
import View.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {

    public static boolean quit = false;
    public static ArrayList<Account> players = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();

    public static View view = new View();
    public static Account account = new Account();


    public ArrayList<Account> getPlayers() {
        return players;
    }


    public static Scanner scanner = new Scanner(System.in);

    private static Map map = new Map();

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
