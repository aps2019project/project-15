package model;

public class Flag extends Item {
//    private int x;
//    private int y;
    private Block currentBlock;

    Card card;

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
    public void setCurrentBlock(Block block){
        this.currentBlock = block;
    }



}
