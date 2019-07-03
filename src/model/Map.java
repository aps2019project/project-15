package model;

import controller.Controller;

import java.util.ArrayList;
import java.util.Random;

class Map {
    private ArrayList<Collectible> inGameCollectible;
    private Block[][] totalMap = new Block[5][9];

    Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                totalMap[i][j] = new Block(i, j);
            }
        }
        inGameCollectible = Collectible.getRandomCollectibles();
        setCollectibleBlocks();
    }

    private void setCollectibleBlocks() {
        Random random = new Random();
        for (Collectible collectible : inGameCollectible) {
            boolean foundBlock = false;
            while (!foundBlock) {
                int x = random.nextInt(5);
                int y = random.nextInt(9);
                Block block = this.getBlock(x, y);
                if (block.getCollectible() == null) {
                    block.setCollectible(collectible);
                    foundBlock = true;
                }
            }
        }
    }

    void addFlagToMap(Flag flag) {
        Random random = new Random();
        boolean blockFound = false;
        while (!blockFound) {
            int x = random.nextInt(5);
            int y = random.nextInt(9);
            Block block = totalMap[x][y];
            if (block.flag == null) {
                block.setFlag(flag);
                blockFound = true;
            }
        }
    }

    Block getBlock(int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 9) {
            return totalMap[x][y];
        }
        return null;
    }

    void checkIfCollectibleOrFlagIsTaken() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (totalMap[i][j].collectible != null && totalMap[i][j].card != null) {
                    totalMap[i][j].card.addToCollectibles(totalMap[i][j].collectible);
                    totalMap[i][j].moveCollectible();
                }
                if (totalMap[i][j].flag != null && totalMap[i][j].card != null) {
                    totalMap[i][j].flag.card = totalMap[i][j].card;
                    totalMap[i][j].flag.setStartTurn(Controller.currentGame.getTurn());
                    totalMap[i][j].removeFlag();
                }
            }
        }
    }
}
