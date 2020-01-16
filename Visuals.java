package BootlegElderScrolls;

import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.geometry.Point2D;
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
    private static ArrayList<SequentialTransition> sequences = new ArrayList<SequentialTransition>();
    private static ArrayList<Integer> damages = new ArrayList<Integer>();
    private static ArrayList<ArrayList<Hero>> recipients = new ArrayList<ArrayList<Hero>>();
    private static ArrayList<Boolean> death = new ArrayList<Boolean>();

    //getters so I can access them and use their methods
    public static ArrayList<Image> getSprites() {
        return sprites;
    }
    public static ArrayList<Integer> getDamages(){
        return damages;
    }
    public static ArrayList<ArrayList<Hero>> getRecipients(){
        return recipients;
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

    public static ArrayList<SequentialTransition> getSequences() {
        return sequences;
    }
    public static ArrayList<Boolean> getDeath() {
        return death;
    }

    //initialize sprites
    public static void initializeSprites() throws IOException {
        for(int i = 0; i < 5; i++){
            sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_" + i + ".png")));
        }
        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_grunt.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_baron.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_reaper.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_icegiant.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_ogre.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_keeper.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_haunted.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_colossus.png")));

        sprites.add(new Image(new FileInputStream("BootlegElderScrolls/Assets/sprite_timelord.png")));



    }
    private static void flipEnemySprites(ImageView imageView) {
        imageView.setScaleX(-1);
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


                Tooltip tooltip = new Tooltip("Name : " + Player.getPlayerTeam().get(i).getName() +
                        "\nStats:\nStarting Health: " + Player.getPlayerTeam().get(i).getHealth() +
                        "\nStarting Attack: " + Player.getPlayerTeam().get(i).getAttack() +
                        "\nSpeed: " + Player.getPlayerTeam().get(i).getSpeed() + "\nSpecial Available: " +
                        Player.getPlayerTeam().get(i).getActiveSpecial() + "\nEvasion: " + Game.getTeam2().get(i).getEvasion() +
                        "\nLevel: " + Player.getPlayerTeam().get(i).getLevel());
                Tooltip.install(Player.getPlayerTeam().get(i).getSpriteView(), tooltip);
            }
            else{

                Image image = team2Sprites.get(i - 3);
                Game.getTeam2().get(i - 3).getSpriteView().setImage(image);
                flipEnemySprites(Game.getTeam2().get(i - 3).getSpriteView());
                Game.getTeam2().get(i - 3).getSpriteView().setFitHeight(110);
                Game.getTeam2().get(i - 3).getSpriteView().setFitWidth(100);
                Game.getTeam2().get(i - 3).getSpriteView().setPreserveRatio(true);
                roots.get(i).getChildren().setAll(Game.getTeam2().get(i - 3).getSpriteView());

                Tooltip tooltip = new Tooltip("Name : " + Game.getTeam2().get(i - 3).getName() +
                        "\nStats:\nStarting Health: " + Game.getTeam2().get(i - 3).getHealth() +
                        "\nStarting Attack: " + Player.getPlayerTeam().get(i - 3).getAttack() +
                        "\nSpeed: " + Game.getTeam2().get(i - 3).getSpeed() + "\nEvasion: " + Game.getTeam2().get(i - 3).getEvasion());
                Tooltip.install(Game.getTeam2().get(i - 3).getSpriteView(), tooltip);

            }



            if(i < 3){
                sprite1Box.getChildren().add(roots.get(i));
            }
            else{
                sprite2Box.getChildren().add(roots.get(i));
            }



        }

    }

    //configures sequence of animations
    public static void attackAnimation(Hero attacker, Hero defender, int damage){

        double nodeMinX2 = defender.getSpriteView().getLayoutBounds().getMinX();
        double nodeMinY2 = defender.getSpriteView().getLayoutBounds().getMinY();

        double nodeMinX1 = attacker.getSpriteView().getLayoutBounds().getMinX();
        double nodeMinY1 = attacker.getSpriteView().getLayoutBounds().getMinY();

        //coordinates for attacker and defender
        Point2D points1 = attacker.getSpriteView().localToScene(nodeMinX1, nodeMinY1);
        Point2D points2 = defender.getSpriteView().localToScene(nodeMinX2, nodeMinY2);

        TranslateTransition attack1 = new TranslateTransition(Duration.millis(400), attacker.getSpriteView().getParent());
        System.out.println("Distance x: " + (points2.getX() - points1.getX()) + "distance y: " + (points2.getY() - points1.getY()));
        //attack1.setFromX(minX1);
        //attack1.setFromY(minY1);
        attack1.setToX(points2.getX() - points1.getX());
        attack1.setToY(points2.getY() - points1.getY());

        attack1.setAutoReverse(true);
        attack1.setCycleCount(2);

        PauseTransition pauseTransition1 = new PauseTransition(Duration.millis(200));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.millis(200));

        SequentialTransition sequence = new SequentialTransition(pauseTransition1, attack1, pauseTransition2);

        sequences.add(sequence);
        ArrayList<Hero> attackerDefender = new ArrayList<Hero>();
        attackerDefender.add(attacker);
        attackerDefender.add(defender);
        recipients.add(attackerDefender);


        damages.add(damage);



    }

    //plays animations
    public static void playAnimationSet() {
        //disable buttons

        //if no animations to play, check for end/go to next turn
        if(sequences.size() < 1){
            Battle.nextTurn();
            Battle.checkForEnd();
            return;
        }
        Battle.disableInteractions();

        SequentialTransition attackTransition = new SequentialTransition();
        //needed array since lambda expressions need final values
        final boolean[] problem = {false};
        PauseTransition[] updates = new PauseTransition[sequences.size()];

        //I'm doing this because the lambda expression needs a constant variable
        final int[] counter  = {0};
        for(int i = 0; i < updates.length; i++){

            updates[i] = new PauseTransition(Duration.millis(1));
            updates[i].setOnFinished(e -> {

                if(problem[0]){
                    return;
                }
                System.out.println("ANIMATION ENTERED________________");
                try {


                    if(recipients.get(counter[0]).get(1).getHealth() <= 0){
                        Game.getBattleLog().getItems().add(recipients.get(counter[0]).get(0).getName() +
                                " 's Attack Was Wasted on a Dead Body");
                    }

                    else {
                        //if damage exceeds health, just does enough damage to kill
                        if (recipients.get(counter[0]).get(1).getHealth() - damages.get(counter[0]) < 0) {
                            damages.set(counter[0], recipients.get(counter[0]).get(1).getHealth());
                        }
                        //deal damage
                        recipients.get(counter[0]).get(1).setHealth(recipients.get(counter[0]).get(1).getHealth() - damages.get(counter[0]));

                        //adds damage to total damage if AI team is being damaged
                        if (Game.getTeam2().contains(recipients.get(counter[0]).get(1))){
                            Player.totalDamageAdd(damages.get(counter[0]));
                        }


                        //battle log message
                        Game.getBattleLog().getItems().add(recipients.get(counter[0]).get(0).getName() + " attacked " +
                                recipients.get(counter[0]).get(1).getName() + " for " + damages.get(counter[0]) + " damage.");

                        //check if dead
                        if (recipients.get(counter[0]).get(1).getHealth() <= 0) {
                            Game.getBattleLog().getItems().add(recipients.get(counter[0]).get(1).getName() + " Has Died in Battle!");

                        }
                        //health bar update
                        recipients.get(counter[0]).get(1).updateHealthBar();
                    }


                    System.out.println("Counter:" + counter[0]);
                    counter[0]++;

                    //only check for end after all animations
                    if(counter[0] == updates.length){
                        //ensure this does not get called again
                        Battle.getFlowPane().setDisable(false);
                        counter[0] = 500;
                        Battle.nextTurn();
                        Battle.checkForEnd();
                        problem[0] = true;
                    }

                }
                catch(Exception error) {
                    System.err.println(error);
                    problem[0] = true;
                }



            });
            if(problem[0]){
                return;
            }
        }

        for(int i = 0; i < sequences.size(); i++){
            attackTransition.getChildren().addAll(sequences.get(i), updates[i]);
        }





        attackTransition.play();


    }



}