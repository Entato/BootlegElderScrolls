package BootlegElderScrolls;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class GameOver {
    public static Scene gameOverScene(){
        VBox vbox = new VBox();

        Label gameOverMessage = new Label("Game Over");
        Label bossesDefeated = new Label("You have defeated " + Player.getBossCount() + " bosses");

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

        vbox.getChildren().addAll(gameOverMessage, bossesDefeated, mainMenu, exit);

        Scene gameOver = new Scene(vbox);
        return gameOver;
    }
}