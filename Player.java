package BootlegElderScrolls;

import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

class Player {
    private static ArrayList<Hero> playerTeam = new ArrayList<Hero>();
    private static ArrayList<ComboBox> heroSelections = new ArrayList<ComboBox>();
    private static ArrayList<TextField> heroNames = new ArrayList<TextField>();
    private int score;
    private int bossCount;
    private int totalDamage;
    private int kills;

    public static ArrayList<Hero> getPlayerTeam(){
        return playerTeam;
    }

    public static ArrayList<ComboBox> getHeroSelections(){
        return heroSelections;
    }

    public static ArrayList<TextField> getHeroNames(){
        return heroNames;
    }
}