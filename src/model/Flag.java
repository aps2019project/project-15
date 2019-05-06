package model;

import controller.Controller;

public class Flag extends Item {

    private Block currentBlock;
    public static final int neededTurns = 6;
    Card card;
    private int startTurn;

    public Card condition() {
        return null;
    }

    public Flag(Block currentBlock) {
        this.currentBlock = currentBlock;
    }


    public Flag() {

    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public Card getCard() {
        return card;
    }

    public void setCurrentBlock(Block block) {
        this.currentBlock = block;
    }

    public void setStartTurn(int startTurn) {
        this.startTurn = startTurn;
    }

    public int getStartTurn() {
        return startTurn;
    }
    public boolean singleFlagModeGameWon(){
        return this.startTurn + neededTurns <= Controller.currentGame.getTurn();
    }
}
