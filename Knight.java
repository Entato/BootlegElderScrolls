package BootlegElderScrolls;

public class Knight extends Hero{
    //constructor
    public Knight(String name){
        super(120, 200, 120, 10, 70, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 10);
        this.setRegAtk(this.getRegAtk() + 10);
        this.setDefence(this.getDefence() + 25);
        this.setRegDef(this.getRegDef() + 25);
        this.setHealth(this.getHealth() + 50);
        this.setMaxHealth(this.getMaxHealth() + 50);
        this.setSpeed(this.getSpeed() + 7);
        this.newHealthBar(this.getMaxHealth());
    }
}
