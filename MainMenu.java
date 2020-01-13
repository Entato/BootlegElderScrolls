package BootlegElderScrolls;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;


public class MainMenu extends Application{

    private static boolean begin;
    private static Stage mainStage;

    //start method -----------------------------------------------------------------------------------------------------
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        mainStage.setTitle("Fantasy Showdown");

        boolean run = display();

        //if player pressed start on menu
        if(run){
            mainStage.setScene(Battle.createHeroSelectionScene());
            mainStage.show();
        }
    }

    public static Stage getMainStage(){
        return mainStage;
    }

    //main method ------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws IOException {

        //GUI
        //launches GUI (start method)
        Visuals.initializeSprites();
        launch(args);
    }

    public static boolean display(){

        //main menu window
        Stage mainMenu = new Stage();
        mainMenu.setTitle("Fantasy Showdown");
        mainMenu.initModality(Modality.APPLICATION_MODAL);

        //labels and buttons
        Label title = new Label("Fantasy Showdown");
        title.setId("title-labels");
        title.setPadding(new Insets(20, 20, 40, 20));
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load Game");
        Button quitButton = new Button("Quit");
        Button infoButton = new Button("How To Play");

        //main menu layouts
        HBox newOrOldLayout = new HBox(10);
        VBox menuLayout = new VBox(20);
        //design
        menuLayout.setAlignment(Pos.TOP_CENTER);
        menuLayout.setPadding(new Insets(20, 20, 20, 20));
        newOrOldLayout.setAlignment(Pos.CENTER);


        //add labels and buttons to layout
        newOrOldLayout.getChildren().addAll(newGameButton, loadGameButton);
        menuLayout.getChildren().addAll(title, newOrOldLayout, quitButton, infoButton);
        //creates scene
        Scene menuScene = new Scene(menuLayout, 500, 400);
        menuScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        //sets as scene to be displayed
        mainMenu.setScene(menuScene);

        //info scene
        VBox infoLayout = new VBox(20);
        infoLayout.setAlignment(Pos.TOP_CENTER);
        Label infoLabel = new Label("How To Play:\netcetcetc");
        Button backButton = new Button("Back");
        infoLayout.getChildren().addAll(infoLabel, backButton);
        Scene infoScene = new Scene(infoLayout, 600, 400);
        infoScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");


        //button methods
        //new game
        newGameButton.setOnAction(e -> {
            begin = true;
            mainMenu.close();
        });
        //load game
        loadGameButton.setOnAction(e -> {
            //placeholder for when we do IO
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
