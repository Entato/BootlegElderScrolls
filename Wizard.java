public class Wizard extends Hero{
    //fields
    private int mana;
    private int spellPower;
    
    //constructor
    public Wizard(int health, int defence, int magicResist, int evasion, int speed, String name, int arrows, int mana,
                  int spellPower){
        super(health, defence, magicResist, evasion, speed, name);
        this.mana = mana;
        this.spellPower = spellPower;
    }
}