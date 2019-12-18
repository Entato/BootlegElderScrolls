package BootlegElderScrolls;

import java.util.Random;

class Battle{
    public static void main(String[] args){
        Hero hero1 = new Wizard("wizzyboy");
        Hero hero2 = new Knight("REEE");
        Hero hero3 = new Healer("healer");

        Hero hero4 = new Assassin("ass ass in");
        Hero hero5 = new Archer("aRcHEr");
        Hero hero6 = new Knight("name");

        Hero[] team1 = {hero1, hero2, hero3};
        Hero[] team2 = {hero4, hero5, hero6};

        Random random = new Random(3);
        for (int i = 0; i < 3; i++){
            int rand = random.nextInt(3);
            team1[i].attack(team2[rand], 500);
        }

        Item item = new Item(Item.ItemType.HEALING);


        
       

    }
}