package BootlegElderScrolls;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Hub{
    public static Scene hubScene(Stage stage){
        BorderPane borderPane = new BorderPane();

        VBox partyList = new VBox();

        Label party = new Label("Party:");
        partyList.getChildren().add(party);

        Button startBattle = new Button("Battle");
        startBattle.setOnAction(e -> {
            stage.setScene(Battle.createBattleScene());
        });

        borderPane.setLeft(partyList);
        borderPane.setBottom(startBattle);

        Scene hub = new Scene(borderPane);
        return hub;
    }
}