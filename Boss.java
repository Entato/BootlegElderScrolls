package BootlegElderScrolls;

class Boss extends Hero{

    public Boss(int attack, int health, int defence, int magicResist, int evasion, int speed, String name){
        super(attack, health, defence, magicResist, evasion, speed, name);
    }

    public String getSprite(){
        return "no sprite";
    }
    public void setName(String name){
        super.setName(name);
    }
}