package BootlegElderScrolls;

import java.lang.reflect.Array;
import java.util.*;



class Battle{
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args){
        
        Hero[] team1 = new Hero[3];
        Hero[] team2 = new Hero[3];
        ArrayList<Integer> picks = new ArrayList<Integer>();

        System.out.println("Choose your heroes");

        for(int i = 0; i < 3; i++){
            team1[i] = selectHero();
            team2[i] = AIPick(i, picks);
            System.out.println(team2[i].toString());
        }
        printBattleField(team1, team2);
        action(team1, team2);
        Game.commitAttacks();

    }
    public static Hero selectHero(){
        boolean valid = false;
        Hero hero = null;
        while(!valid) {
            System.out.println("Select your class");
            String input = reader.nextLine();

            //Switch statement
            switch (input) {
                case "Wizard":
                    hero = new Wizard(askName());
                    valid = true;
                    break;
                case "Archer":
                    hero = new Archer(askName());
                    valid = true;
                    break;
                case "Knight":
                    hero = new Knight(askName());
                    valid = true;
                    break;
                case "Assassin":
                    hero = new Assassin(askName());
                    valid = true;
                    break;
                case "Healer":
                    hero = new Healer(askName());
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Name");
            }
        }

        return hero;
    }
    public static String askName(){
        System.out.println("Enter your name");
        String name = reader.nextLine();

        return name;
    }

    public static Hero AIPick(int i, ArrayList<Integer> picks){

        Random rand = new Random();
        int r;
        while(true) {
            r = rand.nextInt(5);
            if(!picks.contains(r)){
                picks.add(r);
                break;
            }
        }
        Hero hero = null;
        String name = "AI " + i;

        switch(r){
            case 0:
                hero = new Wizard(name);
                break;
            case 1:
                hero = new Archer(name);
                break;
            case 2:
                hero = new Knight(name);
                break;
            case 3:
                hero = new Assassin(name);
                break;
            case 4:
                hero = new Healer(name);
                break;
        }

        return hero;
    }

    public static void action(Hero[] team1, Hero[] team2){
        Scanner ints = new Scanner(System.in);
        for(int i = 0; i < 3; i++){
            System.out.println("What would you like to do with " + team1[i].getName() + "?");
            String action = reader.nextLine();

            if(action.equalsIgnoreCase("Guard")){
                if(Guard.containsUnGuardable(team1[i])){
                    System.out.println("You cannot guard twice in a row!");
                    i--;
                    continue;
                } else {
                    Guard.addUnGuardable(team1[i]);
                    Guard.addImmune(team1[i]);
                }
            }
            else if(action.equalsIgnoreCase("Attack")){
                System.out.println("Who do you want to attack?");
                int x = ints.nextInt();
                Game.addAttack(team1[i], team2[x]);

                //lets hero be ready to guard again next turn
                if(Guard.containsUnGuardable(team1[i])){
                    Guard.removeUnGuardable(team1[i]);
                }
            }
            else if(action.equalsIgnoreCase("Item")){
                System.out.println("What item do you want to use?");
                String item = reader.nextLine();
                if(item.equalsIgnoreCase("Healing")){
                    team1[i].useItem(new Item(Item.ItemType.HEALING));
                }
                else if(item.equalsIgnoreCase("Attack")){
                    team1[i].useItem(new Item(Item.ItemType.ATTACK));
                }
                //will error if you try to use on class without mana but I won't fix since GUI will redo this part
                else if(item.equalsIgnoreCase("Mana")){
                    team1[i].useItem(new Item(Item.ItemType.MANA));
                }
                else if(item.equalsIgnoreCase("Defence")){
                    team1[i].useItem(new Item(Item.ItemType.DEFENCE));
                }
            }

        }

    }

    public static void printBattleField(Hero[] team1, Hero[] team2){
        System.out.println("\nTeam 1:");
        for(Hero h : team1){
            System.out.println(h.toString());
        }
        System.out.println("\nTeam 2:");
        for(Hero h : team2){
            System.out.println(h.toString());
        }
    }




}