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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CollectionMenu implements Info {
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
    Map<String, HBox> decks = new HashMap<>();
    Map<String, Pane> nameOfDeck = new HashMap<>();

    public void exit(MouseEvent mouseEvent) throws IOException {
        BattleMap1.backToMainMenu();
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
            HBox deckContains = new HBox();
            deckNames.getChildren().addAll(deckInfo(deck));
            cardsInDeck.getChildren().add(deckContains);
            decks.put(deck.getName(), deckContains);
            for (Card card : deck.getCards()) {
                deckContains.getChildren().add(cardInfo(card));
            }
            if (deck.getItem() != null) {
                deckContains.getChildren().add(itemInfo(deck.getItem()));
            }
        }
    }


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

    private Pane deckInfo(Deck deck) {
        Text text = new Text(deck.getName());
        text.setStyle("-fx-background-color : bisque ; -fx-font-weight: bolder");
        text.setFont(Font.font(35));
        StackPane deckInfo = new StackPane();
        ImageView background = new ImageView();
        Image image = new Image("/card_backgrounds/neutral_prismatic_artifact@2x.png");
        background.setImage(image);
        background.setFitHeight(340);
        background.setFitWidth(250);
        background.setOpacity(0.68);
        deckInfo.getChildren().addAll(background, text);
        deckInfo.setAlignment(Pos.CENTER);
        nameOfDeck.put(deck.getName(), deckInfo);
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
            if (Controller.currentAccount.getMyCollection().createDeck(entry.getText())) {
                Deck deck = Deck.returnDeckByName(entry.getText());
                if (deck != null) {
                    HBox hBox = new HBox();
                    deckNames.getChildren().addAll(deckInfo(deck));
                    cardsInDeck.getChildren().add(hBox);
                    decks.put(deck.getName(), hBox);
                }
            }
        }
    }

    public void deleteDeck(MouseEvent mouseEvent) {
        if (entryCheck()) {
            if (Controller.currentAccount.getMyCollection().deleteDeck(entry.getText())) {
                deckNames.getChildren().remove(nameOfDeck.get(entry.getText()));
                cardsInDeck.getChildren().remove(decks.get(entry.getText()));
                nameOfDeck.remove(entry.getText());
                decks.remove(entry.getText());
            }
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
                        if (Controller.currentAccount.getMyCollection().cardOrItemToDeck(entry.getText(), DeckName.getText())) {
                            HBox hBox = decks.get(DeckName.getText());
                            Card card = Card.returnCardById(entry.getText());
                            Item item = Item.getItemById(entry.getText());
                            if (hBox != null) {
                                if (card != null) {
                                    hBox.getChildren().add(cardInfo(card));
                                } else if (item != null) {
                                    hBox.getChildren().add(itemInfo(item));
                                }
                            }
                        }
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
                        if (Controller.currentAccount.getMyCollection().removeCardOrItemFromDeck(entry.getText(), DeckName.getText())) {
                            HBox hBox = decks.get(DeckName.getText());
                            Card card = Card.returnCardById(entry.getText());
                            Item item = Item.getItemById(entry.getText());
                            if (hBox != null) {
                                if (card != null) {
                                    hBox.getChildren().remove(cardInfo(card));
                                } else if (item != null) {
                                    hBox.getChildren().remove(itemInfo(item));
                                }
                            }
                        }
                    }
                });
            } else {
                View.getInstance().enterCardId();
            }
        }
    }

    public void goToDeck(MouseEvent mouseEvent) throws IOException {
        if (entryCheck()) {
            for (Deck deck : Controller.currentAccount.getMyCollection().myDecks()) {
                if (deck.getName().equalsIgnoreCase(entry.getText())) {
                    DeckDetails.deck = deck;
                    Parent deckDetail = FXMLLoader.load(view.DeckDetails.class.getResource("DeckDetails.fxml"));
                    Stage primaryStage = UI.getInstance().getPrimaryStage();
                    primaryStage.setTitle("Deck");
                    primaryStage.setScene(new Scene(deckDetail, 3000, 1000));
                    primaryStage.show();
                    return;
                }
            }
            View.getInstance().notValidDeck();
        }
    }
}
