package BootlegElderScrolls;

import java.io.*;
import java.util.*;

public class IO{
    private static final File file = new File("save.txt");

    public static void save() throws IOException{
        file.createNewFile();
        int highScore = getHighScore();
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println(Player.getBossCount());
        writer.println(Player.getKills());
        writer.println(Player.getScore());
        writer.println(Player.getTotalDamage());
        for (int i = 0; i < Player.getPlayerTeam().size(); i++){
            writer.println(Player.getPlayerTeam().get(i).getHero());
            writer.println(Player.getPlayerTeam().get(i).getName());
            writer.println(Player.getPlayerTeam().get(i).getExp());
        }
        
        if (Player.getScore() > highScore){
            writer.println(Player.getScore());
        } else if (highScore == 0){
            writer.println(0);
        } else {
            writer.println(highScore);
        }
        writer.close();
    }

    public static void load() throws IOException{
        Scanner reader = new Scanner(file);
        Player.setBossCount(Integer.parseInt(reader.nextLine()));
        Player.setKills(Integer.parseInt(reader.nextLine()));
        Player.scoreAdd(Integer.parseInt(reader.nextLine()));
        Player.totalDamageAdd(Integer.parseInt(reader.nextLine()));
        for (int i = 0; i < 3; i++){
            switch (reader.nextLine()) {
                case "Wizard":
                    Player.getPlayerTeam().add(new Wizard(reader.nextLine()));
                    Player.getPlayerTeam().get(i).addExp(Integer.parseInt(reader.nextLine()));
                    Visuals.getTeam1Sprites().add(Visuals.getSprites().get(4));
                    break;
                case "Archer":
                    Player.getPlayerTeam().add(new Archer(reader.nextLine()));
                    Player.getPlayerTeam().get(i).addExp(Integer.parseInt(reader.nextLine()));
                    Visuals.getTeam1Sprites().add(Visuals.getSprites().get(0));
                    break;
                case "Knight":
                    Player.getPlayerTeam().add(new Knight(reader.nextLine()));
                    Player.getPlayerTeam().get(i).addExp(Integer.parseInt(reader.nextLine()));
                    Visuals.getTeam1Sprites().add(Visuals.getSprites().get(3));
                    break;
                case "Assassin":
                    Player.getPlayerTeam().add(new Assassin(reader.nextLine()));
                    Player.getPlayerTeam().get(i).addExp(Integer.parseInt(reader.nextLine()));
                    Visuals.getTeam1Sprites().add(Visuals.getSprites().get(1));
                    break;
                case "Healer":
                    Player.getPlayerTeam().add(new Healer(reader.nextLine()));
                    Player.getPlayerTeam().get(i).addExp(Integer.parseInt(reader.nextLine()));
                    Visuals.getTeam1Sprites().add(Visuals.getSprites().get(2));
                    break;
            }
        }
        reader.close();
    }

    public static void erase() throws IOException{
        if (!file.createNewFile()){
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            for (int i = 0; i < 13; i++){
                writer.println("bruh");
            }
            writer.close();
        }
    }

    public static int getHighScore() throws IOException{
        Scanner reader = new Scanner(file);

        //checks if there is a save file
        if (!file.createNewFile()){

            //if file hasnt been used before returns 0;
            if(!reader.hasNextLine()){
                reader.close();
                return 0;
            }

            //skips 13 lines until high score
            for (int i = 0; i < 13; i++){
                System.out.println(reader.nextLine());
            }

            int highScore = Integer.parseInt(reader.nextLine());
            reader.close();
            return highScore;
        }
        reader.close();
        //if no high score exists
        return 0;
    }
}