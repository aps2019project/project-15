package model;

import controller.Controller;

public class Flag extends Item {

    private Block currentBlock;
    private static final int neededTurns = 6;
    Card card;
    private int startTurn;

    public void setBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public Card getCard() {
        return card;
    }

    void removeCard() {
        this.card = null;
    }

    void setCurrentBlock(Block block) {
        this.currentBlock = block;
    }

    void setStartTurn(int startTurn) {
        this.startTurn = startTurn;
    }

    boolean singleFlagModeGameWon() {
        return this.startTurn + neededTurns <= Controller.currentGame.getTurn();
    }
}
