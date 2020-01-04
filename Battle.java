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
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.util.*;


public class Battle{
    static Scanner reader = new Scanner(System.in);

    //GUI METHODS

    //creating battle scene method -------------------------------------------------------------------------------------
    public static Scene createBattleScene(){

        //border pane layout allows for stacking layout easily
        BorderPane borderPane = new BorderPane();

        //bottom portion layout
        HBox bottomBox = new HBox(20);
        bottomBox.setStyle("-fx-background-color: GOLD;");
        bottomBox.setPrefHeight(200);

        //action layout
        FlowPane battleActions = new FlowPane();
        battleActions.setPrefSize(350, 200);
        battleActions.setHgap(40);
        battleActions.setVgap(20);
        battleActions.setAlignment(Pos.CENTER_RIGHT);
        battleActions.setPadding(new Insets(20, 0, 20, 20));


        //action buttons and labels
        Button attackButton = new Button("Attack");
        attackButton.setPrefSize(130, 60);
        Button guardButton = new Button("Guard");
        guardButton.setPrefSize(130, 60);
        Button itemButton = new Button("Use Item");
        itemButton.setPrefSize(130, 60);
        Button specialButton = new Button("Special");
        specialButton.setPrefSize(130, 60);

        //Tells user who is attacking
        VBox nameBox = new VBox();
        nameBox.setStyle("-fx-border-color: black");
        nameBox.setPadding(new Insets(20, 20, 20, 20));
        nameBox.setAlignment(Pos.TOP_CENTER);

        Game.getNameLabel().setWrapText(true);
        Game.getNameLabel().setText("What Will " + Game.getTeam1().get(0).getName() + " Do?");
        nameBox.getChildren().addAll(Game.getNameLabel());

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
        HBox bottomLeftBox = new HBox(20);
        VBox logBox = new VBox(10);
        logBox.setPadding(new Insets(10, 10, 10, 10));
        Label logLabel = new Label("Battle Log:");
        //scroll pane used for when battle log gets longer
        ScrollPane scroller = new ScrollPane();
        scroller.setStyle("-fx-border-color: black");
        Game.getBattleLog().setPrefWidth(300);
        scroller.setFitToWidth(true);
        scroller.setMaxHeight(200);
        //battle log is a static variable stored in the game class
        scroller.setContent(Game.getBattleLog());
        logBox.getChildren().addAll(logLabel, scroller);



        bottomLeftBox.getChildren().addAll(nameBox, logBox);
        bottomLeftBox.setAlignment(Pos.CENTER);

        //BUTTON ACTIONS
        attackButton.setOnAction(e -> attackButtonMethod(bottomBox, battleActions)); //passing layouts for temp change
        itemButton.setOnAction(e -> itemButtonMethod(bottomBox, battleActions));
        guardButton.setOnAction(e -> guardButtonMethod());

        //adds buttons to layouts
        battleActions.getChildren().addAll(attackButton, guardButton, itemButton, specialButton);
        bottomBox.getChildren().addAll(bottomLeftBox, battleActions);

        //adds layouts to border pane
        borderPane.setBottom(bottomBox);
        borderPane.setLeft(team1Box);
        borderPane.setRight(team2Box);

        //creates scene and returns it
        Scene battleScene = new Scene(borderPane, 1000, 650);

        return battleScene;
    }

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
        Label optionsLabel = new Label("Who Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Attack?");

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
            button.setPrefSize(100, 40);
            options.add(button);
            Button finalButton = button;
            optionBox.getChildren().add(button);
            
            //disables button if AI is dead
            if (h.getHealth() <= 0){
                button.setDisable(true);
            }
            
            button.setOnAction(e -> {

                //if guarded last turn, makes it eligible to guard again next turn
                if(Guard.containsUnGuardable(Game.getTeam1().get(Game.getTeamTurn()))){
                    Guard.removeUnGuardable(Game.getTeam1().get(Game.getTeamTurn()));
                }

                Game.addAttack(Game.getTeam1().get(Game.getTeamTurn()), Game.getTeam2().get(options.indexOf(finalButton)));
                //apply old layout back
                hBox.getChildren().set(1, flowPane);

                //removes guard if guard was used last turn
                if (Guard.containsUnGuardable(Game.getTeam1().get(Game.getTeamTurn()))){
                    Guard.removeUnGuardable(Game.getTeam1().get(Game.getTeamTurn()));
                }

                //if there are more actions left for the player
                if(team1Alive()) {
                    //new name label
                    Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
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
        for(int i = start; i < Game.getTeam1().size(); i++){
            if(Game.getTeam1().get(i).getHealth() > 0){
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
        itemChoice.getItems().addAll("Attack", "Defence", "Healing", "Mana");


        itemChoice.setValue("Attack");

        //label
        Label optionsLabel = new Label("Who Will You Use The Item On?");

        //storing buttons so I can reference them later
        ArrayList<Button> options = new ArrayList<Button>();
        Button button;

        for(Hero h : Game.getTeam1()){
            button = new Button(h.getName());
            button.setPrefSize(80, 40);
            options.add(button);
            Button finalButton = button;
            optionBox.getChildren().add(button);
            button.setOnAction(e -> {

                //if guarded last turn, makes it eligible to guard again next turn
                if(Guard.containsUnGuardable(Game.getTeam1().get(Game.getTeamTurn()))){
                    Guard.removeUnGuardable(Game.getTeam1().get(Game.getTeamTurn()));
                }

                //used for battle log
                Game.setItemLastUsedOn(Game.getTeam1().get(options.indexOf(finalButton)));
                switch (itemChoice.getValue().toString()){
                    case "Attack":
                        Game.getTeam1().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.ATTACK));
                        break;
                    case "Defence":
                        Game.getTeam1().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.DEFENCE));
                        break;
                    case "Healing":
                        Game.getTeam1().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.HEALING));
                        break;
                    case "Mana":
                        Game.getTeam1().get(options.indexOf(finalButton)).useItem(new Item(Item.ItemType.MANA));
                        break;
                }
                //apply old layout back
                hBox.getChildren().set(1, flowPane);

                //if there are more actions left for the player
                if(team1Alive()) {
                    //new name label
                    Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
                }
                else {
                    AI();
                }
            });
        }
        //for preventing user from using mana potions on classes without mana, so I gray out those buttons
        itemChoice.setOnAction(e -> {
            if(itemChoice.getValue().equals("Mana")){
                for(Button b : options){
                    if(!(Game.getTeam1().get(options.indexOf(b)) instanceof Wizard) &&
                            !(Game.getTeam1().get(options.indexOf(b)) instanceof Healer)){
                        b.setDisable(true);
                    }
                    //also gray out button if hero has died
                    if(Game.getTeam1().get(options.indexOf(b)).getHealth() <= 0){
                        b.setDisable(true);
                    }
                }
            }
            else{
                for(Button b : options){
                    b.setDisable(false);
                }
            }
        });
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
        hBox.setStyle("-fx-background-color: indianred;");

        for(int i = 0; i < 3; i++){
            VBox vBox = new VBox(20);
            vBox.setPadding(new Insets(10, 20, 10, 20));

            //drop down list
            ComboBox heroList = new ComboBox(FXCollections.observableArrayList(heroTypes));
            heroList.setPromptText("Select a Hero");
            Game.getHeroSelections().add(heroList);

            //enter hero name
            TextField nameField = new TextField();
            nameField.setPromptText("Enter a Name");
            nameField.setFont(new Font("Traditional Arabic", 16));
            Game.getHeroNames().add(nameField);

            Label namePrompt = new Label("Hero Name:");
            namePrompt.setFont(new Font("Traditional Arabic", 20));

            //compile in a layout
            vBox.getChildren().addAll(heroList, namePrompt, nameField);

            //add to main horizontal layout
            hBox.getChildren().add(vBox);
        }
        //button to enter results
        Button enterButton = new Button("Battle!");
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

        //button method
        enterButton.setOnAction(e ->{
            boolean valid = true;

            //loop to check for duplicate hero selections
            try{
                outer_loop:
                for (ComboBox c : Game.getHeroSelections()) {
                    for (ComboBox c2 : Game.getHeroSelections()) {
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
                    switch (Game.getHeroSelections().get(i).getValue().toString()) {
                        case "Wizard":
                            Game.getTeam1().add(new Wizard(Game.getHeroNames().get(i).getText()));
                            break;
                        case "Archer":
                            Game.getTeam1().add(new Archer(Game.getHeroNames().get(i).getText()));
                            break;
                        case "Knight":
                            Game.getTeam1().add(new Knight(Game.getHeroNames().get(i).getText()));
                            break;
                        case "Assassin":
                            Game.getTeam1().add(new Assassin(Game.getHeroNames().get(i).getText()));
                            break;
                        case "Healer":
                            Game.getTeam1().add(new Healer(Game.getHeroNames().get(i).getText()));
                            break;
                    }
                }


                //AI creates team
                //keep track of what AI has chosen
                ArrayList<Integer> picks = new ArrayList<Integer>();
                for(int i = 0; i < 3; i++){
                    Game.getTeam2().add(AIPick(i, picks));
                }
                System.out.println(Game.getTeam2().toString());

                //makes window show the battle scene
                MainMenu.getMainStage().setScene(Hub.hubScene());
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

                Game.getTeam1().get(i).getHealthBar().getHealthInfo().setText(Game.getTeam1().get(i).getName() + ": " +
                        Game.getTeam1().get(i).getHealth() +
                        "/" + (int) Game.getTeam1().get(i).getHealthBar().getMaxHealth());
                Game.getTeam1().get(i).getHealthBar().getGreenBar().setFill(Color.GREEN);
                completeBar = new Pane();
                completeBar.getChildren().addAll(mainBar, Game.getTeam1().get(i).getHealthBar().getGreenBar());

                //adding it to the layout
                box.getChildren().addAll(Game.getTeam1().get(i).getHealthBar().getHealthInfo(), completeBar);
            }
            //for second team
            else{
                Game.getTeam2().get(i).getHealthBar().getHealthInfo().setText(Game.getTeam2().get(i).getName() + ": " +
                        Game.getTeam2().get(i).getHealth() +
                        "/" + (int) Game.getTeam2().get(i).getHealthBar().getMaxHealth());
                Game.getTeam2().get(i).getHealthBar().getGreenBar().setFill(Color.GREEN);
                completeBar = new Pane();
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
                rand = random.nextInt(Game.getTeam1().size());
                //don't want to attack a hero that's already dead
                if(Game.getTeam1().get(rand).getHealth() > 0){
                    break;
                }
            }

            Game.addAttack(Game.getTeam2().get(i), Game.getTeam1().get(rand));
        }
        Game.commitAttacks();

        //update health bars
        //team1
        for(Hero h : Game.getTeam1()){
            h.updateHealthBar();
            System.out.println("team 1: " + h.getHealth());
            System.out.println("Width: " + h.getHealthBar().getGreenBar().getWidth());
        }
        for(Hero h : Game.getTeam2()){
            h.updateHealthBar();
            System.out.println("team 2: " + h.getHealth());
            System.out.println("Width: " + h.getHealthBar().getGreenBar().getWidth());
        }

        //checks if a team is dead
        if(checkTeamDead(Game.getTeam2())){
            System.out.println("you win");
        } else if (checkTeamDead(Game.getTeam1())){
            System.out.println("you lose");
        }

        //for next turn
        Game.setTurn(Game.getTurn() + 1);
        Game.getBattleLog().getItems().add("Turn " + Game.getTurn() + ":");
        for(int i = 0; i < Game.getTeam1().size(); i++) {
            //sets it as first alive hero
            if(Game.getTeam1().get(i).getHealth() > 0){
                Game.setTeamTurn(i);
                break;
            }
        }

        //clears immune
        Guard.clearImmune();
        Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
    }

    //guard button method ----------------------------------------------------------------------------------------------
    public static void guardButtonMethod(){
        //checks if the player guarded last turn
        if (Guard.containsUnGuardable(Game.getTeam1().get(Game.getTeamTurn()))){
            Game.getBattleLog().getItems().add(Game.getTeam1().get(Game.getTeamTurn()).getName() + " Cannot Guard in a Row!");
        } else {
            Guard.addGuard(Game.getTeam1().get(Game.getTeamTurn()));

            //if there are more actions left for the player
            if(team1Alive()) {
                //new name label
                Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
            } else {
                AI();
            }
        }
    }

    public static Hero AIPick(int i, ArrayList<Integer> picks){

        Random rand = new Random();
        int r;
        while(true) {
            r = rand.nextInt(5);
            if(!picks.contains(r)){
                picks.add(r);
                break;
            }
        }
        Hero hero = null;
        String name = "AI " + i;

        switch(r){
            case 0:
                hero = new Wizard(name);
                break;
            case 1:
                hero = new Archer(name);
                break;
            case 2:
                hero = new Knight(name);
                break;
            case 3:
                hero = new Assassin(name);
                break;
            case 4:
                hero = new Healer(name);
                break;
        }

        return hero;
    }
}