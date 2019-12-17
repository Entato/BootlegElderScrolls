public class Archer extends Hero{
    //fields
    private int poison;
    
    //constructor
    public Archer(int health, int defence, int magicResist, int evasion, int speed, String name, int arrows){
        super(health, defence, magicResist, evasion, speed, name);
        this.poison = poison;
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);

        if (item.getItemType() == Item.ItemType.ATTACK) {
            poison += 20;
        }
    }
}
