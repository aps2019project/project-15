package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class BattleMap1 extends Application {

    private int counter = 1;

    public Pane block00;
    public Pane block01;
    public Pane block02;
    public Pane block03;
    public Pane block04;
    public Pane block05;
    public Pane block06;

    public void changeColor(MouseEvent mouseEvent) {

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
