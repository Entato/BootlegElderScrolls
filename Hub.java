package BootlegElderScrolls;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

class Hub{
    //GUI for the hub
    public static Scene hubScene(){
        BorderPane borderPane = new BorderPane();

        VBox partyList = new VBox(30);
        partyList.setPadding(new Insets(20, 20, 20, 20));
        partyList.setAlignment(Pos.CENTER);

        Label party = new Label("Party:");
        party.setId("title-labels");
        party.setStyle("-fx-font-weight: bold");
        party.setFont(new Font("Traditional Arabic", 30));
        partyList.getChildren().add(party);

        //adds all the party members to a VBox
        for (int i = 0; i < Player.getPlayerTeam().size(); i++){
            Label member = new Label("Member " + (i+1) + ": " + Player.getPlayerTeam().get(i).getName());
            member.setFont(new Font("Traditional Arabic", 18));
            partyList.getChildren().add(member);
        }

        //stats
        VBox statBox = new VBox(30);
        Label statLabel = new Label("Statistics");
        statLabel.setId("title-labels");
        Label levelsLabel = new Label("Levels Cleared: " + Player.getBossCount());
        Label killsLabel = new Label("Total Kills: " + Player.getKills());
        Label scoreLabel = new Label("Score: " + Player.getScore());
        Label damageLabel = new Label("Total Damage: " + Player.getTotalDamage());

        statBox.getChildren().addAll(statLabel, levelsLabel, killsLabel, scoreLabel, damageLabel);
        statBox.setAlignment(Pos.CENTER);
        statBox.setPadding(new Insets(20, 40, 60, 20));


        //button to start battle
        Button startBattle = new Button("Battle");
        Button saveButton = new Button("Save and Quit");

        startBattle.setOnAction(e -> {
            MainMenu.getMainStage().setScene(Battle.createBattleScene());
        });

        saveButton.setOnAction(e -> {
            try{
                IO.save();
                MainMenu.getMainStage().close();
            } catch (IOException i){
                System.err.println(i);
            }
        });
        partyList.getChildren().addAll(startBattle, saveButton);
        
        //adds everything to borderPane
        borderPane.setLeft(partyList);
        borderPane.setRight(statBox);

        Scene hub = new Scene(borderPane, 700, 550);
        hub.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        return hub;
    }
}