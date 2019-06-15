package view;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class ShopMenuController {
    public HBox hero;
    public HBox minion;
    public HBox spell;
    public TextArea cardName;
    public AnchorPane shop;
    public GridPane showCardSection;
    public GridPane showItemSection;
    public HBox Item;

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void initialize() {
        showItemSection.setVisible(false);
        cardName.setBackground(Background.EMPTY);
        for (Card card : Shop.getInstance().getAllHeroes()) {
            hero.getChildren().add(cardInfo(card));
        }
        for (Card card : Shop.getInstance().getAllMinions()) {
            minion.getChildren().add(cardInfo(card));
        }
        for (Card card : Shop.getInstance().getAllSpells()) {
            spell.getChildren().add(cardInfo(card));
        }
        for(Item item : Shop.getInstance().getAllItems()){
            Item.getChildren().add(itemInfo(item));
        }
    }
    private Pane itemInfo(Item item){
        StackPane itemInfo = new StackPane();
        ImageView imageView = new ImageView();
        Image image = new Image("card_backgrounds/card_back_gauntlet.png");
        imageView.setImage(image);
        Text text = new Text();
        text.setText(item.toString());
        imageView.setOpacity(0.6);
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

    public void buy(MouseEvent mouseEvent) {
        if (Shop.getInstance().buy(cardName.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Bought!");
            alert.setContentText("Remaining money: " + Controller.currentAccount.getMoney());
            alert.show();
        }
    }

    public void sell(MouseEvent mouseEvent) {
        if (Shop.getInstance().sell(cardName.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sold!");
            alert.setContentText("Remaining money: " + Controller.currentAccount.getMoney());
            alert.show();
        }
    }

    public void search(MouseEvent mouseEvent) {
        Card card = Card.returnCardByName(cardName.getText());
        model.Item item = model.Item.getItemByName(cardName.getText());
        StackPane result = new StackPane();
        ImageView imageView = new ImageView();
        Image image = new Image("card_backgrounds/card_back_snowchaser@2x.png");
        imageView.setImage(image);
        Text text = new Text();
        text.setStyle("-fx-font-size : 16; -fx-font-weight : bold");
        Button button = new Button("return");
        button.setStyle("-fx-background-color : #1919FF; -fx-font-size : 16");
        StringBuilder info = new StringBuilder();
        if(card == null && item == null){
            View.getInstance().itemOrCardIsNotInShop();
            return;
        }
        else if(card != null){
            info.append("type : " + card.getTypeOfAttack() + "\n");
            info.append(card.toString());
        }
        else{
            info.append("type : Item\n");
            info.append(item.toString());
        }
        text.setText(info.toString());
        result.getChildren().addAll(imageView, text);
        result.relocate(800, 200);
        button.relocate(978, 800);
        button.setPrefSize(100, 50);
        shop.getChildren().addAll(result, button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shop.getChildren().removeAll(result, button);
            }
        });
        UI.getInstance().getPrimaryStage().show();
    }

    public void cardsDisplay(MouseEvent mouseEvent) {
        showItemSection.setVisible(false);
        showCardSection.setVisible(true);
    }

    public void itemDisplay(MouseEvent mouseEvent) {
        showCardSection.setVisible(false);
        showItemSection.setVisible(true);
    }

    //public Pane itemInfo(Item item){

    //}
}
