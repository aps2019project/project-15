package view;

import controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;
import model.Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class animationActivities {
//    ArrayList<Card> cardsInGame = Controller.currentGame.getCardsInGame();
    //public  show(cardsInGame){


    //}
    public void MoveCard(String id ,int x,int y,int nextx,int nexty){
        ImageView imageView = new ImageView();
        Image image = new Image("\\sample\\boss_borealjuggernaut_breathing.gif");
        imageView.setImage(image);;

        Timer timer = new Timer();
    if(x==nextx) {
          TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            imageView.setLayoutX(x);

            imageView.setLayoutY(y);
            System.out.println();

              }
    };

        timer.scheduleAtFixedRate(timerTask, 1, 100);
    }
    if(y==nexty){
             TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
                imageView.setLayoutX(x);
                imageView.setLayoutY(y);
                System.out.println();

        }
    };

    timer.scheduleAtFixedRate(timerTask, 1, 100);
    }
    }
}
