package BootlegElderScrolls;

public class Hero{
    //fields
    private int attack;
    private int health;
    private int defence;
    private int magicResist;
    private int evasion;
    private int speed;
    private String name;
    
    //Hero constructor
    public Hero(int attack, int health, int defence, int magicResist, int evasion, int speed, String name){
        this.attack = attack;
        this.health = health;
        this.defence = defence;
        this.magicResist = magicResist;
        this.evasion = evasion;
        this.speed = speed;
        this.name = name;
    }
    //getters and setters
    public int getHealth(){
        return this.health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getDefence(){
        return this.defence;
    }

    public String getName(){
        return this.name;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public int getSpeed(){
        return speed;
    }

    public int getEvasion(){
        return evasion;
    }

    //attack method
    public void attack(Hero hero){
        int trueDamage;
        trueDamage = this.attack - hero.defence/2;
        if(trueDamage <= 0){
            trueDamage = 1;
        }
        if(!Guard.containsImmune(hero)) {
            System.out.println(this.name + " did " + trueDamage + " damage to " + hero.getName());
            hero.setHealth(hero.getHealth() - trueDamage);
        }
        else{
            System.out.println(hero.name + " is guarding and is immune to damage!");
        }
    }
    //use item
    public void useItem(Item item){
        switch(item.getItemType()){
            case HEALING:
                this.health += 250;
                break;
            case DEFENCE:
                this.defence += 50;
        }
    }

    public String toString(){
        return "Hero Type: " + this.getClass() + "\nHero Name: " + this.name + "\nHealth: " + this.health;
    }



}
