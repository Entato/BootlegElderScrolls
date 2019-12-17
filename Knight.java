public class Knight extends Hero{
    //fields
    private int armour;
    private int honour;
    
    //constructor
    public Knight(int health, int defence, int magicResist, int evasion, int speed, String name, int armour,
                  int honour){
        super(health, defence, magicResist, evasion, speed, name);
        this.armour = armour;
        this.honour = honour;
    }
    //use item
    public void useItem(Item item){
        super.useItem(item);

        if(item.getItemType() == Item.ItemType.ATTACK){
            this.honour += 50;
        }
    }


}
