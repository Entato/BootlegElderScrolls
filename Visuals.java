package BootlegElderScrolls;

import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.layout.*;

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
    }

    //adds sprites to layout
    public static void placeSprites(){
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        ArrayList<Group> roots = new ArrayList<Group>();
        for(int i = 0; i < 6; i++){
            roots.add(new Group());
        }

        //3 has to be changed back to 6 after grunt sprites is finshed
        for(int i = 0; i < 3; i++){

            imageViews.add(new ImageView());
            if(i < 3){
                Image image = team1Sprites.get(i);
                imageViews.get(i).setImage(image);
            }
            else{
                Image image = team2Sprites.get(i - 3);
                imageViews.get(i).setImage(image);
            }
            //resizing
            imageViews.get(i).setFitHeight(110);
            imageViews.get(i).setFitWidth(100);
            imageViews.get(i).setPreserveRatio(true);
            //adding to layout
            roots.get(i).getChildren().setAll(imageViews.get(i));
            if(i < 3){
                sprite1Box.getChildren().add(roots.get(i));
            }
            else{
                sprite2Box.getChildren().add(roots.get(i));
            }
            System.out.println("IMGVIEW :" + imageViews.get(i).toString());


        }

    }


}
