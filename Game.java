package BootlegElderScrolls;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static ArrayList<Hero[]> attackList = new ArrayList<Hero[]>();
    private static ArrayList<Hero> team1 = new ArrayList<Hero>();
    private static ArrayList<Hero> team2 = new ArrayList<Hero>();
    private static ArrayList<ComboBox> heroSelections = new ArrayList<ComboBox>();
    private static ArrayList<TextField> heroNames = new ArrayList<TextField>();
    private static ListView<String> battleLog = new ListView<String>();
    private static int teamTurn = 0;
    private static Label nameLabel = new Label();
    private static Hero itemLastUsedOn;
    private static int turn = 1;

    //getters and setters
    public static int getTeamTurn(){
        return teamTurn;
    }

    public static void setTeamTurn(int teamTurn){
        Game.teamTurn = teamTurn;
    }
    public static Label getNameLabel() {
        return nameLabel;
    }
    public static Hero getItemLastUsedOn() {
        return itemLastUsedOn;
    }

    public static void setItemLastUsedOn(Hero itemLastUsedOn) {
        Game.itemLastUsedOn = itemLastUsedOn;
    }
    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Game.turn = turn;
    }

    //made getters so I can edit them without making a method for each action
    public static ListView<String> getBattleLog() {
        return battleLog;
    }
    public static ArrayList<Hero> getTeam1(){
        return team1;
    }
    public static ArrayList<Hero> getTeam2(){
        return team2;
    }
    public static ArrayList<ComboBox> getHeroSelections(){
        return heroSelections;
    }
    public static ArrayList<TextField> getHeroNames(){
        return heroNames;
    }
    //storing attack actions
    public static void addAttack(Hero attacker, Hero defender){
        Hero[] heroes = new Hero[2];
        heroes[0] = attacker;
        heroes[1] = defender;


        //slots attack according to speed
        if(attackList.size() != 0) {
            for (int i = 0; i < attackList.size(); i++) {
                if (attacker.getSpeed() > attackList.get(i)[0].getSpeed()) {
                    attackList.add(i, heroes);
                    break;
                } else if (i == attackList.size() - 1) {
                    attackList.add(heroes);
                    break;
                }
            }
        }
        else{
            attackList.add(heroes);
        }
    }

    //clears attack list
    public static void clearAttacks(){
        attackList.clear();
    }

    //performs the attacks
    public static void commitAttacks(){


        for(int i = 0; i < attackList.size(); i++){
            //if attacker has died, skip that hero's attack
            if(attackList.get(i)[0].getHealth() <= 0){
                continue;
            }
            //if defender is dead, skip that hero's attack
            if(attackList.get(i)[1].getHealth() <= 0){
                battleLog.getItems().add(attackList.get(i)[0].getName() + "'s Attack was wasted On a Dead Body!");
                continue;
            }

            if(Guard.containsImmune(attackList.get(i)[1])){
                System.out.println(attackList.get(i)[1].getName() + " is guarding and is immune to " +
                        attackList.get(i)[0].getName() + "'s attack!");
                battleLog.getItems().add(attackList.get(i)[1].getName() + " is guarding and is immune to " +
                        attackList.get(i)[0].getName() + "'s attack!");
                continue;
            }

            if (evade(attackList.get(i)[1])){
                //battle log message
                Game.getBattleLog().getItems().add(attackList.get(i)[1].getName() + " Evaded " +
                        attackList.get(i)[0].getName() + "'s Attack!");
                continue;
            }

            //attack if none of the above conditions are met
            attackList.get(i)[0].attack(attackList.get(i)[1]);

        }
        clearAttacks();
        Guard.clearImmune();
    }

    //random check to see if defender evades
    public static boolean evade(Hero defender){
        Random random = new Random();
        int rand = random.nextInt(100);
        if(rand <= defender.getEvasion()){
            return true;
        } else {
            return false;
        }
    }

    //reset method
    public static void reset(){
        attackList.clear();
        team2.clear();
        heroSelections.clear();
        heroNames.clear();
        battleLog.getItems().clear();
        Visuals.getTeam2Sprites().clear();
        Game.turn = 1;
    }



}
