package BootlegElderScrolls;

import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class HealthBar {
    private int maxHealth;


    //health bar constructor
    public HealthBar(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public Rectangle adjustGreenBar(int newHealth){
        //calculates how much bar should be filled
        Rectangle greenBar = new Rectangle((newHealth/this.maxHealth)*100, 20);

        return greenBar;
    }


    //maxHP getter
    public int getMaxHealth(){
        return this.maxHealth;
    }

}
