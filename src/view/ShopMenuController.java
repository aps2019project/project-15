package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class ShopMenuController {
    public HBox hero;
    public HBox minion;
    public HBox spell;
    public TextArea cardName;

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }
    public void initialize(){
        cardName.setBackground(Background.EMPTY);
        for(Card card : Shop.getInstance().getAllHeroes()){
            hero.getChildren().add(cardInfo(card));
        }
        for(Card card : Shop.getInstance().getAllMinions()){
            minion.getChildren().add(cardInfo(card));
        }
        for(Card card : Shop.getInstance().getAllSpells()){
            spell.getChildren().add(cardInfo(card));
        }
    }
    private Pane cardInfo(Card card){
        StackPane cardInfo = new StackPane();
        ImageView cardBackground = new ImageView();
        Text text = new Text();
        switch (card.getTypeOfAttack()){
            case Spell:
                Image image = new Image("card_backgrounds/card_back_agenor@2x.png");
                cardBackground.setImage(image);
                Spell spell = (Spell) card;
                text.setText(spell.toString());
                break;
            case Minion:
                Image image1 = new Image("card_backgrounds/card_back_shimzar@2x.png");
                cardBackground.setImage(image1);
                Minion minion = (Minion) card;
                text.setText(minion.toString());
                break;
            case Hero:
                Image image2 = new Image("card_backgrounds/card_back_lyonar_gears@2x.png");
                cardBackground.setImage(image2);
                Hero hero = (Hero) card;
                text.setText(hero.toString());
        }
        cardBackground.setOpacity(0.6);
        cardBackground.setFitHeight(300.0);
        cardBackground.setFitWidth(230.0);
        text.setStyle("-fx-font-weight : bold ; -fx-font-size : 16");
        cardInfo.setAlignment(Pos.CENTER);
        cardInfo.getChildren().addAll(cardBackground, text);
        return cardInfo;
    }

    public void buy(MouseEvent mouseEvent) {
        Shop.getInstance().buy(cardName.getText());

    }

    public void sell(MouseEvent mouseEvent) {
        Shop.getInstance().sell(cardName.getText());
    }
    //public Pane itemInfo(Item item){

    //}
}
