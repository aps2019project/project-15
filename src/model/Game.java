package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Game {

    private Map map = new Map();
    private int currentTurn;
    private GameType gameType;

    private ModeOfGame Mode;
    GameType type;
    private ArrayList<Card> cardsInGame = new ArrayList<>();
    private ArrayList<Card> graveYard = new ArrayList<>();
    private int turn = (int) (Math.random() % 2 + 1);
    private int result;
    private int timeOfGame;
    public Card currentCard;
    public Item currentItem;
    private Account activeAccount;
    private int firstPlayerMP = 2;
    private int secondPlayerMP = 2;
    private View view = View.getInstance();

    public Map getMap() {
        return map;
    }

    private int player1Mp = setInitialPlayer1Mp();
    private int player2Mp = setInitialPlayer2Mp();
    private int firstTurn;
    boolean done = false;


    public void addToHistory() {
        History history = new History();
        history.result = result;
        history.time = timeOfGame;
        Controller.currentAccount.historyGames.add(history);
        Controller.enemyAccount.historyGames.add(history);
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
    }
    private void ghooleTakCheshm(Card card){
        if(card.getName().equalsIgnoreCase("GhooleTakCheshm")){
            Minion minion = (Minion) card;
            ArrayList<Card> cards = minion.cardsAttacked(map, minion.getCurrentBlock());
            for(Card attackedCard : cards){
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

    private boolean returnCondition() {
        return true;
    }

    public void endGame() {
        if (returnCondition()) {

        }
    }

    public void setTypeOfGame(int number) {
        switch (number) {
            case (1): {
                Mode = ModeOfGame.StoryMode;
                break;
            }
            case (2): {
                Mode = ModeOfGame.killOpponent;

                break;
            }
            case (3): {
                Mode = ModeOfGame.KeepFlag;
                setFlag();
                break;
            }
            case (4): {
                Mode = ModeOfGame.CollectFlags;
                setFlag();
                break;
            }
        }
    }

    private void setFlag() {

    }

    public void showNextCard() {

    }

    public int getTurn() {
        return turn;
    }

    public void switchTurn() {
        if (this.turn == 1) {
            player2Mp++;
            this.turn = 2;
        } else if (this.turn == 2) {
            player1Mp++;
            this.turn = 1;
        }
    }

    public void showMyMinions() {
        view.showMyMinions();
    }

    public void showOpponentMinions() {
        view.showEnemyMinion();
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

    public void select(String cardId) {
        Card card = returnCardByIdInGame(cardId);
        if (card == null) {
            view.invalidCardId();
            return;
        }
        currentCard = card;
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
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.y == card.getCurrentBlock().y && block.x > card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x + 1, block.y);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.x == card.getCurrentBlock().x && block.y < card.getCurrentBlock().y) {
            Block checkBlock = map.getBlock(block.x, block.y - 1);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.y == card.getCurrentBlock().y && block.x < card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x - 1, block.y);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        return true;
    }

    private boolean distanceTooLong(Card card, int x, int y) {
        return (Math.abs(card.getCurrentBlock().x - x) + Math.abs(card.getCurrentBlock().y - y)) <= 2;
    }


    public void attack(Card myCard, Card opponentCard) throws CloneNotSupportedException {
        if (!myCard.disarmed) {
            myCard.attack(opponentCard);
            if (activeAccount.equals(Controller.currentAccount)) {
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
        Block block = map.getBlock(x , y);
        if(block == null){
            view.invalidCoordinates();
            return;
        }
        if(currentCard.getTypeOfAttack().equals(TypeOfCard.Spell)){
            view.noSpecialPower();
            return;
        }
        Card card = block.getCard();
        if(card == null){
            view.invalidTarget();
            return;
        }
        if(currentCard.getTypeOfAttack().equals(TypeOfCard.Minion)){
            Minion minion = (Minion) currentCard;
            ArrayList<Card> cards = minion.cardsAttacked(map, block);
            for(Card attackCard : cards){
                minion.specialPowerActing(attackCard);
            }
        }
        if(currentCard.getTypeOfAttack().equals(TypeOfCard.Hero)){
            Hero hero = (Hero) currentCard;
            ArrayList<Card> cards = hero.specialPowerActingOn(map, block);
            for(Card attackCard : cards){
                hero.specialPowerActivation(attackCard);
            }
        }
    }

    public void showHand() {
        activeAccount.getMainDeck().showHand();
    }

    public void endTurn() {
        turn++;
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            card.getCurrentBlock().blockEffect();
        }
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            card.getCurrentBlock().blockEffect();
        }
        if (activeAccount.equals(Controller.currentAccount)) {
            activeAccount = Controller.enemyAccount;
        } else {
            activeAccount = Controller.currentAccount;
        }
        for (Card card : activeAccount.getCardsInGame()) {
            card.attackedThisTurn = false;
        }
        updateGraveYard();
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

    public void addToMana() {
        if (activeAccount.equals(Controller.currentAccount)) {
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
        activeAccount.getMainDeck().showHand();
    }

    public void addCardsToGame(String cardName, int x, int y) {
        Card card = Card.returnCardByName(cardName);
        if (card == null || !activeAccount.getMainDeck().hand.getCardsInHand().contains(card)) {
            view.invalidCardNameInGame();
            return;
        }
        boolean canBeEnserted = false;
        if (card.getTypeOfAttack().equals(TypeOfCard.Spell)) {
            Spell spell = (Spell) card;
            Block block = map.getBlock(x, y);
            if (block == null || block.isEmpty()) {
                view.invalidTarget();
                return;
            }
            canBeEnserted = spell.checkEffectiveness(block.card);
        } else {
            canBeEnserted = checkSurroundingBlocks(x, y, canBeEnserted);
        }
        if (!canBeEnserted) {
            view.invalidTarget();
            return;
        }
        Block block = map.getBlock(x, y);
        if (block == null || !block.isEmpty()) {
            view.invalidTarget();
            return;
        }
        boolean whichAccount = activeAccount.equals(Controller.currentAccount);
        if (whichAccount) {
            if ((Controller.currentAccount.game.player1Mp - card.Mp) < 0) {
                view.notEnoughMana();
                return;
            }
        } else {
            if ((Controller.currentAccount.game.player2Mp - card.Mp) < 0) {
                view.notEnoughMana();
                return;
            }
        }
        if (whichAccount) {
            Controller.currentAccount.game.reducePlayerOneMp(card.Mp);
        } else {
            Controller.currentAccount.game.reducePlayerTwoMp(card.Mp);
        }
        cardsInGame.add(card);
        if (activeAccount.equals(Controller.currentAccount)) {
            Controller.currentAccount.getMainDeck().hand.deleteFromHand(card);
        } else {
            Controller.enemyAccount.getMainDeck().hand.deleteFromHand(card);
        }
    }

    private boolean checkSurroundingBlocks(int x, int y, boolean canBeEnserted) {
        Block surrondingBlock = map.getBlock(x - 1, y);
        if (surrondingBlock != null) {
            if (!surrondingBlock.isEmpty() && activeAccount.getCardsInGame().contains(surrondingBlock.card)) {
                canBeEnserted = true;
            }
        }
        if (!canBeEnserted) {
            surrondingBlock = map.getBlock(x, y - 1);
            if (surrondingBlock != null) {
                if (!surrondingBlock.isEmpty() && !activeAccount.getCardsInGame().contains(surrondingBlock.card)) {
                    canBeEnserted = true;
                }
            }
        }
        if (!canBeEnserted) {
            surrondingBlock = map.getBlock(x + 1, y);
            if (surrondingBlock != null) {
                if (surrondingBlock.isEmpty() && !activeAccount.getCardsInGame().contains(surrondingBlock.card)) {
                    canBeEnserted = true;
                }
            }
        }
        if (!canBeEnserted) {
            surrondingBlock = map.getBlock(x, y + 1);
            if (surrondingBlock != null) {
                if (surrondingBlock.isEmpty() && !activeAccount.getCardsInGame().contains(surrondingBlock.card)) {
                    canBeEnserted = true;
                }
            }
        }
        return canBeEnserted;
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
        return this.activeAccount;
    }
}