package BootlegElderScrolls;

public class Assassin extends Hero{
    
    //constructor
    public Assassin(String name){
        super(130, 100, 50, 30, 120, name);
    }

    //useItem
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 30);
        this.setRegAtk(this.getRegAtk() + 30);
        this.setDefence(this.getDefence() + 20);
        this.setRegDef(this.getRegDef() + 20);
        this.setHealth(this.getHealth() + 30);
        this.setMaxHealth(this.getMaxHealth() + 30);
        this.setSpeed(this.getSpeed() + 17);
        this.setEvasion(this.getEvasion() + 5);
        this.newHealthBar(this.getMaxHealth());
    }
}
