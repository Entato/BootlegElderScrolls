package BootlegElderScrolls;

public class Wizard extends Hero{
    
    //constructor
    public Wizard(String name){
        super(175, 120, 40, 5, 85, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 50);
        this.setRegAtk(this.getRegAtk() + 50);
        this.setDefence(this.getDefence() + 10);
        this.setRegDef(this.getRegDef() + 10);
        this.setHealth(this.getHealth() + 35);
        this.setMaxHealth(this.getMaxHealth() + 35);
        this.setSpeed(this.getSpeed() + 12);
        this.newHealthBar(this.getMaxHealth());
    }
}
