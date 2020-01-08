package BootlegElderScrolls;

public class Grunt extends Boss{
    //constructor
    public Grunt(String name, int Stage){
        super(50 + Stage*25, 75 + Stage*25, 25, 25, 10, 75, name);
    }
}