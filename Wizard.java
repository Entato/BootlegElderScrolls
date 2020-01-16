package BootlegElderScrolls;

public class Wizard extends Hero{
    
    //constructor
    public Wizard(String name){
        super(175, 120, 40, 5, 85, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }
}
