package Controller;

import Model.Account;
import Model.Map;
import View.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {
    public static View view = new View();
    Account account = new Account();

    public static Scanner getScanner() {
        return scanner;
    }

    public static Scanner scanner = new Scanner(System.in);

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

    public static int getNumber() {
        return scanner.nextInt();
    }
}
