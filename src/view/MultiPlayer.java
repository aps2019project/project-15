package view;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Account;
import model.DataCenter;

import java.io.IOException;

public class MultiPlayer {
    public TextArea username;
    public PasswordField password;
    public Label lable;
    public Button accept;
    public Button exit;
    public Button mode1;
    public Button mode2;
    public Button mode3;
    public TextArea number;
    public Button submit;

    private void goToBattlePage(){
        Parent mainMenu = null;
        try {
            mainMenu = FXMLLoader.load(BattleMap1.class.getResource("BattleMap1.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void killOpponent(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(1);
        goToBattlePage();
    }

    public void getFlag(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(2);
        goToBattlePage();
    }

    public void collectFlags(MouseEvent mouseEvent) {
        Controller.currentGame.setTypeOfGame(3);
        number.setVisible(true);
        submit.setVisible(true);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(number.getText().equalsIgnoreCase("") || number.getText().equalsIgnoreCase("0")
                        || !number.getText().matches("[\\d]+")){
                    View.getInstance().flagsNumber();
                }
                else {
                    Controller.currentGame.addFlagsToGame(Integer.parseInt(number.getText()));
                    goToBattlePage();
                }
            }
        });
    }

    public void login(MouseEvent mouseEvent) {
        if(username.getText().equals("")){
            View.getInstance().getSecondUsername();
        }
        else {
            Controller.enemyAccount = DataCenter.getInstance().getAccountByName(username.getText());
            if (Controller.enemyAccount == null) {
                View.getInstance().accountNotExists();
            } else {
                if(Controller.enemyAccount.getPassword().equals(password.getText())){
                    if (Controller.currentAccount.getMainDeck() == null || !Controller.currentAccount.getMainDeck().validated) {
                        View.getInstance().notValidDeck();
                    }
                    else {
                        lable.setVisible(false);
                        username.setVisible(false);
                        password.setVisible(false);
                        accept.setVisible(false);
                        exit.setVisible(false);
                        mode1.setVisible(true);
                        mode2.setVisible(true);
                        mode3.setVisible(true);
                    }
                }
                else {
                    View.getInstance().incorrectPassword();
                }
            }
        }
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent battleMenu = FXMLLoader.load(view.BattleMenu.class.getResource("BattleMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Battle menu");
        primaryStage.setScene(new Scene(battleMenu, 3000, 1000));
        primaryStage.show();
    }
}
