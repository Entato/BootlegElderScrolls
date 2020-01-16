package BootlegElderScrolls;

public class Healer extends Hero{
    //fields
    private int healing;
    private int mana;
    
    //constructor
    public Healer(String name){
        super(100, 150, 70, 10, 50, name);
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
            Game.getBattleLog().getItems().add("A Potion was used on " + Game.getItemLastUsedOn().getName() +
                    " For 50 Mana");
            mana += 50;
        }
    }

    public int getMana() {
        return this.mana;
    }
    public void setMana(int mana){
        this.mana = mana;
    }
}
