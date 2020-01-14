package BootlegElderScrolls;

public class Archer extends Hero{
    //fields
    private int poison;
    
    //constructor
    public Archer(String name){
        super(120, 105, 50, 50, 30, 110, name);
        this.poison = 20;
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);

        if (item.getItemType() == Item.ItemType.ATTACK) {
            poison += 20;
        }
    }
}
