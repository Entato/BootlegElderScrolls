public class Assassin extends Hero{
    //fields
    private int lethality;
    
    //constructor
    public Assassin(int health, int defence, int magicResist, int evasion, int speed, String name, int lethality){
        super(health, defence, magicResist, evasion, speed, name);
        this.lethality = lethality;
    }
   
    
}