package BootlegElderScrolls;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Hero[]> attackList = new ArrayList<Hero[]>();

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
        for(Hero[] h : attackList){
            h[0].attack(h[1]);

            //if attacker has died, removes that hero's attack
            if(h[1].getHealth() <= 0){
                for(Hero[] dead : attackList){
                    if(dead[0] == h[1]){
                        attackList.remove(dead);
                    }
                }
            }

        }
    }

}
