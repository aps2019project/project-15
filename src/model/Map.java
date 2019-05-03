package model;

import java.util.ArrayList;

public class Map {
    int row = 5;
    int column = 9;
    private ArrayList<Block> blocks = new ArrayList<>();
    Block[][] totalMap = new Block[5][9];

    public Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                totalMap[i][j] = new Block(i, j);
            }
        }
    }

    private ArrayList<Collectible> inGameCollectible = new ArrayList<>();

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y) {
        if (x >= 0 && x < 5 && y >= 0 && y < 9) {
            return totalMap[x][y];
        }
        return null;
    }
}
