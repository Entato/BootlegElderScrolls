package BootlegElderScrolls;

public class Healer extends Hero{
    
    //constructor
    public Healer(String name){
        super(100, 150, 70, 10, 50, name);
    }

    //get item
    public void useItem(Item item){
        super.useItem(item);
    }
}
