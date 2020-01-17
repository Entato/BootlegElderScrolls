package BootlegElderScrolls;

public class Healer extends Hero{
    
    //constructor
    public Healer(String name){
        super(100, 160, 70, 5, 50, name);
    }

    //get item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 15);
        this.setRegAtk(this.getRegAtk() + 15);
        this.setDefence(this.getDefence() + 18);
        this.setRegDef(this.getRegDef() + 18);
        this.setHealth(this.getHealth() + 35);
        this.setMaxHealth(this.getMaxHealth() + 35);
        this.setSpeed(this.getSpeed() + 10);
        this.newHealthBar(this.getMaxHealth());
    }
}
