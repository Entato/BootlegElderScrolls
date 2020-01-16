package BootlegElderScrolls;

import javafx.scene.image.ImageView;

public class Hero{
    //fields
    private int attack;
    private int maxHealth;
    private int health;
    private int defence;
    private int evasion;
    private int speed;
    private String name;
    private HealthBar healthBar;
    private boolean activeSpecial = true;
    private int level = 1;
    private int exp = 0;
    private int regDef;
    private int regAtk;
    private ImageView spriteView;
    
    //Hero constructor
    public Hero(int attack, int health, int defence, int evasion, int speed, String name){
        this.attack = attack;
        this.maxHealth = health;
        this.health = health;
        this.defence = defence;
        this.evasion = evasion;
        this.speed = speed;
        this.name = name;

        //store what these values should be
        this.regDef = defence;
        this.regAtk = attack;

        this.healthBar = new HealthBar(this.maxHealth);
        this.spriteView = new ImageView();

    }
    //getters and setters

    public ImageView getSpriteView(){
        return this.spriteView;
    }

    public void setSpriteView(ImageView spriteView) {
        this.spriteView = spriteView;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int health){
        this.health = health;
    }
    public int getAttack(){
        return this.attack;
    }

    public int getDefence(){
        return this.defence;
    }

    public String getName(){
        return this.name;
    }

    public int getSpeed(){
        return this.speed;
    }

    public int getEvasion(){
        return this.evasion;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public boolean getActiveSpecial() {
        return this.activeSpecial;
    }

    public void setActiveSpecial(boolean activeSpecial) {
        this.activeSpecial = activeSpecial;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public int getRegAtk() {
        return regAtk;
    }

    public void setRegAtk(int regAtk) {
        this.regAtk = regAtk;
    }

    public int getRegDef() {
        return regDef;
    }

    public void setRegDef(int regDef) {
        this.regDef = regDef;
    }

    public void addExp(int exp){
        this.exp += exp;
        checkLevelUp();
    }
    public void setName(String name){
        this.name = name;
    }

    public int getExp(){
        return exp;
    }

    //attack method
    public void attack(Hero hero){
        int trueDamage;
        trueDamage = this.attack * 100 / (hero.defence + 100);
        //at least 1 damage
        if(trueDamage <= 0){
            trueDamage = 1;
        }
        //if hero is killed, damage is exactly how much it took to kill
        if(hero.health - trueDamage < 0){
            trueDamage = hero.health;

        }
        if(!Guard.containsImmune(hero)) {
            System.out.println(this.name + " did " + trueDamage + " damage to " + hero.getName());

            Visuals.attackAnimation(this, hero, trueDamage);


        }
        else{
            Game.getBattleLog().getItems().add(hero.name + " is guarding and is immune to damage!");
        }
        //put this in visuals
        if(hero.getHealth() <= 0){
            //Game.getBattleLog().getItems().add(hero.name + " Has Died in Battle!");

            //for hero has died message
            Visuals.getDeath().add(true);

            this.addExp(50 + (20 * Player.getBossCount()));
        }
        //in this case it will make it so there will be no death message
        else{
            Visuals.getDeath().add(false);
        }
    }
    //use item
    public void useItem(Item item){
        switch(item.getItemType()){
            case HEALING:
                int healed;
                //if there is more than 150 hp to heal, heal 150
                if(this.healthBar.getMaxHealth() - this.health > 150) {
                    healed = 150;
                    this.health += healed;
                }
                //else just fill the health back to full
                else {
                    healed = (int) this.healthBar.getMaxHealth() - this.health;
                    this.health += healed;
                }
                //battle log message
                System.out.println();
                Game.getBattleLog().getItems().add("A Potion was used on " + Game.getItemLastUsedOn().getName() +
                        " For " + healed + " Health");
                //update health
                this.updateHealthBar();

                break;

            case DEFENCE:
                this.defence += 50;
                Game.getBattleLog().getItems().add("A Potion was used on " + Game.getItemLastUsedOn().getName() +
                        " For 50 Defence");
                break;

            case ATTACK:
                this.attack += 50;
                Game.getBattleLog().getItems().add("A Potion was used on " + Game.getItemLastUsedOn().getName() +
                        " For 50 Attack");
                break;
        }
    }

    //to string method, was used for testing information about the heroes in the text-based version
    public String toString(){
        return "Hero Type: " + this.getClass() + "\nHero Name: " + this.name + "\nHealth: " + this.health;
    }

    //update individual health bars, doing this individually to allow for animations to happen
    public void updateHealthBar(){
        System.out.println("Update health bar reached");
        //for the green bar
        this.healthBar.adjustGreenBar(this.health);
        //for the label
        this.healthBar.getHealthInfo().setText(this.name + ": " + this.health + "/" +
                (int) this.healthBar.getMaxHealth());
    }


    //checks if hero should be leveled up
    public void checkLevelUp(){
        if (level == 1 && exp > 100){
            levelUp();
            checkLevelUp();
        } else if (level == 2 && exp > 300){
            levelUp();
            checkLevelUp();

        }
    }

    //adds stats to hero that has leveled up
    public void levelUp(){
        this.attack += 10;
        this.defence += 10;
        this.health += 30;
        this.maxHealth += 30;
        this.speed += 50;
        this.regDef += 10;
        this.regAtk += 10;
        //makes a new health bar so the green bar won't extend the boundaries
        this.healthBar = new HealthBar(this.maxHealth);
    }
    
    public String getHero(){
        String name = this.getClass()+ "";

        return name.substring(26);
    }

}
