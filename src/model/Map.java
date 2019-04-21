package model;

import java.util.ArrayList;

public class Map {
    int row = 5;
    int column = 9;
    private ArrayList<Block> blocks = new ArrayList<>();
    Block[][] totalMap = new Block[5][9];
    private ArrayList<Collectible> inGameCollectible = new ArrayList<>();

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Block getBlock(int x, int y) {
    return null;
    }
}
