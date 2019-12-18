package BootlegElderScrolls;

import java.util.ArrayList;

class Guard{
    private ArrayList<Hero> immune = new ArrayList<Hero>();
    private ArrayList<Hero> guardable = new ArrayList<Hero>();

    //getters
    public ArrayList<Hero> getImmune(){
        return this.immune;
    }

    public ArrayList<Hero> getGuardable(){
        return this.guardable;
    }

    public void addImmune(Hero hero){
        immune.add(hero);
    }

    public void addGuardable(Hero hero){
        guardable.add(hero);
    }

    public void removeImmune(Hero hero){
        immune.remove(hero);
    }

    public void removeGuardable(Hero hero){
        guardable.remove(hero);
    }

    public boolean containsImmune(Hero hero){
        return immune.contains(hero);
    }

    public boolean containsGuardable(Hero hero){
        return guardable.contains(hero);
    }
}