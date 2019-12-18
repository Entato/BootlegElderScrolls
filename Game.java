package BootlegElderScrolls;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Hero[]> attackList = new ArrayList<Hero[]>();

    //storing attack actions
    public static void addAttack(Hero attacker, Hero defender){
        Hero[] heroes = new Hero[2];
        heroes[0] = attacker;
        heroes[1] = defender;

        attackList.add(heroes);
    }

    //clears attack list
    public static void clearAttacks(){
        attackList.clear();
    }
}
