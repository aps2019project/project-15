package view;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class singlePlayerBattleOptions {
    public TextArea number;
    public Button submit;

    public void collectFlags(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(3);
        number.setVisible(true);
        submit.setVisible(true);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (number.getText().equalsIgnoreCase("") || number.getText().equalsIgnoreCase("0")
                        || !number.getText().matches("[\\d]+")) {
                    View.getInstance().flagsNumber();
                } else {
                    Controller.currentGame.addFlagsToGame(Integer.parseInt(number.getText()));
                    goToBattlePage();
                }
            }
        });
    }

    public void getFlag(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(2);
        goToBattlePage();
    }

    public void killOpponent(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(1);
        goToBattlePage();
    }

    public void storyMode(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(0);
        goToBattlePage();
    }

    private void goToBattlePage() {
        Parent mainMenu = null;
        try {
            mainMenu = FXMLLoader.load(view.BattleMap1.class.getResource("BattleMap1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        if (mainMenu != null) {
            primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        }
        primaryStage.show();
    }

    public void exit(MouseEvent mouseEvent) {
        Parent battleMenu = null;
        try {
            battleMenu = FXMLLoader.load(view.BattleMenu.class.getResource("BattleMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Battle menu");
        if (battleMenu != null) {
            primaryStage.setScene(new Scene(battleMenu, 3000, 1000));
            primaryStage.show();
        }
    }
}
