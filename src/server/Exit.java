package server;

import com.google.gson.Gson;
import controller.Controller;
import menu.BattleMenu;
import menu.CollectionMenu;
import menu.MainMenu;
import menu.ShopMenu;

public class Exit implements CheckAccuracy{
    String message;
    public Exit(String message){
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if(this.message.equals("Exit")){
            if(Controller.currentMenu.getClass().equals(BattleMenu.class) || Controller.currentMenu.getClass().equals(ShopMenu.class)
            || Controller.currentMenu.getClass().equals(CollectionMenu.class)){
                Controller.currentMenu = new MainMenu();
                return new Gson().toJson("MainMenu");
            }
            if(Controller.currentMenu.getClass().equals(MainMenu.class)){
                return new Gson().toJson("EndDisplay");
            }
        }
        return new Gson().toJson("false");
    }
}
