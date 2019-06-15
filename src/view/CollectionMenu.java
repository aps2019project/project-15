package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class CollectionMenu {
    public HBox heroes;
    public HBox minions;
    public HBox spells;
    public HBox items;
    public Button mycards;
    public Button myitems;
    public Button mydecks;
    public GridPane cards;
    public GridPane myItems;
    public GridPane myDecks;
    public VBox deckNames;
    public VBox cardsInDeck;

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.CreateAccount.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void initialize() {
        myItems.setVisible(false);
        myDecks.setVisible(false);
        for (Card card : Controller.currentAccount.getMyCollection().myCards()) {
            switch (card.getTypeOfAttack()) {
                case Hero:
                    heroes.getChildren().add(cardInfo(card));
                    break;
                case Minion:
                    minions.getChildren().add(cardInfo(card));
                    break;
                case Spell:
                    spells.getChildren().add(cardInfo(card));
            }
        }
        for (Item item : Controller.currentAccount.getMyCollection().myItems()) {
            items.getChildren().add(itemInfo(item));
        }
        for (Deck deck : Controller.currentAccount.getMyCollection().myDecks()) {
            TextArea textArea = new TextArea(deck.getName());
            textArea.setPrefSize(230, 300);
            deckNames.getChildren().add(textArea);
            HBox deckContains = new HBox();
            for(Card card : deck.getCards()){
                deckContains.getChildren().add(cardInfo(card));
            }
            if(deck.getItem() != null){
                deckContains.getChildren().add(itemInfo(deck.getItem()));
            }
            cardsInDeck.getChildren().add(deckContains);
        }
    }
    private Pane itemInfo(Item item) {
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

    private Pane cardInfo(Card card) {
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
        cardBackground.setFitHeight(300.0);
        cardBackground.setFitWidth(230.0);
        text.setStyle("-fx-font-weight : bold ; -fx-font-size : 16");
        cardInfo.setAlignment(Pos.CENTER);
        cardInfo.getChildren().addAll(cardBackground, text);
        return cardInfo;
    }

    public void showCards(MouseEvent mouseEvent) {
        myItems.setVisible(false);
        myDecks.setVisible(false);
        cards.setVisible(true);
    }

    public void showItems(MouseEvent mouseEvent) {
        cards.setVisible(false);
        myDecks.setVisible(false);
        myItems.setVisible(true);
    }

    public void showDecks(MouseEvent mouseEvent) {
        cards.setVisible(false);
        myItems.setVisible(false);
        myDecks.setVisible(true);
    }
}
