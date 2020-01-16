package BootlegElderScrolls;

public class Knight extends Hero{
    //fields
    private int armour;
    private int honour;
    
    //constructor
    public Knight(String name){
        super(100, 200, 100, 10, 50, name);
        this.armour = 50;
        this.honour = 0;
    }
    //use item
    public void useItem(Item item){
        super.useItem(item);

        if(item.getItemType() == Item.ItemType.ATTACK){
            this.honour += 50;
        }
    }

    public void specialAttack(){
        
    }
}
