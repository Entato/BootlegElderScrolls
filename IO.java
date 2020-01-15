package BootlegElderScrolls;

import java.io.*;
import java.util.*;

public class IO{
    private static final File file = new File("save.txt");

    public static void save() throws IOException{
        file.createNewFile();
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(Player.getBossCount());
        writer.println(Player.getKills());
        writer.println(Player.getScore());
        writer.println(Player.getTotalDamage());
        for (int i = 0; i < Player.getPlayerTeam().size(); i++){
            writer.println(Player.getPlayerTeam().get(i).getName());
            writer.println(Player.getPlayerTeam().get(i).getExp());
        }
        
        if (Player.getScore() > getHighScore()){
            writer.println(Player.getScore());
        }
        writer.close();
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

            //skips 10 lines until high score
            for (int i = 0; i < 10; i++){
                reader.nextLine();
            }

            return reader.nextInt();
        }

        return 0;
    }
}