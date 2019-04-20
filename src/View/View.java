package View;

import Model.Collectible;

public class View {

    public static void printMainMenuOfGame() {
        System.out.println("1.Collection");
        System.out.println("2.Shop");
        System.out.println("3.Battle");
        System.out.println("4.Exit");
        System.out.println("5.Help");
    }

    public static void printCollectibleID(Collectible collectible) {
        System.out.println("collectible Id: " + collectible.getId());
    }

    public static void printIncrementHealth(int number) {
        System.out.println(number + "units added to health");
    }

    public static void printCollectibleDescription(Collectible collectible) {

    }
    public static void printEnterCollectibleID() {
        System.out.println("Enter the collectible ID: ");
    }

    public void showMinionsYouCanMove() {

    }
    public void showMinionsYouCanAttack() {

    }
    public void cardsYouCanInsert() {

    }
}
