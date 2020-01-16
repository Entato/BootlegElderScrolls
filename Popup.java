package BootlegElderScrolls;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Popup {
    public static void display(String text){
        //window
        Stage popStage = new Stage();
        popStage.setTitle("Attention");

        popStage.initModality(Modality.APPLICATION_MODAL);

        //layout
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);

        //buttons and labels
        Label warningLabel = new Label(text);
        Button okButton = new Button("OK");
        okButton.setPrefSize(60, 30);

        //add to layout
        box.getChildren().addAll(warningLabel, okButton);

        //button closes stage
        okButton.setOnAction(e -> popStage.close());

        Scene scene = new Scene(box, 300, 150);
        scene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        popStage.setScene(scene);

        //you have to deal with this before you carry on
        popStage.showAndWait();
    }
}
