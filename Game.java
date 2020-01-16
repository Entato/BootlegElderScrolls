package BootlegElderScrolls;

import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private static ArrayList<Hero[]> attackList = new ArrayList<Hero[]>();
    private static ArrayList<Hero> team2 = new ArrayList<Hero>();
    private static ListView<String> battleLog = new ListView<String>();
    private static int teamTurn = 0;
    private static Label nameLabel = new Label();
    private static Hero itemLastUsedOn;
    private static int turn = 1;
    private static boolean midReset = false;

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
    
    public static void setMidReset(boolean midReset){
        Game.midReset = midReset;
    }

    //made getters so I can edit them without making a method for each action
    public static ListView<String> getBattleLog() {
        return battleLog;
    }

    public static ArrayList<Hero> getTeam2(){
        return team2;
    }
    public static boolean getMidReset(){
        return midReset;
    }
    public static ArrayList<Hero[]> getAttackList(){
        return attackList;
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
        deleteBadAttacks();

        for(int i = 0; i < attackList.size(); i++){



            if(Guard.containsImmune(attackList.get(i)[1])){
                System.out.println(attackList.get(i)[1].getName() + " is guarding and is immune to " +
                        attackList.get(i)[0].getName() + "'s attack!");
                battleLog.getItems().add(attackList.get(i)[1].getName() + " is guarding and is immune to " +
                        attackList.get(i)[0].getName() + "'s attack!");
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
        //reset enemy team, battle log, layouts, enemy team sprites, and some misc. variables
        midReset = true;
        attackList.clear();
        team2.clear();
        battleLog.getItems().clear();
        Battle.getFlowPane().getChildren().clear();
        Visuals.getTeam2Sprites().clear();
        Visuals.getSprite2Box().getChildren().clear();
        Visuals.getSprite1Box().getChildren().clear();
        Game.turn = 1;
        Game.teamTurn = 0;
        Player.getHeroSelections().clear();
        Player.getHeroNames().clear();

        //reset health
        for(Hero h : Player.getPlayerTeam()){
            h.setHealth((int)h.getHealthBar().getMaxHealth());
            h.updateHealthBar();

            //remove in game item effects
            h.setDefence(h.getRegDef());
            h.setAttack(h.getRegAtk());
        }

        //resets player specials to active
        Player.resetSpecials();

    }




    public static void deleteBadAttacks(){
        ArrayList<Hero[]> attackListCopy = (ArrayList<Hero[]>)attackList.clone();
        ArrayList<int[]> healths = new ArrayList<int[]>();
        for(int i = 0; i < attackList.size(); i++){
            int[] hp = new int[2];
            hp[0] = attackListCopy.get(i)[0].getHealth();
            hp[1] = attackListCopy.get(i)[1].getHealth();

            healths.add(hp);
        }

        //removes evaded attacks
        for(int i = 0; i < attackListCopy.size(); i++){
            //remove evaded attacks
            if (evade(attackListCopy.get(i)[1])){
                //battle log message
                Game.getBattleLog().getItems().add(attackListCopy.get(i)[1].getName() + " Evaded " +
                        attackListCopy.get(i)[0].getName() + "'s Attack!");
                attackListCopy.remove(i);

            }
        }

        //another loop for removing dead attacker, don't want to use the same loop, since it might cause conflict
        for(int i = 0; i < attackListCopy.size(); i++){
            Hero attacker = attackListCopy.get(i)[0];
            Hero defender = attackListCopy.get(i)[1];


            //remove dead attackers
            int damage = attacker.getAttack() * 100 / (defender.getDefence() + 100);
            defender.setHealth(defender.getHealth() - damage);
            if(defender.getHealth() <= 0){
                for(int j = i; j < attackListCopy.size(); j++){
                    if(attackListCopy.get(j)[0].equals(defender)){
                        attackListCopy.remove(j);
                    }
                }
            }




        }

        for(int i = 0; i < attackList.size(); i++){
            attackList.get(i)[0].setHealth(healths.get(i)[0]);
            attackList.get(i)[1].setHealth(healths.get(i)[1]);
        }

        //get new list
        attackList = attackListCopy;



    }





}