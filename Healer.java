public class Healer extends Hero{
    //fields
    private int healing;
    private int mana;
    
    //constructor
    public Healer(int health, int defence, int magicResist, int evasion, int speed, String name, int healing, 
                  int mana){
        super(health, defence, magicResist, evasion, speed, name);
        this.healing = healing;
        this.mana = mana;
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
