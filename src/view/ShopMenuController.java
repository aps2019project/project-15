package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class ShopMenuController {
    public HBox hero;
    public HBox minion;
    public HBox spell;

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }
    public void initialize(){
        System.out.println("initializing");
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
        cardBackground.setOpacity(0.7);
        text.setStyle("-fx-font-weight : bold ; -fx-font-size : 16");
        cardInfo.setAlignment(Pos.CENTER);
        cardInfo.getChildren().addAll(cardBackground, text);
        return cardInfo;
    }
    //public Pane itemInfo(Item item){

    //}
}
