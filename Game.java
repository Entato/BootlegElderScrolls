package BootlegElderScrolls;

import java.util.ArrayList;
import java.util.Random;

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
        for(int i = 0; i < attackList.size(); i++){
            if(Guard.containsImmune(attackList.get(i)[1])){
                System.out.println(attackList.get(i)[1].getName() + " is guarding and is immune to " +
                        attackList.get(i)[0].getName() + "'s attack!");
                continue;
            } else if (evade(attackList.get(i)[1])){
                continue;
            }
            attackList.get(i)[0].attack(attackList.get(i)[1]);

            //if attacker has died, removes that hero's attack
            if(attackList.get(i)[1].getHealth() <= 0){
                for(Hero[] dead : attackList){
                    if(dead[0] == attackList.get(i)[1]){
                        attackList.remove(dead);
                        break;
                    }
                }
            }

        }
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
}
