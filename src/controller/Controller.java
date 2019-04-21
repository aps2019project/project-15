package controller;

import model.Account;
import model.Map;
import view.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Controller {


    public static boolean quit = false;
    public static ArrayList<Account> players = new ArrayList<>();
    public static ArrayList<String> usernames = new ArrayList<>();

    //public View view = new View();
    public Request request = new Request();
    public static Account account = new Account();


    public ArrayList<Account> getPlayers() {
        return players;
    }




    public void startGame() {
        while (true) {

        }
    }

    public void sortRankings() {
        Collections.sort(players, Comparator.comparing(Account::getNumOfWins).reversed());
    }


    public void save() {

    }

}
