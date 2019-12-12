public class Archer extends Hero{
    //fields
    private int arrows;
    
    //constuctor
    public Archer(int health, int defence, int magicResist, int evasion, int speed, String name, int arrows){
        super(health, defence, magicResist, evasion, speed, name);
        this.arrows = arrows;
    }
}