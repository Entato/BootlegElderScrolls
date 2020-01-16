package BootlegElderScrolls;

import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

import java.io.IOException;
import java.util.*;


public class Battle{
    private static FlowPane battleActions = new FlowPane();

    //getter and setter
    public static FlowPane getFlowPane(){
        return battleActions;
    }
    //GUI METHODS

    //creating battle scene method -------------------------------------------------------------------------------------
    public static Scene createBattleScene(){

        //border pane layout allows for stacking layout easily
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(new VBox());


        //bottom portion layout
        HBox bottomBox = new HBox(20);
        bottomBox.setId("redder-layouts");
        bottomBox.setPrefHeight(200);

        //action layout
        battleActions = new FlowPane();

        battleActions.setHgap(40);
        battleActions.setVgap(20);
        battleActions.setAlignment(Pos.CENTER_RIGHT);
        battleActions.setPadding(new Insets(20, 0, 20, 20));


        //action buttons and labels
        Button attackButton = new Button("Attack");
        attackButton.setPrefSize(270, 60);
        Button guardButton = new Button("Guard");
        guardButton.setPrefSize(270, 60);
        Button itemButton = new Button("Use Item");
        itemButton.setPrefSize(270, 60);
        Button specialButton = new Button("Special");
        specialButton.setPrefSize(270, 60);

        //Tells user who is attacking
        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.TOP_CENTER);
        nameBox.setPadding(new Insets(0, 0, 0, 0));
        Game.getNameLabel().setWrapText(true);
        Game.getNameLabel().setId("title-labels");
        Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(0).getName() + " Do?");
        Game.getNameLabel().setPadding(new Insets(0, 350, 0, 0));

        Label levelLabel = new Label("Level " + (Player.getBossCount() + 1));
        levelLabel.setId("title-labels");
        levelLabel.setAlignment(Pos.CENTER_LEFT);
        levelLabel.setPadding(new Insets(0, 300, 0, 0));

        nameBox.getChildren().addAll(levelLabel, Game.getNameLabel());

        //Health bars
        //team 1
        VBox team1Box = new VBox(50);
        team1Box.setPadding(new Insets(20, 20, 20, 20));
        team1Box.setAlignment(Pos.CENTER);
        setBars(team1Box, true);

        //team2
        VBox team2Box = new VBox(50);
        team2Box.setPadding(new Insets(20, 20, 20, 20));
        team2Box.setAlignment(Pos.CENTER);
        setBars(team2Box, false);

        //battle log
        //bottom left box is for name info and battle log
        HBox bottomLeftBox = new HBox();
        VBox logBox = new VBox(10);
        logBox.setPadding(new Insets(10, 10, 30, 10));
        Label logLabel = new Label("Battle Log:");
        //scroll pane used for when battle log gets longer
        ScrollPane scroller = new ScrollPane();
        Game.getBattleLog().setPrefWidth(320);

        scroller.setFitToWidth(true);
        scroller.setMaxHeight(200);
        //battle log is a static variable stored in the game class
        scroller.setId("battle-log");
        scroller.setContent(Game.getBattleLog());
        logBox.getChildren().addAll(logLabel, scroller);

        bottomLeftBox.getChildren().addAll(logBox);
        bottomLeftBox.setAlignment(Pos.CENTER);

        //SPRITES
        HBox spriteBox = new HBox(500);

        spriteBox.setAlignment(Pos.CENTER);
        Visuals.getSprite1Box().setAlignment(Pos.CENTER_LEFT);
        Visuals.getSprite2Box().setAlignment(Pos.CENTER_RIGHT);
        spriteBox.getChildren().addAll(Visuals.getSprite1Box(), Visuals.getSprite2Box());
        System.out.println(Visuals.getTeam1Sprites().toString());
        Visuals.placeSprites();

        //BUTTON ACTIONS
        attackButton.setOnAction(e -> attackButtonMethod(bottomBox, battleActions)); //passing layouts for temp change
        itemButton.setOnAction(e -> itemButtonMethod(bottomBox, battleActions));
        guardButton.setOnAction(e -> guardButtonMethod());
        specialButton.setOnAction(e -> specialButtonMethod(bottomBox, battleActions));

        //adds buttons to layouts
        battleActions.getChildren().addAll(attackButton, guardButton, itemButton, specialButton);
        battleActions.setOrientation(Orientation.VERTICAL);
        bottomBox.getChildren().addAll(bottomLeftBox, battleActions);

        //adds layouts to border pane
        borderPane.setBottom(bottomBox);
        borderPane.setLeft(team1Box);
        borderPane.setRight(team2Box);
        borderPane.setCenter(spriteBox);
        borderPane.setTop(nameBox);
        

        //creates scene and returns it
        Scene battleScene = new Scene(borderPane, 1000, 650);
        battleScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        return battleScene;
    }

    //checks if a team is dead
    public static boolean checkTeamDead(ArrayList<Hero> team){
        boolean dead = true;
        for (int i = 0; i < team.size(); i++){
            if (team.get(i).getHealth() > 0){
                dead = false;
            }
        }
        return dead;
    }

    //attack button method ---------------------------------------------------------------------------------------------
    public static void attackButtonMethod(HBox hBox, FlowPane flowPane){
        //layout
        VBox bigBox = new VBox(20);
        bigBox.setPadding(new Insets(20, 20, 20, 20));
        HBox optionBox = new HBox(20);
        optionBox.setPadding(new Insets(10, 10, 10, 10));
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setStyle("-fx-border-color: black");

        //label
        Label optionsLabel = new Label("Who Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Attack?");

        //back button
        Button backButton = new Button("<");
        backButton.setPrefSize(40, 40);
        optionBox.getChildren().add(backButton);
        backButton.setOnAction(e -> {
            hBox.getChildren().set(1, flowPane);
        });

        //storing buttons so I can reference them later
        ArrayList<Button> options = new ArrayList<Button>();
        Button button;

        for(Hero h : Game.getTeam2()){

            button = new Button(h.getName());
            button.setPrefSize(150, 40);
            options.add(button);
            Button finalButton = button;
            optionBox.getChildren().add(button);
            
            //disables button if AI is dead
            if (h.getHealth() <= 0){
                button.setDisable(true);
            }
            
            button.setOnAction(e -> {

                Game.addAttack(Player.getPlayerTeam().get(Game.getTeamTurn()), Game.getTeam2().get(options.indexOf(finalButton)));
                //apply old layout back
                hBox.getChildren().set(1, flowPane);

                //removes guard if guard was used last turn
                if (Guard.containsUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()))){
                    Guard.removeUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()));
                }

                //if there are more actions left for the player
                if(team1Alive()) {
                    //new name label
                    Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Do?");
                } else {
                    AI();
                }
            });
        }
        //apply new layout
        bigBox.getChildren().addAll(optionsLabel, optionBox);
        hBox.getChildren().set(1, bigBox);

    }
    //check if there are any other heroes in team 1 that can still attack
    public static boolean team1Alive(){
        //start one after current one
        int start = Game.getTeamTurn() + 1;
        //there are only 3 heroes max (index 0, 1, 2)
        if(start > 2){
            return false;
        }
        for(int i = start; i < Player.getPlayerTeam().size(); i++){
            if(Player.getPlayerTeam().get(i).getHealth() > 0){
                Game.setTeamTurn(i);
                return true;
            }
        }
        //if no one else is alive, return false
        return false;
    }
    // item button method ----------------------------------------------------------------------------------------------
    public static void itemButtonMethod(HBox hBox, FlowPane flowPane){

        //layout
        VBox bigBox = new VBox(20);
        bigBox.setPadding(new Insets(20, 20, 20, 20));
        HBox optionBox = new HBox(20);
        optionBox.setPadding(new Insets(10, 10, 10, 10));
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setStyle("-fx-border-color: black");

        //back button
        Button backButton = new Button("<");
        backButton.setPrefSize(40, 40);
        optionBox.getChildren().add(backButton);
        backButton.setOnAction(e -> {
            hBox.getChildren().set(1, flowPane);
        });


        ChoiceBox itemChoice = new ChoiceBox();


        itemChoice.setMaxWidth(100);
        itemChoice.getItems().addAll("Attack", "Defence", "Healing");


        itemChoice.setValue("Attack");

        //label
        Label optionsLabel = new Label("Who Will You Use The Item On?");

        //storing buttons so I can reference them later
        ArrayList<Button> options = new ArrayList<Button>();
        Button button;

        for(Hero h : Player.getPlayerTeam()){
            button = new Button(h.getName());
            if(h.getHealth() <= 0){
                button.setDisable(true);
            }
            button.setPrefSize(120, 40);
            options.add(button);
            Button finalButton = button;
            optionBox.getChildren().add(button);
            button.setOnAction(e -> {

                //if guarded last turn, makes it eligible to guard again next turn
                if(Guard.containsUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()))){
                    Guard.removeUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()));
                }

                //used for battle log
                Game.setItemLastUsedOn(Player.getPlayerTeam().get(options.indexOf(finalButton)));
                switch (itemChoice.getValue().toString()){
                    case "Attack":
                        Player.getPlayerTeam().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.ATTACK));
                        break;
                    case "Defence":
                        Player.getPlayerTeam().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.DEFENCE));
                        break;
                    case "Healing":
                        Player.getPlayerTeam().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.HEALING));
                        break;
                }
                //apply old layout back
                hBox.getChildren().set(1, flowPane);

                //if there are more actions left for the player
                if(team1Alive()) {
                    //new name label
                    Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Do?");
                }
                else {
                    AI();
                }
            });
        }
        optionBox.getChildren().add(itemChoice);
        //apply new layout
        bigBox.getChildren().addAll(optionsLabel, optionBox);
        hBox.getChildren().set(1, bigBox);
    }

    //create hero selection scene method -------------------------------------------------------------------------------
    public static Scene createHeroSelectionScene(){
        String[] heroTypes = {"Archer", "Assassin", "Healer", "Knight", "Wizard"};
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setId("redder-layouts");

        for(int i = 0; i < 3; i++){
            VBox vBox = new VBox(20);
            vBox.setPadding(new Insets(10, 20, 10, 20));

            //drop down list
            ComboBox heroList = new ComboBox(FXCollections.observableArrayList(heroTypes));
            heroList.setId("drop-downs");
            heroList.setPromptText("Select a Hero");
            Player.getHeroSelections().add(heroList);

            //enter hero name
            TextField nameField = new TextField();
            nameField.setPromptText("Enter a Name");
            nameField.setFont(new Font("Traditional Arabic", 16));
            Player.getHeroNames().add(nameField);

            Label namePrompt = new Label("Hero Name:");
            namePrompt.setId("name-labels");
            namePrompt.setFont(new Font("Traditional Arabic", 20));

            //compile in a layout
            vBox.getChildren().addAll(heroList, namePrompt, nameField);

            //add to main horizontal layout
            hBox.getChildren().add(vBox);
        }
        //button to enter results
        Button enterButton = new Button("Ready");
        enterButton.setFont(new Font("Urdu Typesetting", 20));
        enterButton.setPrefSize(200, 20);
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 20, 20, 20));
        buttonBox.getChildren().add(enterButton);

        //putting the layout in the border pane
        borderPane.setCenter(hBox);
        borderPane.setBottom(buttonBox);

        Scene selectionScene = new Scene(borderPane, 600, 300);
        selectionScene.getStylesheets().add("BootlegElderScrolls/MainStyleSheet.css");

        //button method
        enterButton.setOnAction(e ->{
            boolean valid = true;

            //loop to check for duplicate hero selections
            try{
                outer_loop:
                for (ComboBox c : Player.getHeroSelections()) {
                    for (ComboBox c2 : Player.getHeroSelections()) {
                        if (c.getValue().equals(c2.getValue()) && !c.equals(c2)) {
                            Popup.display("You Cannot Select Duplicate Heroes!");
                            valid = false;
                            break outer_loop;
                        }
                    }
                }
            }
            //if user tries to battle without selecting 3 heroes
            catch(NullPointerException ne){
                valid = false;
                Popup.display("Please Select 3 Heroes!");
            }

            //saving hero selections
            if(valid) {
                for (int i = 0; i < 3; i++) {
                    switch (Player.getHeroSelections().get(i).getValue().toString()) {
                        case "Wizard":
                            Player.getPlayerTeam().add(new Wizard(Player.getHeroNames().get(i).getText()));
                            Visuals.getTeam1Sprites().add(Visuals.getSprites().get(4));
                            break;
                        case "Archer":
                            Player.getPlayerTeam().add(new Archer(Player.getHeroNames().get(i).getText()));
                            Visuals.getTeam1Sprites().add(Visuals.getSprites().get(0));
                            break;
                        case "Knight":
                            Player.getPlayerTeam().add(new Knight(Player.getHeroNames().get(i).getText()));
                            Visuals.getTeam1Sprites().add(Visuals.getSprites().get(3));
                            break;
                        case "Assassin":
                            Player.getPlayerTeam().add(new Assassin(Player.getHeroNames().get(i).getText()));
                            Visuals.getTeam1Sprites().add(Visuals.getSprites().get(1));
                            break;
                        case "Healer":
                            Player.getPlayerTeam().add(new Healer(Player.getHeroNames().get(i).getText()));
                            Visuals.getTeam1Sprites().add(Visuals.getSprites().get(2));
                            break;
                    }
                }


                //AI creates team
                //keep track of what AI has chosen
                ArrayList<Integer> picks = new ArrayList<Integer>();
                for(int i = 0; i < 3; i++){
                    /*
                    Game.getTeam2().add(AIPick(i, picks));
                    */
                    Game.getTeam2().add(AIPickGrunt(i));
                }
                System.out.println(Game.getTeam2().toString());

                //makes window show the hub scene
                MainMenu.getMainStage().setScene(Hub.hubScene());
                System.out.println(Game.getTeam2().toString());
            }
        });

        return selectionScene;
    }
    //setting health bars method ---------------------------------------------------------------------------------------
    public static void setBars(VBox box, boolean team){
        Rectangle mainBar;
        Pane completeBar;



        

        for(int i = 0; i < 3; i++){

            //creating health bar

            mainBar = new Rectangle(100, 20);
            mainBar.setStroke(Color.BLACK);
            mainBar.setStrokeWidth(5);
            

            //for first team
            if(team) {
                Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo().setWrapText(true);
                box.setPadding(new Insets(0, 0, 0, 40));
                box.setAlignment(Pos.CENTER_RIGHT);
                //css id
                Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo().setId("hp-labels");
                Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo().setPadding(new Insets(0, 0, -30, 0));
                Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo().setAlignment(Pos.CENTER_LEFT);
                

                Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo().setText(Player.getPlayerTeam().get(i).getName() + ": " +
                        Player.getPlayerTeam().get(i).getHealth() +
                        "/" + (int) Player.getPlayerTeam().get(i).getHealthBar().getMaxHealth());
                Player.getPlayerTeam().get(i).getHealthBar().getGreenBar().setFill(Color.GREEN);
                completeBar = new Pane();
                completeBar.setPadding(new Insets(0, 0, 0, 0));
                completeBar.getChildren().addAll(mainBar, Player.getPlayerTeam().get(i).getHealthBar().getGreenBar());

                //adding it to the layout
                box.getChildren().addAll(Player.getPlayerTeam().get(i).getHealthBar().getHealthInfo(), completeBar);
            }
            //for second team
            else{
                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setWrapText(true);
                box.setPadding(new Insets(0, 150, 0, 0));
                box.setAlignment(Pos.CENTER_LEFT);
                //css id
                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setId("hp-labels");
                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setPadding(new Insets(0, 0, -30, 0));
                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setAlignment(Pos.CENTER_RIGHT);

                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setText(Game.getTeam2().get(i).getName() + ": " +
                        Game.getTeam2().get(i).getHealth() +
                        "/" + (int) Game.getTeam2().get(i).getHealthBar().getMaxHealth());
                Game.getTeam2().get(i).getHealthBar().getGreenBar().setFill(Color.GREEN);
                completeBar = new Pane();
                completeBar.setPadding(new Insets(0, 0, 0, 0));
                completeBar.getChildren().addAll(mainBar, Game.getTeam2().get(i).getHealthBar().getGreenBar());

                //adding it to the layout
                box.getChildren().addAll(Game.getTeam2().get(i).getHealthBar().getHealthInfo(), completeBar);
            }

        }
    }

    public static void AI(){
        Random random = new Random();
        int rand;

        //randomly picks hero to attack
        for(int i = 0; i < Game.getTeam2().size(); i++){
            if (Game.getTeam2().get(i).getHealth() <= 0){
                continue;
            }

            while(true) {
                rand = random.nextInt(Player.getPlayerTeam().size());
                //don't want to attack a hero that's already dead
                if(Player.getPlayerTeam().get(rand).getHealth() > 0){
                    break;
                }
            }

            Game.addAttack(Game.getTeam2().get(i), Player.getPlayerTeam().get(rand));
        }
        Game.commitAttacks();
        Visuals.playAnimationSet();

        //clears immune
        Guard.clearImmune();



    }

    //guard button method ----------------------------------------------------------------------------------------------
    public static void guardButtonMethod(){
        //checks if the player guarded last turn
        if (Guard.containsUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()))){
            Game.getBattleLog().getItems().add(Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Cannot Guard in a Row!");
        }
        else {
            Guard.addGuard(Player.getPlayerTeam().get(Game.getTeamTurn()));

            //if there are more actions left for the player
            if(team1Alive()) {
                //new name label
                Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Do?");
            }
            else {
                AI();
            }
        }
    }
    //special button method
    public static void specialButtonMethod(HBox hBox, FlowPane flowPane){
        if(!Player.getPlayerTeam().get(Game.getTeamTurn()).getActiveSpecial()){
            Game.getBattleLog().getItems().add(Player.getPlayerTeam().get(Game.getTeamTurn()).getName() +
                    " Has Already Used Their Special!");
        }
        else{
            //sets their special to false so they can't use it again for the rest of the game
            Player.getPlayerTeam().get(Game.getTeamTurn()).setActiveSpecial(false);

            //Archer special
            if(Player.getPlayerTeam().get(Game.getTeamTurn()) instanceof Archer){
                Game.getBattleLog().getItems().add("Archer Used Trishot for 50 damage to Each Enemy!");
                for(int i = 0 ; i < Game.getTeam2().size(); i++){
                    if(Game.getTeam2().get(i).getHealth() >= 50) {
                        Game.getTeam2().get(i).setHealth(Game.getTeam2().get(i).getHealth() - 50);
                        Player.totalDamageAdd(50);
                    }
                    else{
                        Player.totalDamageAdd(Game.getTeam2().get(i).getHealth());
                        Game.getTeam2().get(i).setHealth(0);

                    }
                    Game.getTeam2().get(i).updateHealthBar();
                    if(checkTeamDead(Game.getTeam2())){
                        Battle.nextTurn();
                        Battle.checkForEnd();
                    }
                }
            }

            //Assassin Special
            else if(Player.getPlayerTeam().get(Game.getTeamTurn()) instanceof Assassin){
                Game.getBattleLog().getItems().add("Assassin Used Hidden Mist to guard your team!");
                for(int i = 0 ; i < Player.getPlayerTeam().size(); i++){
                    Guard.addImmune(Player.getPlayerTeam().get(i));
                }
            }

            //Knight special
            else if(Player.getPlayerTeam().get(Game.getTeamTurn()) instanceof Knight){
                Game.getBattleLog().getItems().add("Knight used Tank Up to increase team's defence");
                for(int i = 0 ; i < Player.getPlayerTeam().size(); i++){
                    Player.getPlayerTeam().get(i).setDefence(Player.getPlayerTeam().get(i).getDefence() + 50);
                }
            }

            //Healer special
            else if(Player.getPlayerTeam().get(Game.getTeamTurn()) instanceof Healer){
                Game.getBattleLog().getItems().add("Healer used Moonlight to heal your team by 75 health");
                for(int i = 0 ; i < Player.getPlayerTeam().size(); i++){

                    //only apply special if teammate is alive
                    if(Player.getPlayerTeam().get(i).getHealth() > 0) {
                        //if player is down 75 or more hp, heal 75
                        if (Player.getPlayerTeam().get(i).getHealthBar().getMaxHealth() - Player.getPlayerTeam().get(i).getHealth() >= 75) {
                            Player.getPlayerTeam().get(i).setHealth(Player.getPlayerTeam().get(i).getHealth() + 75);
                        }
                        //otherwise just heal to full
                        else {
                            Player.getPlayerTeam().get(i).setHealth((int)Player.getPlayerTeam().get(i).getHealthBar().getMaxHealth());
                        }
                        Player.getPlayerTeam().get(i).updateHealthBar();
                    }
                }
            }

            //Wizard Special
            else if(Player.getPlayerTeam().get(Game.getTeamTurn()) instanceof Wizard){
                Game.getBattleLog().getItems().add("Wizard Used Super Steroid Spell to boost\nHis teammate's attacks by 50");
                for(int i = 0 ; i < Player.getPlayerTeam().size(); i++){
                    Player.getPlayerTeam().get(i).setAttack(Player.getPlayerTeam().get(i).getAttack() + 50);
                }
            }

            //removes guard if guard was used last turn
            if (Guard.containsUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()))){
                Guard.removeUnGuardable(Player.getPlayerTeam().get(Game.getTeamTurn()));
            }

            //if there are more actions left for the player
            if(team1Alive()) {
                //new name label
                Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Do?");
            } else {
                AI();
            }
        }

    }

    public static Hero AIPickGrunt(int i){
        //determines what boss appears on what screen
        if(i == 1 && Player.getBossCount() == 2){
            return BossInfo.getBoss();
        }
        else if(i == 1 && Player.getBossCount() == 3){
            return BossInfo.getBoss();
        }
        else if(Player.getBossCount() == 4){
            Boss iceGiant = BossInfo.getBoss();
            iceGiant.setName("Ice Giant" + (i+1));
            return iceGiant;
        }
        else if(Player.getBossCount() == 5 && (i == 0 || i == 2)){
            //ice giants are from level 4 so I need to call the add sprites
            Boss iceGiant = new IceGiant();
            Visuals.getTeam2Sprites().add(Visuals.getSprites().get(8));
            iceGiant.setName("Ice King " + (i+1));
            return iceGiant;
        }
        else if(Player.getBossCount() == 5 && i == 1){
            return BossInfo.getBoss();
        }
        else if(Player.getBossCount() == 6 && i == 1){
            return BossInfo.getBoss();
        }
        else if(Player.getBossCount() == 7 && i == 1){
            return BossInfo.getBoss();
        }
        else if(Player.getBossCount() == 8 && i == 1){
            return BossInfo.getBoss();
        }
        else if(Player.getBossCount() == 9 && (i == 0 || i == 2)){
            //ice giants are from level 4 so I need to call the add sprites
            Boss iceGiant = new IceGiant();
            Visuals.getTeam2Sprites().add(Visuals.getSprites().get(8));
            iceGiant.setName("Ice King " + (i+1));
            return iceGiant;
        }
        else if(Player.getBossCount() == 9 && i == 1){
            return BossInfo.getBoss();
        }

        String name = "Grunt " + (i + 1);

        Grunt grunt = new Grunt(name, Player.getBossCount());
        //5 is for grunts
        Visuals.getTeam2Sprites().add(Visuals.getSprites().get(5));

        return grunt;
    }

    //gets called when battle finishes
    public static void battleOver(){
        System.out.println("Battle over called");
        //adds 3 kills
        Player.killsAdd();
        Player.killsAdd();
        Player.killsAdd();
        Player.scoreAdd(1000);
        for(int i = 0; i < Player.getPlayerTeam().size(); i++){
            if(Player.getPlayerTeam().get(i).getHealth() > 0) {
                Player.scoreAdd(250);
                Player.getPlayerTeam().get(i).addExp(50 + 10 * Player.getBossCount());
            }
        }

        Player.bossCountAdd();
        MainMenu.getMainStage().setScene(Hub.hubScene());
        Game.reset();
    }
    //check if battle is over ------------------------------------------------------------------------------------------
    public static void checkForEnd(){
        //checks if battle is over
        //both cases have a return so the rest of the method is not used
        if(checkTeamDead(Game.getTeam2())){

            battleOver();
            //AI creates team
            for(int i = 0; i < 3; i++){
                Game.getTeam2().add(AIPickGrunt(i));
            }

            return;
        } else if (checkTeamDead(Player.getPlayerTeam())){
            MainMenu.getMainStage().setScene(GameOver.gameOverScene());

            return;
        }
    }
    public static void nextTurn(){
        //for next turn

        Game.getAttackList().clear();

        Game.setTurn(Game.getTurn() + 1);
        String turnUpdate = new String("Turn " + Game.getTurn() + ":");
        Game.getBattleLog().getItems().add(turnUpdate);

        for(int i = 0; i < Player.getPlayerTeam().size(); i++) {
            //sets it as first alive hero
            if(Player.getPlayerTeam().get(i).getHealth() > 0){
                Game.setTeamTurn(i);
                break;
            }
        }

        Visuals.getSequences().clear();
        Visuals.getRecipients().clear();
        Visuals.getDamages().clear();


        Game.getNameLabel().setText("What Will " + Player.getPlayerTeam().get(Game.getTeamTurn()).getName() + " Do?");
    }

    public static void disableInteractions(){

        battleActions.setDisable(true);

    }
}