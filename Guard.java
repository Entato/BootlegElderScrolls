package BootlegElderScrolls;

import java.util.ArrayList;

class Guard{
    private static ArrayList<Hero> immune = new ArrayList<Hero>();
    private static ArrayList<Hero> unGuardable = new ArrayList<Hero>();

    public static void addGuard(Hero hero){
        addImmune(hero);
        addUnGuardable(hero);
    }

    public static void addImmune(Hero hero){
        immune.add(hero);
    }

    public static void addUnGuardable(Hero hero){
        unGuardable.add(hero);
    }

    public static void removeImmune(Hero hero){
        immune.remove(hero);
    }

    public static void removeUnGuardable(Hero hero){
        unGuardable.remove(hero);
    }

    public static boolean containsImmune(Hero hero){
        return immune.contains(hero);
    }

    public static boolean containsUnGuardable(Hero hero){
        return unGuardable.contains(hero);
    }

    public static void clearImmune(){
        immune.clear();
    }
}