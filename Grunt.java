package BootlegElderScrolls;

public class Grunt extends Boss{
    //constructor
    public Grunt(String name, int Stage){
        super(70 + Stage*15, 125 + Stage*15, 50 + Stage*5, 5, 45 + Stage*10, name);
    }
}