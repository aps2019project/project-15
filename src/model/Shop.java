package model;

import controller.Controller;
import model.menu.MainMenu;
import view.View;

import java.util.ArrayList;

public class Shop {

    private static Shop shop = new Shop();

    public static Shop getInstance() {
        return shop;
    }

    private DataCenter dataCenter = DataCenter.getInstance();

    ArrayList<Card> allCards = new ArrayList<>();
    private ArrayList<Minion> allMinions = new ArrayList<>();
    private ArrayList<Spell> allSpells = new ArrayList<>();
    private ArrayList<Hero> allHeroes = new ArrayList<>();
    private ArrayList<Item> allItems = new ArrayList<>();
    private View view = View.getInstance();


    public void exitShop() {
        view.exitShop();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public void showCollection() {
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public ArrayList<Minion> getAllMinions() {
        return allMinions;
    }

    public ArrayList<Spell> getAllSpells() {
        return allSpells;
    }

    public ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }

    public ArrayList<Item> getAllItems() {
        return allItems;
    }

    public void addCard() {
        for (Hero hero : dataCenter.getHeroes()) {
            this.allCards.add(hero);
            this.allHeroes.add(hero);
        }
        for (Minion minion : dataCenter.getMinions()) {
            this.allCards.add(minion);
            this.allMinions.add(minion);
        }

        for (Spell spell : dataCenter.getSpells()) {
            this.allCards.add(spell);
            this.allSpells.add(spell);
        }
    }

    public void addItem() {
        this.allItems.addAll(dataCenter.getItems());
    }

    public int searchInCollection(String name) {
        for (Card card : this.allCards) {
            if (card.getName().equalsIgnoreCase(name)) {
                return 1;
            }
        }
        return 0;
    }

    public boolean buy(String name) {
        Card card = Card.returnCardByName(name);
        Item item = findItemByName(name);
        if (card == null && item == null) {
            view.itemOrCardIsNotInShop();
            return false;
        }
        if (card != null && card.price > Controller.currentAccount.getMoney()) {
            view.notEnoughMoney();
            return false;
        }
        if (item != null && item.getPrice().matches("\\d+") && Integer.parseInt(item.price) > Controller.currentAccount.getMoney()) {
            view.notEnoughMoney();
            return false;
        }
        if (item != null && item.getPrice().matches("\\d+") && Controller.currentAccount.getMyCollection().getMyItems().size() >= 3) {
            view.tooManyItemsInMyCollection();
            return false;
        }
        if (card != null) {
            Controller.currentAccount.reduceMoney(card.getPrice());
            Controller.currentAccount.getMyCollection().addCardToCollection(card);
            card.numOfCardInCollection++;
            view.cardBought();
            view.remainingMoney();
            return true;
        }
        if (item.getPrice().matches("[0-9]+")) {
            Controller.currentAccount.reduceMoney(Integer.parseInt(item.getPrice()));
        } else {
            Controller.currentAccount.myCollectibles.add(item);
        }
        if (Controller.currentAccount.getMyCollection().addItemToCollection(item)) {
            view.itemBought();
            view.remainingMoney();
        }
        return false;
    }


    public void show() {
        view.showMinions();
        view.showSpells();
        view.showHeroesInShop();
        view.showItems();
    }

    public String search(String name) {
        for (Card card : shop.getAllCards()) {
            if (card.getName().equals(name)) {
                view.cardExistsInShop();
                return card.getCardID();
            }
        }
        for (Item item : shop.getAllItems()) {
            if (item.getItemName().equals(name)) {
                view.itemExistsInShop();
                return item.getId();
            }
        }
        view.cardDoesntExistInShop();
        return null;
    }

    public void help() {
        view.printShopHelpMenu();
    }

    public boolean sell(String name) {
        Item item = Item.getItemByName(name);
        Card card = Card.returnCardByName(name);
        card = Controller.currentAccount.getMyCollection().findCardInCollection(card);
        boolean realItem = false;
        if (Controller.currentAccount.getMyCollection().getMyItems().contains(item)) {
            realItem = true;
        }
        if (card == null && item == null && !realItem) {
            view.notOwnThisCardOrItem();
            view.remainingMoney();
            return false;
        }
        if (card != null) {
            Controller.currentAccount.sellCard(card);
            Controller.currentAccount.getMyCollection().removeCardFromCollection(card);
            Controller.currentAccount.addMoney(card.getPrice());
            card.numOfCardInCollection--;
            for (Deck deck : Controller.currentAccount.getMyCollection().getMyDecks()) {
                deck.getCards().remove(card);
            }
            view.cardSold();
            view.remainingMoney();
            return true;
        }
        if (item != null && realItem) {
            Controller.currentAccount.getMyCollection().removeItemFromCollection(item);
            Controller.currentAccount.addMoney(Integer.parseInt(item.getPrice()));
            for (Deck deck : Controller.currentAccount.getMyCollection().getMyDecks()) {
                deck.getCards().remove(card);
            }
            view.itemSold();
            view.remainingMoney();
            return true;
        }
        return false;
    }

    public boolean isSold(String name) {
        Card card = Card.returnCardByName(name);
        return card != null && !Controller.currentAccount.getMyCollection().hasCard(card) && Controller.currentAccount.soldCard(card);
    }


    private Item findItemByName(String name) {
        for (Item item : DataCenter.getInstance().getItems()) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
