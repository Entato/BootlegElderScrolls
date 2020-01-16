package BootlegElderScrolls;

class Boss extends Hero{

    public Boss(int attack, int health, int defence, int evasion, int speed, String name){
        super(attack, health, defence, evasion, speed, name);
    }

    public void setName(String name){
        super.setName(name);
    }
}