package BootlegElderScrolls;

import java.util.ArrayList;

class Guard{
    private static ArrayList<Hero> immune = new ArrayList<Hero>();
    private static ArrayList<Hero> guardable = new ArrayList<Hero>();

    public static void addImmune(Hero hero){
        immune.add(hero);
    }

    public static void addGuardable(Hero hero){
        guardable.add(hero);
    }

    public static void removeImmune(Hero hero){
        immune.remove(hero);
    }

    public static void removeGuardable(Hero hero){
        guardable.remove(hero);
    }

    public static boolean containsImmune(Hero hero){
        return immune.contains(hero);
    }

    public static boolean containsGuardable(Hero hero){
        return guardable.contains(hero);
    }
}