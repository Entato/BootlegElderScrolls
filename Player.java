package BootlegElderScrolls;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

class Player {
    private static ArrayList<Hero> playerTeam = new ArrayList<Hero>();
    private static ArrayList<ComboBox> heroSelections = new ArrayList<ComboBox>();
    private static ArrayList<TextField> heroNames = new ArrayList<TextField>();
    private static int score = 0;
    private static int bossCount = 0;
    private static int totalDamage = 0;
    private static int kills = 0;

    public static ArrayList<Hero> getPlayerTeam(){
        return playerTeam;
    }

    public static ArrayList<ComboBox> getHeroSelections(){
        return heroSelections;
    }

    public static ArrayList<TextField> getHeroNames(){
        return heroNames;
    }

    public static void scoreAdd (int score){
        Player.score += score;
    }

    public static void bossCountAdd(){
        bossCount++;
    }

    public static void totalDamageAdd(int damage){
        totalDamage += damage;
    }

    public static void killsAdd(){
        kills++;
    }

    public static int getScore(){
        return score;
    }

    public static int getBossCount(){
        return bossCount;
    }

    public static int getTotalDamage(){
        return totalDamage;
    }

    public static int getKills(){
        return kills;
    }

    //set specials back to true
    public static void resetSpecials(){
        for(Hero h : playerTeam){
            h.setActiveSpecial(true);
        }
    }

    //called upon starting new game without exiting gui
    public static void reset(){
        playerTeam.clear();
        heroSelections.clear();
        heroNames.clear();
        score = 0;
        bossCount = 0;
        totalDamage = 0;
        kills = 0;

    }
}