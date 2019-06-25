package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class singlePlayerBattleOptions {
    public TextArea number;
    public Button submit;

    public void collectFlags(MouseEvent mouseEvent) {
        number.setVisible(true);
        submit.setVisible(true);
    }

    public void getFlag(MouseEvent mouseEvent) {
    }

    public void killOpponent(MouseEvent mouseEvent) {
    }

    public void storyMode(MouseEvent mouseEvent) {
    }
    private void goToBattlePage(){

    }
}
