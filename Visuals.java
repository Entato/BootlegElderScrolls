package BootlegElderScrolls;

import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.shape.Rectangle;
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
        double minX1 = attacker.getSpriteView().localToScene(attacker.getSpriteView().getParent().getBoundsInLocal()).getMinX();
        double minX2 = defender.getSpriteView().localToScene(defender.getSpriteView().getParent().getBoundsInLocal()).getMinX();

        double minY1 = attacker.getSpriteView().localToScene(attacker.getSpriteView().getParent().getBoundsInLocal()).getMinY();
        double minY2 = defender.getSpriteView().localToScene(defender.getSpriteView().getParent().getBoundsInLocal()).getMinY();


        TranslateTransition attack1 = new TranslateTransition(Duration.millis(3000), attacker.getSpriteView().getParent());
        System.out.println("Defender x: " + minX2 + "defender y: " + minY2);
        //attack1.setFromX(minX1);
        //attack1.setFromY(minY1);
        attack1.setToX(minX2);
        attack1.setToX(minY2);

        attack1.setAutoReverse(true);
        attack1.setCycleCount(2);


        //attack1.setOnFinished(e ->);

        attack1.play();
    }




}
