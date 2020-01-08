package BootlegElderScrolls;

public class DungeonKeeper extends Boss{
    //constructor
    public DungeonKeeper (String name, int Stage){
        super(75 + Stage*25, 100 + Stage*25, 25, 25, 10, 90, name);
    }
}