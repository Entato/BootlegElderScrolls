package BootlegElderScrolls;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.FileInputStream;
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
        Scene infoScene = createInfoScene(mainMenu, menuScene);



        //button methods
        //new game
        newGameButton.setOnAction(e -> {
            begin = true;
            mainMenu.close();
        });
        //load game
        loadGameButton.setOnAction(e -> {
            try{
                IO.load();
                mainMenu.close();
                mainStage.setScene(Hub.hubScene());
                mainStage.show();
            } catch (IOException i){
                System.err.println(i);
                Popup.display("Error: Could not load file");
            }
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

        //displays window
        mainMenu.showAndWait();

        return begin;
    }

    //create info Scene
    public static Scene createInfoScene(Stage mainMenu, Scene menuScene){

        //main layout
        VBox mainBox = new VBox(20);
        mainBox.setPadding(new Insets(20, 20, 20, 20));
        mainBox.setAlignment(Pos.CENTER);

        //title label
        Label label = new Label("How To Play");
        label.setId("title-labels");

        //game description layout
        VBox descriptionBox = new VBox(10);
        Label descriptionTitleLabel = new Label("Game Description");
        Label gameDescription = new Label("After waking up inside a cold and damp dungeon, you must fight your "+
                "way out by defeating the monsters that lie in your path. Assemble your team of 3 heroes and conquer the underground" +
                " with your knowledge of turn based strategy that will test your skills and break your spirit.");
        gameDescription.setWrapText(true);
        descriptionTitleLabel.setId("subtitle");
        Separator separator0 = new Separator(Orientation.HORIZONTAL);
        separator0.setPadding(new Insets(5, 0, 5, 0));

        descriptionBox.getChildren().addAll(descriptionTitleLabel, gameDescription, separator0);

        //controls layout
        VBox controlsBox = new VBox(10);
        Label controlsTitleLabel = new Label("Instructions and Controls");
        controlsTitleLabel.setId("title-labels");
        Label controlsInstructions = new Label("Fantasy Showdown is a turn based strategy game in which both" +
                " players make their moves simultaneously and therefore an element of prediction and mind-games is" +
                " involved.\nEach turn, both players can commit actions with each of their currently alive team members\n:" +
                "\n1. Attack: Choose an enemy to attack. During the battle phase, your hero will attempt to attack the" +
                " selected enemy.\n\n2. Guard: Guarding makes a hero immune to damage for a turn, but it consumes a turn." +
                " You cannot guard twice in a row with the same hero.\n\n3. Use Item: You may use your choice of item and" +
                " apply it to a team member. This will consume a turn and boost the stat of the recipient corresponding" +
                " to the type of item used.\n\n4. Special: Your hero will use their special move unique to their hero class" +
                " for various effects (descriptions below). You may only use a hero's special once per game.");
        controlsInstructions.setWrapText(true);

        Separator separator00 = new Separator(Orientation.HORIZONTAL);
        separator00.setPadding(new Insets(5, 0, 5, 0));

        controlsBox.getChildren().addAll(controlsTitleLabel, controlsInstructions, separator00);



        //hero descriptions:
        Label heroesLabel = new Label("Meet the Heroes");
        heroesLabel.setPadding(new Insets(10, 0, 30, 0));
        heroesLabel.setId("title-labels");

        //archer layout ------------------------------------------------------------------------------------------------
        VBox archerInfoBox = new VBox();
        archerInfoBox.setAlignment(Pos.CENTER);
        Label archerLabel = new Label("Archer");
        archerLabel.setId("subtitle");
        Label archerInfo = new Label("The Archer is a versatile hero with great mobility and damage.\n" +
                "Pros:\n    -High Attack\n    -Great Evasion\n    -Great Speed\nCons:\n   -Poor Defence\n"+
                "   -Mediocre Health\nSpecial:\n    -Trishot: Fires a volley of arrows to each enemy doing 50 damage");
        archerInfo.setWrapText(true);
        archerInfoBox.getChildren().addAll(archerLabel, archerInfo);
        ImageView archerView = new ImageView(Visuals.getSprites().get(0));
        archerView.setFitHeight(180);
        archerView.setFitWidth(170);
        HBox archerBox = new HBox(20);
        archerBox.setPadding(new Insets(20, 0, 20, 0));
        archerBox.getChildren().addAll(archerInfoBox, archerView);
        //--------------------------------------------------------------------------------------------------------------
        //Assassin layout ------------------------------------------------------------------------------------------------
        VBox assassinInfoBox = new VBox();
        assassinInfoBox.setAlignment(Pos.CENTER);
        Label assassinLabel = new Label("Assassin");
        assassinLabel.setId("subtitle");
        Label assassinInfo = new Label("The Assassin is a high risk high reward hero with extremely high speed and attack.\n" +
                "Pros:\n    -Great Attack\n    -Good Evasion\n    -Great Speed\nCons:\n   -Poor Defence\n "+
                "   -Poor Health\nSpecial:\n    -Assassinate: Deal 150 damage to an enemy");
        assassinInfo.setWrapText(true);
        assassinInfoBox.getChildren().addAll(assassinLabel, assassinInfo);
        ImageView assassinView = new ImageView(Visuals.getSprites().get(1));
        assassinView.setFitHeight(180);
        assassinView.setFitWidth(170);
        HBox assassinBox = new HBox(20);
        assassinBox.setPadding(new Insets(20, 0, 20, 0));
        assassinBox.getChildren().addAll(assassinView, assassinInfoBox);
        //--------------------------------------------------------------------------------------------------------------
        //Healer layout ------------------------------------------------------------------------------------------------
        VBox healerInfoBox = new VBox();
        healerInfoBox.setAlignment(Pos.CENTER);
        Label healerLabel = new Label("Healer");
        healerLabel.setId("subtitle");
        Label healerInfo = new Label("The healer is a nice compliment to your team as she can absorb "+
                "hits and use her special to heal teammates.\n" +
                "Pros:\n    -Great Health\n    -Good Defence\n    -Can Heal Teammates\nCons:\n   -Poor Speed\n "+
                "  -Poor Evasion\n   -Mediocre Attack\nSpecial:\n    -Moonlight: Heal everyone on your team  by 75 health");
        healerInfo.setWrapText(true);
        healerInfoBox.getChildren().addAll(healerLabel, healerInfo);
        ImageView healerView = new ImageView(Visuals.getSprites().get(2));
        healerView.setFitHeight(140);
        healerView.setFitWidth(150);
        HBox healerBox = new HBox(20);
        healerBox.setPadding(new Insets(20, 0, 20, 0));
        healerBox.getChildren().addAll(healerInfoBox, healerView);
        //--------------------------------------------------------------------------------------------------------------
        //Knight layout ------------------------------------------------------------------------------------------------
        VBox knightInfoBox = new VBox();
        knightInfoBox.setAlignment(Pos.CENTER);
        Label knightLabel = new Label("Knight");
        knightLabel.setId("subtitle");
        Label knightInfo = new Label("The Knight boasts amazing toughness with great offensive firepower.\n" +
                "Pros:\n    -Great Attack\n    -Good Health\n    -Great Defence\nCons:\n   -Slow Speed\n "+
                "   -Poor Evasion\nSpecial:\n    -Tank Up: Give your team +50 Defence");
        knightInfo.setWrapText(true);
        knightInfoBox.getChildren().addAll(knightLabel, knightInfo);
        ImageView knightView = new ImageView(Visuals.getSprites().get(3));
        knightView.setFitHeight(180);
        knightView.setFitWidth(170);
        HBox knightBox = new HBox(20);
        knightBox.setPadding(new Insets(20, 0, 20, 0));
        knightBox.getChildren().addAll(knightView, knightInfoBox);
        //--------------------------------------------------------------------------------------------------------------
        //Wizard layout ------------------------------------------------------------------------------------------------
        VBox wizardInfoBox = new VBox();
        wizardInfoBox.setAlignment(Pos.CENTER);
        Label wizardLabel = new Label("Wizard");
        wizardLabel.setId("subtitle");
        Label wizardInfo = new Label("The Wizard obtains amazing firepower, But is fragile. A true glass cannon.\n" +
                "Pros:\n    -Amazing Attack\n    -Good Speed\nCons:\n   -Poor Defence\n "+
                "   -Mediocre Health\nSpecial:\n    -Super Steroid Spell: Give your team +50 attack");
        wizardInfo.setWrapText(true);
        wizardInfoBox.getChildren().addAll(wizardLabel, wizardInfo);
        ImageView wizardView = new ImageView(Visuals.getSprites().get(4));
        wizardView.setFitHeight(180);
        wizardView.setFitWidth(170);
        HBox wizardBox = new HBox(20);
        wizardBox.setPadding(new Insets(20, 0, 20, 0));
        wizardBox.getChildren().addAll(wizardInfoBox, wizardView);
        //--------------------------------------------------------------------------------------------------------------




        //separators
        Separator separator1 = new Separator(Orientation.HORIZONTAL);
        separator1.setPadding(new Insets(5, 0, 5, 0));
        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        separator2.setPadding(new Insets(5, 0, 5, 0));
        Separator separator3 = new Separator(Orientation.HORIZONTAL);
        separator3.setPadding(new Insets(5, 0, 5, 0));
        Separator separator4 = new Separator(Orientation.HORIZONTAL);
        separator4.setPadding(new Insets(5, 0, 5, 0));

        //VBox that stores all of the hero information
        VBox heroPanes = new VBox(heroesLabel, archerBox, separator1, assassinBox, separator2, healerBox, separator3, knightBox,
                separator4, wizardBox);

        //button to head back to main menu
        Button backButton = new Button("Back");
        backButton.setPrefWidth(150);

        mainBox.getChildren().addAll(label, backButton, descriptionBox, controlsBox, heroPanes);
        mainBox.setId("how-to-play");
        //back
        backButton.setOnAction(e ->{
            mainMenu.setScene(menuScene);
        });

        //scroller to store all of the information neatly
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mainBox);

        Scene infoScene = new Scene(scrollPane, 700, 550);
        infoScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        return infoScene;
    }
}
