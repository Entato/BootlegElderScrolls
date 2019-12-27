package BootlegElderScrolls;

import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class MainMenu{

    private static boolean begin;

    public static boolean display(){

        //main menu window
        Stage mainMenu = new Stage();
        mainMenu.setTitle("Fantasy Showdown");
        mainMenu.initModality(Modality.APPLICATION_MODAL);

        //labels and buttons
        Label title = new Label("Fantasy Showdown");
        title.setPadding(new Insets(20, 20, 40, 20));
        Button startButton = new Button("Start");
        Button quitButton = new Button("Quit");
        Button infoButton = new Button("How To Play");

        //main menu layout
        VBox menuLayout = new VBox(20);
        //design
        menuLayout.setAlignment(Pos.TOP_CENTER);
        menuLayout.setPadding(new Insets(20, 20, 20, 20));


        //add labels and buttons to layout
        menuLayout.getChildren().addAll(title, startButton, quitButton, infoButton);
        //creates scene
        Scene menuScene = new Scene(menuLayout, 400, 300);
        //sets as scene to be displayed
        mainMenu.setScene(menuScene);

        //info scene
        VBox infoLayout = new VBox(20);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        Label infoLabel = new Label("How To Play:\netcetcetc");
        Button backButton = new Button("Back");
        infoLayout.getChildren().addAll(infoLabel, backButton);
        Scene infoScene = new Scene(infoLayout, 600, 400);


        //button methods
        //start
        startButton.setOnAction(e -> {
            begin = true;
            mainMenu.close();
        });
        //quit
        quitButton.setOnAction(e -> {
            begin = false;
            mainMenu.close();
        });
        //info
        infoButton.setOnAction(e ->{
            mainMenu.setScene(infoScene);
        });
        //back
        backButton.setOnAction(e ->{
            mainMenu.setScene(menuScene);
        });

        //displays window
        mainMenu.showAndWait();

        return begin;
    }
}
