package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void changeColor(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.RED);
            block00.setEffect(dropShadow);
        }
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
            block00.setEffect(null);
        }
    }

    public void setCurrentPaneEntered(MouseEvent mouseEvent) {

    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.CreateAccount.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void endTurn(MouseEvent mouseEvent) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        ImageView imageView = new ImageView();
        Image image = new Image("/unit_gifs/boss_andromeda_breathing.gif");
        imageView.setImage(image);

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                imageView.setLayoutX(counter);
                imageView.setLayoutY(100);
                System.out.println(counter);
                counter++;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1, 100);
        root.getChildren().addAll(imageView);

        Scene scene = new Scene(root, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
