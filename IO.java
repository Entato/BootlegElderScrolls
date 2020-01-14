package BootlegElderScrolls;

import java.io.*;
import java.util.*;

public class IO{
    private static final File file = new File("save.txt");

    public static void save() throws IOException{
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(Player.getBossCount());
        writer.write(Player.getKills());
        writer.write(Player.getScore());
        writer.write(Player.getTotalDamage());
        for (int i = 0; i < Player.getPlayerTeam().size(); i++){
            writer.write(Player.getPlayerTeam().get(i).getName());
        }
        
        if (Player.getScore() > getHighScore()){
            writer.write(Player.getScore());
        }
        
    }

    public static void load() throws IOException{
        Scanner reader = new Scanner(file);

    }

    public static void erase(){

    }

    public static int getHighScore() throws IOException{
        Scanner reader = new Scanner(file);

        //checks if there is a save file
        if (file.createNewFile()){

            //skips 7 lines until high score
            for (int i = 0; i < 7; i++){
                reader.nextLine();
            }

            return reader.nextInt();
        }

        return 0;
    }
}