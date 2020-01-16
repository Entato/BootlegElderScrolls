package BootlegElderScrolls;

public class Grunt extends Boss{
    //constructor
    public Grunt(String name, int Stage){
        super(35 + Stage*15, 75 + Stage*15, 25 + Stage*5, 10, 25 + Stage*10, name);
    }
}