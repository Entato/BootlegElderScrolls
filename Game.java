package BootlegElderScrolls;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Hero[]> attackList = new ArrayList<Hero[]>();

    //storing attack actions
    public static void addAttack(Hero attacker, Hero defender){
        Hero[] heroes = new Hero[2];
        heroes[0] = attacker;
        heroes[1] = defender;


        for(int i = 0; i < attackList.size(); i++){
            if(attacker.getSpeed() > attackList.get(i)[0].getSpeed()){
                attackList.add(i, heroes);
            }
            else if(i = attackList.size()-1){
                attackList.add(heroes);
            }
        }

        
    }

    //clears attack list
    public static void clearAttacks(){
        attackList.clear();
    }
}
