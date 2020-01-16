package BootlegElderScrolls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.io.*;

class GameOver {
    public static Scene gameOverScene(){
        try{
            IO.erase();
        } catch (IOException e){
            System.err.println(e);
        }
    
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
    public static Scene gameWonScene(){

        //saves after winning
        try{
            IO.save();
            IO.erase();
        } catch (IOException i){
            System.err.println(i);
        }

        VBox box = new VBox();

        Label victoryLabel = new Label("Victory!");
        victoryLabel.setId("title-labels");
        Label messageLabel = new Label("Your team of heroes has made it out of the dungeon. Congratulations!");
        Label statLabel = new Label("Stats");

        Label levelsLabel = new Label("Levels Cleared: " + Player.getBossCount());
        Label killsLabel = new Label("Total Kills: " + Player.getKills());
        Label scoreLabel = new Label("Score: " + Player.getScore());
        Label damageLabel = new Label("Total Damage: " + Player.getTotalDamage());
        Label highScoreLabel = new Label("Previous Highscore: N/A");
        try {
            highScoreLabel = new Label("Previous Highscore: " + IO.getHighScore());
        }
        catch(IOException e){
            System.err.println("IO error: " + e);
        }

        Button playAgain = new Button("Play Again");
        Button exit = new Button("Exit");

        playAgain.setOnAction(e-> {
            MainMenu.getMainStage().close();
            MainMenu.display();
        });

        exit.setOnAction(e-> {
            MainMenu.getMainStage().close();
        });

        box.getChildren().addAll(statLabel, levelsLabel, killsLabel, scoreLabel, damageLabel, highScoreLabel);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20, 20, 20, 20));

        Scene winnerScene = new Scene(box, 550, 350);
        winnerScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        return winnerScene;
    }
}