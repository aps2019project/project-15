package view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.*;

import java.io.IOException;
import java.util.ArrayList;

public class DeckDetails implements Info {


    public AnchorPane deckShow;
    public VBox cardsAndItems;
    public static Deck deck = new Deck("");

    public void initialize() {
        ArrayList<Pane> deckContains = new ArrayList<>();
        for (Card card : deck.getCards()) {
            deckContains.add(cardInfo(card));
        }
        if (deck.getItem() != null) {
            deckContains.add(itemInfo(deck.getItem()));
        }
        int i = 1;
        HBox hBox = new HBox();
        for (Pane pane : deckContains) {
            if (i % 5 != 0) {
                hBox.getChildren().add(pane);
                if (i == deckContains.size()) {
                    cardsAndItems.getChildren().add(hBox);
                }
            } else {
                hBox.getChildren().add(pane);
                cardsAndItems.getChildren().add(hBox);
                hBox = new HBox();
            }
            i++;
        }
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent CollectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Collection menu");
        primaryStage.setScene(new Scene(CollectionMenu, 3000, 1000));
        primaryStage.show();
    }

    @Override
    public Pane cardInfo(Card card) {
        StackPane cardInfo = new StackPane();
        ImageView cardBackground = new ImageView();
        Text text = new Text();
        switch (card.getTypeOfAttack()) {
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
        cardBackground.setFitHeight(340.0);
        cardBackground.setFitWidth(250.0);
        text.setStyle("-fx-font-weight : bold ; -fx-font-size : 16");
        cardInfo.setAlignment(Pos.CENTER);
        cardInfo.getChildren().addAll(cardBackground, text);
        return cardInfo;
    }

    @Override
    public Pane itemInfo(Item item) {
        StackPane itemInfo = new StackPane();
        ImageView imageView = new ImageView();
        Image image = new Image("card_backgrounds/card_back_gauntlet.png");
        imageView.setImage(image);
        Text text = new Text();
        text.setText(item.toString());
        imageView.setOpacity(0.5);
        text.setStyle("-fx-font-weight : bold ; -fx-font-size : 16");
        imageView.setFitHeight(340.0);
        imageView.setFitWidth(250.0);
        itemInfo.setAlignment(Pos.CENTER);
        itemInfo.getChildren().addAll(imageView, text);
        return itemInfo;
    }
}
