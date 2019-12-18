package BootlegElderScrolls;

public class Assassin extends Hero{
    //fields
    private int lethality;
    
    //constructor
    public Assassin(String name){
        super(100, 50, 50, 10, 50, name);
        this.lethality = 50;
    }

    //useItem
    public void useItem(Item item){
        super.useItem(item);

        if (item.getItemType() == Item.ItemType.ATTACK) {
            this.lethality += 50;
        }
    }
   
    
}
