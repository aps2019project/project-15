package model;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    int row = 5;
    int column = 9;
    private ArrayList<Block> blocks = new ArrayList<>();
    private ArrayList<Collectible> inGameCollectible = new ArrayList<>();
    Block[][] totalMap = new Block[5][9];

    public Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                totalMap[i][j] = new Block(i, j);
            }
        }
        inGameCollectible = Collectible.getRandomCollectibles();
        setCollectibleBlocks();
    }
    private void setCollectibleBlocks(){
        Random random = new Random();
        for(Collectible collectible : inGameCollectible){
            boolean foundBlock = false;
            while (!foundBlock){
                int x = random.nextInt(5);
                int y = random.nextInt(9);
                Block block = this.getBlock(x , y);
                if(block.getCollectible() == null){
                    block.setCollectible(collectible);
                    foundBlock = true;
                }
            }
        }
    }


    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 9) {
            return totalMap[x][y];
        }
        return null;
    }
    public void checkIfCollectibleIsTaken(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(totalMap[i][j].collectible != null && totalMap[i][j].card != null){
                    totalMap[i][j].card.addToCollectibles(totalMap[i][j].collectible);
                    //todo add item effect
                    totalMap[i][j].moveCollectible();
                }
            }
        }
    }
}
