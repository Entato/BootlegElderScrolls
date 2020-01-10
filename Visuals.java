package BootlegElderScrolls;

import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class Visuals {
    private static ArrayList<Image> sprites = new ArrayList<Image>();
    private static ArrayList<Image> team1Sprites = new ArrayList<Image>();
    private static ArrayList<Image> team2Sprites = new ArrayList<Image>();
    //layouts for sprites
    private static VBox sprite1Box = new VBox(50);
    private static VBox sprite2Box = new VBox(50);

    //getters so I can access them and use their methods
    public static ArrayList<Image> getSprites() {
        return sprites;
    }

    public static ArrayList<Image> getTeam1Sprites() {
        return team1Sprites;
    }

    public static ArrayList<Image> getTeam2Sprites() {
        return team2Sprites;
    }

    public static VBox getSprite2Box() {
        return sprite2Box;
    }

    public static VBox getSprite1Box() {
        return sprite1Box;
    }

    //initialize sprites
    public static void initializeSprites() throws IOException {
        for(int i = 0; i < 5; i++){
            sprites.add(new Image(new FileInputStream("src/BootlegElderScrolls/Assets/sprite_" + i + ".png")));
        }
        sprites.add(new Image(new FileInputStream("src/BootlegElderScrolls/Assets/sprite_grunt.png")));
    }

    //adds sprites to layout
    public static void placeSprites(){
        ArrayList<Group> roots = new ArrayList<Group>();

        for(int i = 0; i < 6; i++){
            roots.add(new Group());
        }

        //3 has to be changed back to 6 after grunt sprites is finished
        for(int i = 0; i < 6; i++){
            System.out.println(Game.getTeam2().size());
            if(i < 3){
                Image image = team1Sprites.get(i);
                Player.getPlayerTeam().get(i).getSpriteView().setImage(image);
                Player.getPlayerTeam().get(i).getSpriteView().setFitHeight(110);
                Player.getPlayerTeam().get(i).getSpriteView().setFitWidth(100);
                Player.getPlayerTeam().get(i).getSpriteView().setPreserveRatio(true);
                roots.get(i).getChildren().setAll(Player.getPlayerTeam().get(i).getSpriteView());
            }
            else{
                Image image = team2Sprites.get(i - 3);
                Game.getTeam2().get(i - 3).getSpriteView().setImage(image);
                Game.getTeam2().get(i - 3).getSpriteView().setFitHeight(110);
                Game.getTeam2().get(i - 3).getSpriteView().setFitWidth(100);
                Game.getTeam2().get(i - 3).getSpriteView().setPreserveRatio(true);
                roots.get(i).getChildren().setAll(Game.getTeam2().get(i - 3).getSpriteView());
            }

            if(i < 3){
                sprite1Box.getChildren().add(roots.get(i));
            }
            else{
                sprite2Box.getChildren().add(roots.get(i));
            }



        }

    }

    public static void attackAnimation(Hero attacker, Hero defender){
        TranslateTransition attack1 = new TranslateTransition(Duration.millis(1000), attacker.getSpriteView());
        System.out.println("Defender x: " + defender.getSpriteView().getLayoutX() + "defender y: " + defender.getSpriteView().getLayoutY());
        //attack1.setFromX(attacker.getSpriteView().getLayoutX());
        //attack1.setFromY(attacker.getSpriteView().getLayoutY());
        //attack1.setToX(defender.getSpriteView().getLayoutX());
        //attack1.setToY(defender.getSpriteView().getLayoutY());

        attack1.setByX(200);
        attack1.setCycleCount(4);
        attack1.setAutoReverse(true);

        attack1.play();


        attack1.play();
    }


}
