package BootlegElderScrolls;

public class Knight extends Hero{
    //fields
    private int armour;
    private int honour;
    
    //constructor
    public Knight(String name){
        super(200, 999999, 999999, 100, 100, name);
        this.armour = 999999;
        this.honour = 999999;
    }
    //use item
    public void useItem(Item item){
        super.useItem(item);

        if(item.getItemType() == Item.ItemType.ATTACK){
            this.honour += 50;
        }
    }


}
