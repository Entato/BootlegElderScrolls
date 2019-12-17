public class Wizard extends Hero{
    //fields
    private int mana;
    private int spellPower;
    
    //constructor
    public Wizard(int health, int defence, int magicResist, int evasion, int speed, String name, int mana,
                  int spellPower){
        super(health, defence, magicResist, evasion, speed, name);
        this.mana = mana;
        this.spellPower = spellPower;
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
