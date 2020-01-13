package BootlegElderScrolls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class GameOver {
    public static Scene gameOverScene(){
    
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20, 20, 20, 20));

        Label gameOverMessage = new Label("Game Over");
        gameOverMessage.setId("title-labels");
        Label bossesDefeated = new Label("You have defeated " + Player.getBossCount() + " bosses");
        Label scoreLabel = new Label("Final Score: " + Player.getScore());

        Button mainMenu = new Button("Main Menu");
        Button exit = new Button("Exit");

        mainMenu.setOnAction(e -> {
            MainMenu.getMainStage().close();
            boolean run = MainMenu.display();
            if(run){
                MainMenu.getMainStage().setScene(Battle.createHeroSelectionScene());
                MainMenu.getMainStage().show();
            }
        });

        exit.setOnAction(e -> {
            MainMenu.getMainStage().close();
        });

        vbox.getChildren().addAll(gameOverMessage, bossesDefeated, scoreLabel, mainMenu, exit);

        Scene gameOver = new Scene(vbox, 500, 350);
        gameOver.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        Player.reset();
        Game.reset();
        return gameOver;
    }
}