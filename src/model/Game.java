package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Game {

    private Map map = new Map();
    private ArrayList<Flag> flags = new ArrayList<>();
    private ModeOfGame Mode;
    private ArrayList<Card> cardsInGame = new ArrayList<>();
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int turn = (int) (Math.random() % 2 + 1);
    private int result;
    private int timeOfGame;
    public Card currentCard;
    public Item currentItem;
    private boolean storyMode = false;
    private View view = View.getInstance();

    public Account whoseTurn() {
        if (Controller.currentAccount.myTurn) {
            return Controller.currentAccount;
        }
        return Controller.enemyAccount;
    }

    Map getMap() {
        return map;
    }

    public void addFlagsToGame(int n) {
        for (int i = 0; i < n; i++) {
            Flag flag = new Flag();
            flags.add(flag);
            map.addFlagToMap(flag);
        }
    }

    private int player1Mp = setInitialPlayer1Mp();
    private int player2Mp = setInitialPlayer2Mp();
    private int firstTurn;
    boolean done = false;
    private boolean finishedGame = false;

    public boolean isFinishedGame() {
        return finishedGame;
    }

    private void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    private void endGameSetter() {
        switch (this.Mode) {
            case killOpponent:
                setFinishedGame(killOpponentGameEnded());
                if (storyMode) {
                    Controller.currentGame = new Game();
                    Controller.currentGame.storyMode = true;
                    Controller.currentGame.setTypeOfGame(2);
                }
                break;
            case KeepFlag:
                setFinishedGame(keepFlagEndGame());
                if (storyMode) {
                    Controller.currentGame = new Game();
                    Controller.currentGame.storyMode = true;
                    Controller.currentGame.setTypeOfGame(3);
                    Controller.currentGame.addFlagsToGame(5);
                }
                break;
            case CollectFlags:
                setFinishedGame(collectFlagModeEndGame());
        }
    }

    private boolean collectFlagModeEndGame() {
        ArrayList<Card> cardsThatOwnFlag = new ArrayList<>();
        for (Flag flag : flags) {
            if (flag.card != null) {
                cardsThatOwnFlag.add(flag.card);
            }
        }
        int cardsOfCurrentAccount = 0;
        int cardsOfEnemyAccount = 0;
        for (Card card : cardsThatOwnFlag) {
            if (Controller.currentAccount.getCardsInGame().contains(card)) {
                cardsOfCurrentAccount++;
            }
        }
        cardsOfEnemyAccount = cardsThatOwnFlag.size() - cardsOfCurrentAccount;
        if (cardsOfCurrentAccount >= cardsThatOwnFlag.size()) {
            view.gameWon(Controller.currentAccount.getUsername());
            Controller.currentAccount.addMoney(1500);
            Controller.currentAccount.addToHistory(1, timeOfGame, Controller.enemyAccount.getUsername());
            Controller.enemyAccount.addToHistory(0, timeOfGame, Controller.currentAccount.getUsername());
            return true;
        } else if (cardsOfEnemyAccount >= cardsThatOwnFlag.size()) {
            view.gameWon(Controller.enemyAccount.getUsername());
            Controller.enemyAccount.addMoney(1500);
            Controller.currentAccount.addToHistory(0, timeOfGame, Controller.enemyAccount.getUsername());
            Controller.enemyAccount.addToHistory(1, timeOfGame, Controller.currentAccount.getUsername());
            return true;
        }
        return false;
    }

    private boolean keepFlagEndGame() {
        for (Flag flag : flags) {
            if (flag.singleFlagModeGameWon()) {
                if (Controller.currentAccount.getMainDeck().getCards().contains(flag.card)) {
                    view.gameWon(Controller.currentAccount.getUsername());
                    Controller.currentAccount.addMoney(1000);
                    Controller.currentAccount.addToHistory(1, timeOfGame, Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addToHistory(0, timeOfGame, Controller.currentAccount.getUsername());
                    return true;
                } else {
                    view.gameWon(Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addMoney(1000);
                    Controller.currentAccount.addToHistory(0, timeOfGame, Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addToHistory(1, timeOfGame, Controller.currentAccount.getUsername());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean killOpponentGameEnded() {
        for (Card card : graveYard) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                if (Controller.currentAccount.getMainDeck().getDeckHero().equals(card)) {
                    view.gameWon(Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addMoney(500);
                    Controller.currentAccount.addToHistory(0, timeOfGame, Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addToHistory(1, timeOfGame, Controller.currentAccount.getUsername());
                    return true;
                } else {
                    view.gameWon(Controller.currentAccount.getUsername());
                    Controller.currentAccount.addMoney(500);
                    Controller.currentAccount.addToHistory(1, timeOfGame, Controller.enemyAccount.getUsername());
                    Controller.enemyAccount.addToHistory(0, timeOfGame, Controller.currentAccount.getUsername());
                    return true;
                }
            }
        }
        return false;
    }


    public void setMode(ModeOfGame mode) {
        Mode = mode;
    }

    private int setInitialPlayer1Mp() {
        if (this.firstTurn == 1) {
            return 2;
        }
        return 3;
    }

    private int setInitialPlayer2Mp() {
        if (this.firstTurn == 2) {
            return 2;
        }
        return 3;
    }


    public ModeOfGame getMode() {
        return Mode;
    }

    void addPlayerOneMp(int n) {
        this.player1Mp += n;
    }

    void addPlayerTwoMp(int n) {
        this.player2Mp += n;
    }

    public void updateGraveYard() {
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            if (card.Hp <= 0) {
                graveYard.add(card);
                ghooleTakCheshm(card);
                Controller.currentAccount.removeCardInGame(card);
            }
        }
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            if (card.Hp <= 0) {
                graveYard.add(card);
                ghooleTakCheshm(card);
                Controller.enemyAccount.removeCardInGame(card);
            }
        }
        for (Flag flag : flags) {
            if (flag.card != null) {
                if (graveYard.contains(flag.card)) {
                    flag.setCurrentBlock(flag.card.getCurrentBlock());
                    flag.removeCard();
                }
            }
        }
    }

    private void ghooleTakCheshm(Card card) {
        if (card.getName().equalsIgnoreCase("GhooleTakCheshm")) {
            Minion minion = (Minion) card;
            ArrayList<Card> cards = minion.cardsAttacked(map, minion.getCurrentBlock());
            for (Card attackedCard : cards) {
                try {
                    minion.ghooleTakCheshm(attackedCard);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void reducePlayerOneMp(int n) {
        player1Mp -= n;
    }

    private void reducePlayerTwoMp(int n) {
        player2Mp -= n;
    }

    public void setTypeOfGame(int number) {
        switch (number) {
            case (0): {
                this.storyMode = true;
                Mode = ModeOfGame.killOpponent;
                break;
            }
            case (1): {
                Mode = ModeOfGame.killOpponent;
                break;
            }
            case (2): {
                Mode = ModeOfGame.KeepFlag;
                break;
            }
            case (3): {
                Mode = ModeOfGame.CollectFlags;
                break;
            }
        }
    }

    public int getTurn() {
        return turn;
    }

    public void switchTurn() {
        Account account = Controller.enemyAccount;
        Controller.enemyAccount = Controller.currentAccount;
        Controller.currentAccount = account;
        if (this.turn == 1) {
            player2Mp++;
            this.turn = 2;
        } else if (this.turn == 2) {
            player1Mp++;
            this.turn = 1;
        }
    }

    public void showCardInfo(String cardId) {
        Card card = returnCardByIdInGame(cardId);
        if (card != null) {
            view.showCardInGame(card);
        } else {
            view.noSuchCardInGame();
        }
    }

    private Card returnCardByIdInGame(String cardId) {
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            if (card.getCardIdInGame().equalsIgnoreCase(cardId)) {
                return card;
            }
        }
        for (Card card : Controller.enemyAccount.getMainDeck().getCards()) {
            if (card.getCardIdInGame().equalsIgnoreCase(cardId)) {
                return card;
            }
        }
        return null;
    }

    public void moveTo(Card card, int x, int y) {
        if (card.stunned) {
            view.cardIsStun();
            return;
        }
        if (distanceTooLong(card, x, y)) {
            view.invalidTarget();
            return;
        }
        if (x < 0 || x > 5 || y < 0 || y > 9) {
            view.invalidTarget();
            return;
        }
        Block block = map.getBlock(x, y);
        if (block.isEmpty()) {
            view.invalidTarget();
            return;
        }
        if (!enemyInWay(block, card)) {
            view.invalidTarget();
            return;
        }
        card.getCurrentBlock().card = null;
        card.getCurrentBlock().setEmpty(true);
        block.setEmpty(false);
        block.card = card;
        card.setCurrentBlock(block);
        view.cardMoved(card);
    }

    private boolean enemyInWay(Block block, Card card) {
        if (block.x == card.getCurrentBlock().x && block.y > card.getCurrentBlock().y) {
            Block checkBlock = map.getBlock(block.x, block.y + 1);
            return !checkBlock.isEmpty();
        }
        if (block.y == card.getCurrentBlock().y && block.x > card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x + 1, block.y);
            return !checkBlock.isEmpty();
        }
        if (block.x == card.getCurrentBlock().x && block.y < card.getCurrentBlock().y) {
            Block checkBlock = map.getBlock(block.x, block.y - 1);
            return !checkBlock.isEmpty();
        }
        if (block.y == card.getCurrentBlock().y && block.x < card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x - 1, block.y);
            return !checkBlock.isEmpty();
        }
        return true;
    }

    private boolean distanceTooLong(Card card, int x, int y) {
        return (Math.abs(card.getCurrentBlock().x - x) + Math.abs(card.getCurrentBlock().y - y)) <= 2;
    }


    public void attack(Card myCard, Card opponentCard) throws CloneNotSupportedException {
        if (!myCard.disarmed) {
            myCard.attack(opponentCard);
            if (whoseTurn().equals(Controller.currentAccount)) {
                player1Mp -= myCard.Mp;
            } else {
                player2Mp -= myCard.Mp;
            }
            return;
        }
        view.disarmedCard();
    }

    public void attackCombo(String opponentCardId, String... myCardIds) throws CloneNotSupportedException {
        Card enemyCard = returnCardByIdInGame(opponentCardId);
        if (enemyCard == null) {
            view.noSuchCardInGame();
            return;
        }
        Account otherAccount;
        if (Controller.currentAccount.getCardsInGame().contains(enemyCard)) {
            otherAccount = Controller.enemyAccount;
        } else if (Controller.enemyAccount.getCardsInGame().contains(enemyCard)) {
            otherAccount = Controller.currentAccount;
        } else {
            view.cardNotInGame();
            return;
        }
        int length = myCardIds.length;
        String[] myCardId = new String[length];
        Minion[] myCrads = new Minion[length];
        for (int i = 0; i < length; i++) {
            Card card = returnCardByIdInGame(myCardId[i]);
            if (card == null) {
                view.cardNotInGame();
                return;
            }
            if (!card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                view.wrongCardTypeForCombo();
                return;
            }
            Minion minion = (Minion) card;
            if (!minion.activationTime.equals(SpecialPowerActivation.combo.toString())) {
                view.notAComboMinion();
                return;
            }
            myCrads[i] = minion;
        }
        Minion.comboAttack(enemyCard, myCrads);
    }

    public void useSpecialPower(int x, int y) {
        Block block = map.getBlock(x, y);
        if (block == null) {
            view.invalidCoordinates();
            return;
        }
        if (currentCard.getTypeOfAttack().equals(TypeOfCard.Spell)) {
            view.noSpecialPower();
            return;
        }
        Card card = block.getCard();
        if (card == null) {
            view.invalidTarget();
            return;
        }
        if (currentCard.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            Minion minion = (Minion) currentCard;
            ArrayList<Card> cards = minion.cardsAttacked(map, block);
            for (Card attackCard : cards) {
                minion.specialPowerActing(attackCard);
            }
        }
        if (currentCard.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            Hero hero = (Hero) currentCard;
            ArrayList<Card> cards = hero.specialPowerActingOn(map, block);
            for (Card attackCard : cards) {
                hero.specialPowerActivation(attackCard);
            }
        }
    }

    public void showHand() {
        whoseTurn().getMainDeck().showHand();
    }

    public void endTurn() {
        turn++;
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            card.getCurrentBlock().blockEffect();
        }
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            card.getCurrentBlock().blockEffect();
        }
        if (whoseTurn().equals(Controller.currentAccount)) {
            Controller.currentAccount.myTurn = false;
            Controller.enemyAccount.myTurn = true;
        } else {
            Controller.currentAccount.myTurn = true;
            Controller.enemyAccount.myTurn = false;
        }
        for (Card card : whoseTurn().getCardsInGame()) {
            card.attackedThisTurn = false;
        }
        if (whoseTurn().getMainDeck() != null && whoseTurn().getMainDeck().getItem() != null) {
            if (whoseTurn().getMainDeck().getItem().getItemName().equalsIgnoreCase("KingWisdom")) {
                whoseTurn().getMainDeck().getItem().KingWisdom(whoseTurn().getUsername());
            }
            if (whoseTurn().getMainDeck().getItem().getItemName().equalsIgnoreCase("TajeDanaii")) {
                whoseTurn().getMainDeck().getItem().tajeDanaii(whoseTurn().getUsername());
            }
            if (whoseTurn().getMainDeck().getItem().getItemName().equalsIgnoreCase("ShamshireChini")) {
                whoseTurn().getMainDeck().getItem().shamshireChini(whoseTurn().getUsername());
            }
        }
        map.checkIfCollectibleOrFlagIsTaken();
        updateGraveYard();
        endGameSetter();
        addToMana();
    }

    private void buffEffect() throws CloneNotSupportedException {
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            for (Buff buff : card.getActivatedBuffs()) {
                buff.buffEffect(card);
            }
        }
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            for (Buff buff : card.getActivatedBuffs()) {
                buff.buffEffect(card);
            }
        }
    }

    private void addToMana() {
        if (whoseTurn().equals(Controller.currentAccount)) {
            if (turn < 14) {
                player1Mp = (turn / 2) + 2;
            } else {
                player1Mp = 9;
            }
        } else {
            if (turn < 14) {
                player2Mp = (turn / 2) + 2;
            } else {
                player2Mp = 9;
            }
        }
    }

    public void help() {
        view.showMyMinions();
        view.showEnemyMinion();
        whoseTurn().getMainDeck().showHand();
    }

    public void useItem(Item item, int x, int y) {
        ArrayList<Card> effecteds = item.itemEffectOnWhat(x, y);
        for (Card card : effecteds) {
            try {
                item.itemEffect(card);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCardsToGame(String cardName, int x, int y) {
        Card card = Card.returnCardByName(cardName);
        if (card == null || !whoseTurn().getMainDeck().hand.returnHand().contains(card)) {
            view.invalidCardNameInGame();
            return;
        }
        boolean canBeInserted = false;
        if (card.getTypeOfAttack().equals(TypeOfCard.Spell)) {
            Spell spell = (Spell) card;
            Block block = map.getBlock(x, y);
            if (block == null || block.isEmpty()) {
                view.invalidTarget();
                return;
            }
            canBeInserted = spell.checkEffectiveness(block.card);
        } else {
            canBeInserted = checkSurroundingBlocks(x, y, canBeInserted);
        }
        if (!canBeInserted) {
            view.invalidTarget();
            return;
        }
        Block block = map.getBlock(x, y);
        if (block == null || !block.isEmpty()) {
            view.invalidTarget();
            return;
        }
        boolean whichAccount = whoseTurn().equals(Controller.currentAccount);
        if (whichAccount) {
            if ((Controller.currentGame.player1Mp - card.Mp) < 0) {
                view.notEnoughMana();
                return;
            }
        } else {
            if ((Controller.currentGame.player2Mp - card.Mp) < 0) {
                view.notEnoughMana();
                return;
            }
        }
        if (whichAccount) {
            Controller.currentGame.reducePlayerOneMp(card.Mp);
        } else {
            Controller.currentGame.reducePlayerTwoMp(card.Mp);
        }
        cardsInGame.add(card);
        block.cardMovedToBlock(card);
        if (whoseTurn().equals(Controller.currentAccount)) {
            Controller.currentAccount.getMainDeck().hand.deleteFromHand(card);
            if (Controller.currentAccount.getMainDeck().getItem().getItemName().equals("ghosleTamid") && card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Item item = Controller.currentAccount.getMainDeck().getItem();
                try {
                    item.itemEffect(card);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            if (Controller.currentAccount.getMainDeck().getItem().getItemName().equals("assassinationDagger")) {
                Item item = Controller.currentAccount.getMainDeck().getItem();
                item.assassinationDaggerEffect();
            }
        } else {
            Controller.enemyAccount.getMainDeck().hand.deleteFromHand(card);
            if (Controller.currentAccount.getMainDeck().getItem().getItemName().equals("ghosleTamid") && card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Item item = Controller.currentAccount.getMainDeck().getItem();
                try {
                    item.itemEffect(card);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            if (Controller.currentAccount.getMainDeck().getItem().getItemName().equals("assassinationDagger")) {
                Item item = Controller.currentAccount.getMainDeck().getItem();
                item.assassinationDaggerEffect();
            }
        }
    }

    private boolean checkSurroundingBlocks(int x, int y, boolean canBeInserted) {
        Block surrondingBlock = map.getBlock(x - 1, y);
        if (surrondingBlock != null) {
            if (!surrondingBlock.isEmpty() && whoseTurn().getCardsInGame().contains(surrondingBlock.card)) {
                canBeInserted = true;
            }
        }
        if (!canBeInserted) {
            surrondingBlock = map.getBlock(x, y - 1);
            if (surrondingBlock != null) {
                if (!surrondingBlock.isEmpty() && !whoseTurn().getCardsInGame().contains(surrondingBlock.card)) {
                    canBeInserted = true;
                }
            }
        }
        if (!canBeInserted) {
            surrondingBlock = map.getBlock(x + 1, y);
            if (surrondingBlock != null) {
                if (surrondingBlock.isEmpty() && !whoseTurn().getCardsInGame().contains(surrondingBlock.card)) {
                    canBeInserted = true;
                }
            }
        }
        if (!canBeInserted) {
            surrondingBlock = map.getBlock(x, y + 1);
            if (surrondingBlock != null) {
                if (surrondingBlock.isEmpty() && !whoseTurn().getCardsInGame().contains(surrondingBlock.card)) {
                    canBeInserted = true;
                }
            }
        }
        return canBeInserted;
    }

    public void showCard(String cardIdInGame) {
        Card wantedCard = null;
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            if (card.getCardIdInGame().equals(cardIdInGame)) {
                wantedCard = card;
            }
        }
        if (wantedCard == null) {
            for (Card card : Controller.enemyAccount.getMainDeck().getCards()) {
                if (card.getCardIdInGame().equals(cardIdInGame)) {
                    wantedCard = card;
                }
            }
        }
        if (wantedCard == null) {
            view.noSuchCardInGame();
            return;
        }
        view.showCardInGame(wantedCard);
    }

    public Account getActiveAccount() {
        return this.whoseTurn();
    }

    public void showCollectibles() {
        for (Card card : whoseTurn().getCardsInGame()) {
            for (Collectible collectible : card.getCollectibles()) {
                view.showCollectible(collectible);
            }
        }
    }

    public int getPlayer1Mp() {
        return player1Mp;
    }

    public int getPlayer2Mp() {
        return player2Mp;
    }

    public ArrayList<Card> getCardsInGame() {
        return cardsInGame;
    }
}