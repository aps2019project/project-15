package model;

import java.util.ArrayList;

public class Usable extends Item {

    private static ArrayList<Usable> usables = new ArrayList<>();

    public static ArrayList<Usable> getUsables() {
        return usables;
    }

}
