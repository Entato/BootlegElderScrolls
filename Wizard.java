package BootlegElderScrolls;

public class Wizard extends Hero{
    //fields
    private int mana;
    private int spellPower;
    
    //constructor
    public Wizard(String name){
        super(100, 100, 50, 50, 10, 50, name);
        this.mana = 100;
        this.spellPower = 75;
    }

    public int getMana() {
        return mana;
    }
    public void setMana(int mana){
        this.mana = mana;
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);

        switch(item.getItemType()){
            case MANA:
                this.mana += 150;
            case ATTACK:
                this.spellPower += 50;
        }
    }
}
