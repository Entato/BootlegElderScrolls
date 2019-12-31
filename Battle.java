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


public class Battle extends Application{
    static Scanner reader = new Scanner(System.in);

    //GUI METHODS
    //start method -----------------------------------------------------------------------------------------------------
    public void start(Stage primaryStage) throws Exception{
        Stage battleStage = primaryStage;
        battleStage.setTitle("Fantasy Showdown");

        boolean run = MainMenu.display();

        //if player pressed start on menu
        if(run){
            battleStage.setScene(createHeroSelectionScene(battleStage));
            battleStage.show();
        }
    }

    //creating battle scene method -------------------------------------------------------------------------------------
    public Scene createBattleScene(){

        //border pane layout allows for stacking layout easily
        BorderPane borderPane = new BorderPane();

        //bottom portion layout
        HBox bottomBox = new HBox(20);
        bottomBox.setStyle("-fx-background-color: GOLD;");
        bottomBox.setPrefHeight(200);

        //action layout
        FlowPane battleActions = new FlowPane();
        battleActions.setPrefSize(400, 200);
        battleActions.setHgap(40);
        battleActions.setVgap(20);
        battleActions.setAlignment(Pos.CENTER);
        battleActions.setPadding(new Insets(20, 20, 20, 20));


        //action buttons and labels
        Button attackButton = new Button("Attack");
        attackButton.setPrefSize(150, 60);
        Button guardButton = new Button("Guard");
        guardButton.setPrefSize(150, 60);
        Button itemButton = new Button("Use Item");
        itemButton.setPrefSize(150, 60);
        Button specialButton = new Button("Special");
        specialButton.setPrefSize(150, 60);

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
        VBox team1Bars = new VBox(30);
        team1Bars.setPadding(new Insets(20, 20, 20, 20));
        team1Bars.setAlignment(Pos.CENTER);
        setBars(team1Bars, true);
        //team2
        VBox team2Bars = new VBox(30);
        team2Bars.setPadding(new Insets(20, 20, 20, 20));
        team2Bars.setAlignment(Pos.CENTER);
        setBars(team2Bars, false);

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

        //adds buttons to layouts
        battleActions.getChildren().addAll(attackButton, guardButton, itemButton, specialButton);
        bottomBox.getChildren().addAll(bottomLeftBox, battleActions);

        //adds layouts to border pane
        borderPane.setBottom(bottomBox);
        borderPane.setLeft(team1Bars);
        borderPane.setRight(team2Bars);

        //creates scene and returns it
        Scene battleScene = new Scene(borderPane, 900, 550);

        return battleScene;
    }

    //attack button method ---------------------------------------------------------------------------------------------
    public void attackButtonMethod(HBox hBox, FlowPane flowPane){
        //layout
        VBox bigBox = new VBox(20);
        bigBox.setPadding(new Insets(20, 20, 20, 20));
        HBox optionBox = new HBox(20);
        optionBox.setPadding(new Insets(10, 10, 10, 10));
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setStyle("-fx-border-color: black");

        //label
        Label optionsLabel = new Label("Who Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Attack?");

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
                Game.addAttack(Game.getTeam1().get(Game.getTeamTurn()), Game.getTeam2().get(options.indexOf(finalButton)));
                //apply old layout back
                hBox.getChildren().set(1, flowPane);

                //if there are more actions left for the player
                if(Game.getTeamTurn() < 2) {
                    //increase counter for next turn
                    Game.setTeamTurn(Game.getTeamTurn() + 1);

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

    public void itemButtonMethod(HBox hBox, FlowPane flowPane){
        //layout
        VBox bigBox = new VBox(20);
        bigBox.setPadding(new Insets(20, 20, 20, 20));
        HBox optionBox = new HBox(20);
        optionBox.setPadding(new Insets(10, 10, 10, 10));
        optionBox.setAlignment(Pos.CENTER);
        optionBox.setStyle("-fx-border-color: black");



        ChoiceBox itemChoice = new ChoiceBox();

        itemChoice.setMaxWidth(50);
        itemChoice.getItems().addAll("Attack", "Defence", "Healing", "Mana");
        //only wizard and healer have mana

        itemChoice.setValue("Attack");

        //label
        Label optionsLabel = new Label("Who Will You Use The Item On?");

        //storing buttons so I can reference them later
        ArrayList<Button> options = new ArrayList<Button>();
        Button button;

        for(Hero h : Game.getTeam1()){
            button = new Button(h.getName());
            button.setPrefSize(100, 40);
            options.add(button);
            Button finalButton = button;
            optionBox.getChildren().add(button);
            button.setOnAction(e -> {
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
                if(Game.getTeamTurn() < 2) {
                    //increase counter for next turn
                    Game.setTeamTurn(Game.getTeamTurn() + 1);
                    //new name label
                    Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
                }
            });
        }
        //for preventing user from using mana potions on classes without mana
        itemChoice.setOnAction(e -> {
            if(itemChoice.getValue().equals("Mana")){
                for(Button b : options){
                    if(!(Game.getTeam1().get(options.indexOf(b)) instanceof Wizard) &&
                            !(Game.getTeam1().get(options.indexOf(b)) instanceof Healer)){
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
    public Scene createHeroSelectionScene(Stage stage){
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

                //makes window show the battle scene
                stage.setScene(createBattleScene());
            }
        });

        return selectionScene;
    }
    //setting health bars method ---------------------------------------------------------------------------------------
    public static void setBars(VBox box, boolean team){
        Rectangle mainBar;
        Rectangle greenBar;

        for(int i = 0; i < Game.getTeam1().size(); i++){
            Label healthInfo;
            //creating health bar

            mainBar = new Rectangle(100, 20);
            mainBar.setStroke(Color.BLACK);
            mainBar.setStrokeWidth(5);


            //for first team
            if(team) {
                greenBar = Game.getTeam1().get(i).getHealthBar().adjustGreenBar(Game.getTeam1().get(i).getHealth());

                healthInfo = new Label(Game.getTeam1().get(i).getName() + ": " +
                        Game.getTeam1().get(i).getHealth() +
                        "/" + Game.getTeam1().get(i).getHealthBar().getMaxHealth());
            }
            //for second team
            else{
                greenBar = Game.getTeam2().get(i).getHealthBar().adjustGreenBar(Game.getTeam2().get(i).getHealth());

                healthInfo = new Label(Game.getTeam2().get(i).getName() + ": " +
                        Game.getTeam2().get(i).getHealth() +
                        "/" + Game.getTeam2().get(i).getHealthBar().getMaxHealth());
            }
            greenBar.setFill(Color.GREEN);

            Pane completeBar = new Pane();
            completeBar.getChildren().addAll(mainBar, greenBar);

            //adding it to the layout
            box.getChildren().addAll(healthInfo, completeBar);
        }
    }

    public void AI(){
        Random random = new Random();
        int rand;
        //randomly picks hero to attack
        for(int i = 0; i < Game.getTeam2().size(); i++){
            if (Game.getTeam2().get(i).getHealth() <= 0){
                continue;
            }

            rand = random.nextInt(Game.getTeam1().size());

            Game.addAttack(Game.getTeam2().get(i), Game.getTeam1().get(rand));
        }
        Game.commitAttacks();
        Game.setTeamTurn(0);

        Game.getNameLabel().setText("What Will " + Game.getTeam1().get(Game.getTeamTurn()).getName() + " Do?");
    }
    //main method ------------------------------------------------------------------------------------------------------
    public static void main(String[] args){
        //GUI
        //launches GUI (start method)
        launch(args);





        
        ArrayList<Hero> team1 = new ArrayList<Hero>();
        ArrayList<Hero> team2 = new ArrayList<Hero>();
        ArrayList<Integer> picks = new ArrayList<Integer>();
        int turn = 1;

        System.out.println("Choose your heroes");

        for(int i = 0; i < 3; i++){
            team1.add(selectHero());
            team2.add(AIPick(i, picks));
            System.out.println(team2.get(i).toString());
        }
        while(true) {
            System.out.println("\n========================\nIt is now turn " + turn);
            Guard.clearImmune();
            Game.clearAttacks();
            printBattleField(team1, team2);
            action(team1, team2);
            AIAction(team1, team2);
            Game.commitAttacks();
            checkIfDead(team1, team2);
            gameOver(team1, team2);
            turn ++;
        }

    }
    public static Hero selectHero(){
        boolean valid = false;
        Hero hero = null;
        while(!valid) {
            System.out.println("Select your class");
            String input = reader.nextLine();

            //Switch statement
            switch (input) {
                case "Wizard":
                    hero = new Wizard(askName());
                    valid = true;
                    break;
                case "Archer":
                    hero = new Archer(askName());
                    valid = true;
                    break;
                case "Knight":
                    hero = new Knight(askName());
                    valid = true;
                    break;
                case "Assassin":
                    hero = new Assassin(askName());
                    valid = true;
                    break;
                case "Healer":
                    hero = new Healer(askName());
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid Name");
            }
        }

        return hero;
    }
    public static String askName(){
        System.out.println("Enter your name");
        String name = reader.nextLine();

        return name;
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

    public static void action(ArrayList<Hero> team1, ArrayList<Hero> team2){
        Scanner ints = new Scanner(System.in);
        for(int i = 0; i < team1.size(); i++){
            System.out.println("What would you like to do with " + team1.get(i).getName() + "?");
            String action = reader.nextLine();

            if(action.equalsIgnoreCase("Guard")){
                if(Guard.containsUnGuardable(team1.get(i))){
                    System.out.println("You cannot guard twice in a row!");
                    i--;
                    continue;
                } else {
                    Guard.addUnGuardable(team1.get(i));
                    Guard.addImmune(team1.get(i));
                }
            }
            else if(action.equalsIgnoreCase("Attack")){
                System.out.println("What index do you want to attack (MAY VARY)");
                int x = ints.nextInt();
                Game.addAttack(team1.get(i), team2.get(x));

                //lets hero be ready to guard again next turn
                if(Guard.containsUnGuardable(team1.get(i))){
                    Guard.removeUnGuardable(team1.get(i));
                }
            }
            else if(action.equalsIgnoreCase("Item")){
                System.out.println("What item do you want to use?");
                String item = reader.nextLine();
                if(item.equalsIgnoreCase("Healing")){
                    team1.get(i).useItem(new Item(Item.ItemType.HEALING));
                }
                else if(item.equalsIgnoreCase("Attack")){
                    team1.get(i).useItem(new Item(Item.ItemType.ATTACK));
                }
                //will error if you try to use on class without mana but I won't fix since GUI will redo this part
                else if(item.equalsIgnoreCase("Mana")){
                    team1.get(i).useItem(new Item(Item.ItemType.MANA));
                }
                else if(item.equalsIgnoreCase("Defence")){
                    team1.get(i).useItem(new Item(Item.ItemType.DEFENCE));
                }
            }

        }

    }

    public static void AIAction(ArrayList<Hero> player, ArrayList<Hero> AI){
        ArrayList<Integer> selected = new ArrayList<Integer>();
        Random random = new Random();
        int rand;
        //randomly picks hero to attack
        for(int i = 0; i < AI.size(); i++){
            if (AI.get(i).getHealth() <= 0){
                continue;
            }
            while(true){
                rand = random.nextInt(player.size());
                if(!selected.contains(rand)){
                    selected.add(rand);
                    break;
                }
            }
            Game.addAttack(AI.get(i), player.get(rand));
        }

    }

    public static void printBattleField(ArrayList<Hero> team1, ArrayList<Hero> team2){
        System.out.println("\nTeam 1:");
        for(Hero h : team1){
            System.out.println(h.toString());
        }
        System.out.println("\nTeam 2:");
        for(Hero h : team2){
            System.out.println(h.toString());
        }
    }

    public static void checkIfDead(ArrayList<Hero> team1, ArrayList<Hero> team2){

        for(int i = 0; i < team1.size(); i++){
            if(team1.get(i).getHealth() <= 0){
                System.out.println(team1.get(i).getName() + " has died!");
                team1.remove(team1.get(i));
                i--;
            }
        }

        for(int i = 0; i < team2.size(); i++){
            if(team2.get(i).getHealth() <= 0){
                System.out.println(team2.get(i).getName() + " has died!");
                team2.remove(team2.get(i));
                i--;
            }
        }

    }

    public static boolean gameOver(ArrayList<Hero> team1, ArrayList<Hero> team2){
        boolean gg = false;
        if(team1.isEmpty()){
            System.out.println("Player 1's team has been wiped out! Player 2 has won the game!");
            gg = true;
        }
        if(team2.isEmpty()){
            System.out.println("Player 2's team has been wiped out! Player 1 has won the game!");
            gg = true;
        }

        return gg;
    }




}