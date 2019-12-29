package BootlegElderScrolls;

import javafx.scene.shape.*;

public class HealthBar {
    private int maxHealth;
    private static int barWidth = 100;
    private static Rectangle bar = new Rectangle(barWidth, 20);

    //health bar constructor
    public HealthBar(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public Rectangle adjustGreenBar(int newHealth){
        //calculates how much bar should be filled
        Rectangle greenBar = new Rectangle((newHealth/barWidth)*100, 20);

        return greenBar;
    }

}
