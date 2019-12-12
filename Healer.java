public class Healer extends Hero{
    //fields
    private int healing;
    private int manaRegen;
    
    //constructor
    public Healer(int health, int defence, int magicResist, int evasion, int speed, String name, int healing, 
                  int manaRegen){
        super(health, defence, magicResist, evasion, speed, name);
        this.healing = healing;
        this.manaRegen = manaRegen;
    }
}