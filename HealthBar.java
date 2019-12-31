package BootlegElderScrolls;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class HealthBar {
    private double maxHealth;
    private Rectangle greenBar = new Rectangle(100, 20);
    private Label healthInfo = new Label();


    //health bar constructor
    public HealthBar(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public Rectangle getGreenBar(){
        return this.greenBar;
    }


    public void adjustGreenBar(double newHealth){
        //calculates how much bar should be filled
        this.greenBar.setWidth((newHealth/this.maxHealth)*100);
        this.greenBar.setFill(Color.GREEN);
    }


    //maxHP getter
    public double getMaxHealth(){
        return this.maxHealth;
    }

    public Label getHealthInfo() {
        return this.healthInfo;
    }

    public void setHealthInfo(Label healthInfo) {
        this.healthInfo = healthInfo;
    }
}
