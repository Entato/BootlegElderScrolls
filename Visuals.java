package BootlegElderScrolls;

import javafx.scene.image.*;
import java.io.*;
import java.util.*;

public class Visuals {
    private static ArrayList<FileInputStream> sprites = new ArrayList<FileInputStream>();
    private static ArrayList<Image> team1Sprites = new ArrayList<Image>();
    private static ArrayList<Image> team2Sprites = new ArrayList<Image>();

    //getters so I can access them and use their methods
    public static ArrayList<FileInputStream> getSprites() {
        return sprites;
    }

    public static ArrayList<Image> getTeam1Sprites() {
        return team1Sprites;
    }

    public static ArrayList<Image> getTeam2Sprites() {
        return team2Sprites;
    }
}
