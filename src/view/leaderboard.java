package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Account;
import model.DataCenter;

import java.io.IOException;

public class leaderboard {
    @FXML
    private TextArea leaderBoard;

    public void setLeaderBoard() {
        int num = 1;
        StringBuilder show = new StringBuilder();
        for (Account account : DataCenter.getInstance().getAccounts().values()) {
            show.append(num + ". UserName : " + account.getUsername() + " Wins : " + account.getNumOfWins() + "\n");
            num++;
        }
        leaderBoard.setText(show.toString());
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent accountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Account Menu!");
        primaryStage.setScene(new Scene(accountMenu, 3000, 1000));
        primaryStage.show();
    }

    public void initialize() {
        this.setLeaderBoard();
        leaderBoard.setStyle("-fx-font-size : 23");
    }
}
