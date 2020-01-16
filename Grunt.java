package BootlegElderScrolls;

public class Grunt extends Boss{
    //constructor
    public Grunt(String name, int Stage){
        super(45 + Stage*15, 85 + Stage*15, 50 + Stage*5, 10, 25 + Stage*10, name);
    }
}