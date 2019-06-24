package view;

import controller.Controller;
import javafx.event.EventHandler;
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
    public TextArea entry;
    public TextArea DeckName;
    public Button submit;

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
            textArea.setStyle("-fx-background-color : bisque ");
            textArea.setPrefSize(250, 340);
            deckNames.getChildren().add(textArea);
            HBox deckContains = new HBox();
            for (Card card : deck.getCards()) {
                deckContains.getChildren().add(cardInfo(card));
            }
            if (deck.getItem() != null) {
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

    public Pane deckInfo(Deck deck) {
        assert deck != null;
        StackPane deckInfo = new StackPane();
        ImageView background = new ImageView();
        Image image = new Image("/card_backgrounds/neutral_prismatic_artifact@2x.png");
        background.setImage(image);
        background.setFitHeight(300);
        background.setFitWidth(230);
        deckInfo.setAlignment(Pos.CENTER);
        deckInfo.getChildren().add(background);
        return deckInfo;
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

    public void createDeck(MouseEvent mouseEvent) {
        if (entryCheck()) {
            Controller.currentAccount.getMyCollection().createDeck(entry.getText());
            Deck deck = Deck.returnDeckByName(entry.getText());
            assert deck != null;
            TextArea textArea = new TextArea(deck.getName());
            textArea.setStyle("-fx-background-color : bisque ; -fx-font-weight: bolder");
            textArea.setPrefSize(250, 340);
            Pane pane = deckInfo(deck);
            deckNames.getChildren().addAll(pane , textArea);
        }
    }

    public void deleteDeck(MouseEvent mouseEvent) {
        if (entryCheck()) {
            Controller.currentAccount.getMyCollection().deleteDeck(entry.getText());
        }
    }

    private boolean entryCheck() {
        if (entry.getText().equalsIgnoreCase("")) {
            View.getInstance().deckNameNotEntered();
            return false;
        }
        return true;
    }

    public void validate(MouseEvent mouseEvent) {
        if (entryCheck()) {
            Controller.currentAccount.getMyCollection().validateDeck(entry.getText());
        }
    }

    public void addToDeck(MouseEvent mouseEvent) {
        if (entryCheck()) {
            if (entry.getText().matches("[\\d]+")) {
                DeckName.setVisible(true);
                submit.setVisible(true);
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        DeckName.setVisible(false);
                        submit.setVisible(false);
                        Controller.currentAccount.getMyCollection().cardOrItemToDeck(entry.getText(), DeckName.getText());
                        HBox deckContains = new HBox();
                        Deck deck = Deck.returnDeckByName(DeckName.getText());
                        Card card = Card.returnCardByName(entry.getText());
                        Item item = Item.getItemByName(entry.getText());
                        assert deck != null;
                        assert deck.getCards() != null;
                        if (card != null) {
                            deckContains.getChildren().add(cardInfo(card));
                        } else if (item != null) {
                            if (deck.getItem() == null) {
                                deckContains.getChildren().add(itemInfo(item));
                            } else {
                                View.getInstance().deckHasItem();
                            }
                        }
                        cardsInDeck.getChildren().add(deckContains);
                    }
                });
            } else {
                View.getInstance().enterCardId();
            }
        }
    }

    public void selectDeck(MouseEvent mouseEvent) {
        try {
            Controller.currentAccount.getMyCollection().selectDeck(entry.getText());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void removeFromDeck(MouseEvent mouseEvent) {
        if (entryCheck()) {
            if (entry.getText().matches("[\\d]+")) {
                DeckName.setVisible(true);
                submit.setVisible(true);
                submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        DeckName.setVisible(false);
                        submit.setVisible(false);
                        Controller.currentAccount.getMyCollection().removeCardOrItemFromDeck(entry.getText(), DeckName.getText());
                    }
                });
            } else {
                View.getInstance().enterCardId();
            }
        }
    }

    public void goToDeck(MouseEvent mouseEvent) throws IOException {
        DeckDetails.deckName = entry.getText();
        Parent deckDetail = FXMLLoader.load(view.DeckDetails.class.getResource("DeckDetails.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Deck");
        primaryStage.setScene(new Scene(deckDetail, 3000, 1000));
        primaryStage.show();
    }
}
