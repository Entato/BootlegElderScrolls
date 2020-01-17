package BootlegElderScrolls;

public class Knight extends Hero{
    //constructor
    public Knight(String name){
        super(110, 200, 90, 5, 70, name);
    }

    //use item
    public void useItem(Item item){
        super.useItem(item);
    }

    @Override
    public void levelUp(){
        this.addLevel();
        this.setAttack(this.getAttack() + 8);
        this.setRegAtk(this.getRegAtk() + 8);
        this.setDefence(this.getDefence() + 20);
        this.setRegDef(this.getRegDef() + 20);
        this.setHealth(this.getHealth() + 35);
        this.setMaxHealth(this.getMaxHealth() + 35);
        this.setSpeed(this.getSpeed() + 7);
        this.newHealthBar(this.getMaxHealth());
    }
}
