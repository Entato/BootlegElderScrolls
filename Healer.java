package BootlegElderScrolls;

public class Healer extends Hero{
    //fields
    private int healing;
    private int mana;
    
    //constructor
    public Healer(String name){
        super(100, 50, 50, 10, 50, name);
        this.healing = 50;
        this.mana = 100;
    }

    //get item
    public void useItem(Item item){
        super.useItem(item);

        if(item.getItemType() == Item.ItemType.ATTACK){
            healing += 50;
        }
        else if(item.getItemType() == Item.ItemType.MANA){
            mana += 50;
        }
    }
}
