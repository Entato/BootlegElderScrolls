package BootlegElderScrolls;

public class Archer extends Hero{
    
    //constructor
    public Archer(String name){
        super(120, 105, 50, 30, 110, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }
}
