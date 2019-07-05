package view;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Account;
import model.Card;
import model.Game;
import model.Hand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class BattleMap1 extends Application {

    public GridPane gridPane;
    public ImageView firstCard;
    public ImageView secondCard;
    public ImageView thirdCard;
    public ImageView fourthCard;
    public ImageView fifthCard;
    public ImageView sixthCard;
    public Label firstCardName;
    public Label secondCardName;
    public Label thirdCardName;
    public Label fourthCardName;
    public Label fifthCardName;
    public Label sixthCardName;

    private int counter = 1;

    public Pane block00;
    public Pane block01;
    public Pane block02;
    public Pane block03;
    public Pane block04;
    public Pane block05;
    public Pane block06;
    public Pane block07;
    public Pane block08;
    public Pane block10;
    public Pane block11;
    public Pane block12;
    public Pane block13;
    public Pane block14;
    public Pane block15;
    public Pane currentPaneEntered;

    public void initialize() {
        cardsInHand();
        for (Node pane : gridPane.getChildren()) {
            pane.setOnMouseEntered(event -> {
                DropShadow dropShadow = new DropShadow();
                dropShadow.setColor(Color.RED);
                pane.setEffect(dropShadow);
            });
            pane.setOnMouseExited(event -> {
                pane.setEffect(null);
            });
        }
    }

    private void cardsInHand() {
        ArrayList<Card> cardsInHand;
        cardsInHand = Controller.currentGame.getActiveAccount().getMainDeck().getHand().returnHand();
       firstCardName.setText(cardsInHand.get(0).getName());
       secondCardName.setText(cardsInHand.get(1).getName());
      thirdCardName.setText(cardsInHand.get(2).getName());
     fourthCardName.setText(cardsInHand.get(3).getName());
      fifthCardName.setText(cardsInHand.get(4).getName());
      sixthCardName.setText(cardsInHand.get(5).getName());
//
        Image image0 = new Image("/unit_gifs/"+cardsInHand.get(0).getName()+".gif");
        Image image1 = new Image("/unit_gifs/"+cardsInHand.get(1).getName()+".gif");
        Image image2= new Image("/unit_gifs/"+cardsInHand.get(2).getName()+".gif");
        Image image3 = new Image("/unit_gifs/"+cardsInHand.get(3).getName()+".gif");
        Image image4= new Image("/unit_gifs/"+cardsInHand.get(4).getName()+".gif");
        Image image5 = new Image("/unit_gifs/"+cardsInHand.get(5).getName()+".gif");
        firstCard.setImage(image0);
        secondCard.setImage(image1);
        thirdCard.setImage(image2);
        fourthCard.setImage(image3);
        sixthCard.setImage(image5);
        fifthCard.setImage(image4);

    }


    public void setCurrentPaneEntered(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        backToMainMenu();
    }

    static void backToMainMenu() throws IOException {
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void endTurn(MouseEvent mouseEvent) {
        Controller.currentGame.endTurn();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Switch turn");
        alert.setContentText("Enemy turn!");
        alert.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
//        ImageView imageView = new ImageView();
//        Image image = new Image("/unit_gifs/boss_andromeda_breathing.gif");
//        imageView.setImage(image);
//
//        Timer timer = new Timer();
//
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                imageView.setLayoutX(counter);
//                imageView.setLayoutY(100);
//                System.out.println(counter);
//                counter++;
//            }
//        };
//        timer.scheduleAtFixedRate(timerTask, 1, 100);
//        root.getChildren().addAll(imageView);
        Scene scene = new Scene(root, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
