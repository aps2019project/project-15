package server;

import com.google.gson.Gson;
import controller.Controller;
import menu.CollectionMenu;
import menu.MainMenu;
import menu.ShopMenu;

public class Enter implements CheckAccuracy{
    String message;
    public Enter(String message){
        this.message = message;
    }
    @Override
    public String checkAccuracy() {
        if(Controller.currentMenu.getClass().equals(MainMenu.class)) {
            switch (this.message) {
                case "Enter Collection":
                    Controller.currentMenu = new CollectionMenu();
                    return new Gson().toJson("CollectionMenu");
                case "Enter Shop":
                    Controller.currentMenu = new ShopMenu();
                    return new Gson().toJson("ShopMenu");
                case "Enter Battle":
                    if(Controller.currentAccount.getMainDeck() != null && Controller.currentAccount.getMainDeck().validated){
                        return new Gson().toJson("BattleMenu");
                    }
            }
            return new Gson().toJson("false");
        }
        return new Gson().toJson("false");
    }
}
