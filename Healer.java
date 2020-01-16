package BootlegElderScrolls;

public class Healer extends Hero{
    
    //constructor
    public Healer(String name){
        super(100, 175, 70, 10, 50, name);
    }

    //get item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 10);
        this.setRegAtk(this.getRegAtk() + 10);
        this.setDefence(this.getDefence() + 20);
        this.setRegDef(this.getRegDef() + 20);
        this.setHealth(this.getHealth() + 40);
        this.setMaxHealth(this.getMaxHealth() + 40);
        this.setSpeed(this.getSpeed() + 10);
        this.newHealthBar(this.getMaxHealth());
    }
}
