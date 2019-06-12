package view;

import javafx.stage.Stage;

public class UI {
    private static UI ui = new UI();
    private Stage primaryStage;
    private UI(){

    }
    public static UI getInstance(){
        return ui;
    }
    public static void setPrimaryStage(Stage primaryStage1){
        ui.primaryStage = primaryStage1;
    }
    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
