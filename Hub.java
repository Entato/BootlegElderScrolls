package BootlegElderScrolls;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Hub{
    //GUI for the hub
    public static Scene hubScene(Stage stage){
        BorderPane borderPane = new BorderPane();

        VBox partyList = new VBox();

        Label party = new Label("Party:");
        partyList.getChildren().add(party);

        //adds all the party member to a vBox
        for (int i = 0; i < Game.getTeam1().size(); i++){
            Label member = new Label(Game.getTeam1().get(i).getName());
            partyList.getChildren().add(member);
        }

        //button to start battle
        Button startBattle = new Button("Battle");
        startBattle.setOnAction(e -> {
            stage.setScene(Battle.createBattleScene());
        });

        //adds everything to borderPane
        borderPane.setLeft(partyList);
        borderPane.setBottom(startBattle);

        Scene hub = new Scene(borderPane, 1000, 650);
        return hub;
    }
}