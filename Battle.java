package BootlegElderScrolls;

import java.util.*;



class Battle{
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args){
        Hero[] team1 = new Hero[3];
        Hero[] team2 = new Hero[3];

        System.out.println("Choose your heroes");

        for(int i = 0; i < 3; i++){
            team1[i] = selectHero();
            team2[i] = AIPick(i);
        }

    }
    public static Hero selectHero(){
        System.out.println("Select your class");
        String input = reader.nextLine();
        Hero hero = null;

        //Switch statement
        switch(input){
            case "Wizard":
                hero = new Wizard(askName());
                break;
            case "Archer":
                hero = new Archer(askName());
                break;
            case "Knight":
                hero = new Knight(askName());
                break;
            case "Assassin":
                hero = new Assassin(askName());
                break;
            case "Healer":
                hero = new Healer(askName());
                break;
        }

        return hero;
    }
    public static String askName(){
        System.out.println("Enter your name");
        String name = reader.nextLine();

        return name;
    }

    public static Hero AIPick(int i){

        Random rand = new Random();
        int r = rand.nextInt(5);
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


}