package BootlegElderScrolls;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

class Hub{
    //GUI for the hub
    public static Scene hubScene(){
        BorderPane borderPane = new BorderPane();

        VBox partyList = new VBox(30);
        partyList.setPadding(new Insets(20, 20, 20, 20));
        partyList.setAlignment(Pos.CENTER);

        Label party = new Label("Party:");
        party.setStyle("-fx-font-weight: bold");
        party.setFont(new Font("Traditional Arabic", 30));
        partyList.getChildren().add(party);

        //adds all the party members to a vBox
        for (int i = 0; i < Player.getPlayerTeam().size(); i++){
            Label member = new Label("Member " + (i+1) + ": " + Player.getPlayerTeam().get(i).getName());
            member.setFont(new Font("Traditional Arabic", 18));
            partyList.getChildren().add(member);
        }

        //stats
        VBox statBox = new VBox(20);

        //button to start battle
        Button startBattle = new Button("Battle");

        startBattle.setOnAction(e -> {
            MainMenu.getMainStage().setScene(Battle.createBattleScene());
        });
        partyList.getChildren().add(startBattle);

        //adds everything to borderPane
        borderPane.setLeft(partyList);

        Scene hub = new Scene(borderPane, 500, 350);

        return hub;
    }
}