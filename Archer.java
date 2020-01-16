package BootlegElderScrolls;

public class Archer extends Hero{
    
    //constructor
    public Archer(String name){
        super(125, 100, 60, 30, 100, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 40);
        this.setRegAtk(this.getRegAtk() + 40);
        this.setDefence(this.getDefence() + 15);
        this.setRegDef(this.getRegDef() + 15);
        this.setHealth(this.getHealth() + 25);
        this.setMaxHealth(this.getMaxHealth() + 25);
        this.setSpeed(this.getSpeed() + 15);
        this.setEvasion(this.getEvasion() + 5);
        this.newHealthBar(this.getMaxHealth());
    }
}
