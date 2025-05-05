package main;

import entities.Entity;
import exceptions.*;
import game.Battle;
import game.EntityManager;
import simulator.BattleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that holds all the logic for managing user input and user interface
 * Developed by: Luis Marin
 */
public class PlayerInterface {

    private final Scanner userInput;

    private final BattleManager battleManager;
    private final EntityManager entityManager;

    private final Random random;

    private boolean gameOn;

    /**
     * Creates a new player interface to be played inside the console.
     */
    public PlayerInterface(){
        userInput = new Scanner(System.in);
        gameOn = true;
        battleManager = new BattleManager();
        entityManager = new EntityManager();
        random = new Random();

        System.out.println("Welcome to the Fantasy Battle Simulator!");
        System.out.println("Press Enter to start!");
        userInput.nextLine();

        while(gameOn){
            System.out.println();

            int option = selectMenuOption();

            switch(option){
                case 1:
                    createBattle();
                    break;
                case 2:
                    initBattle();
                    break;
                case 0:
                    turnOff();
                    break;
            }

        }
    }

    /**
     * Prompts the user the main menu of the game.
     *
     * @return The menu option selected by the user.
     */
    private int selectMenuOption(){
        System.out.println("=== MENU ===");
        System.out.println("1. Create a Battle");
        System.out.println("2. Start Battle");
        System.out.println("0. Exit");


        try{
            System.out.print("\nOption: ");
            return Integer.parseInt(userInput.nextLine());
        }catch (NumberFormatException e){
            System.out.println("Wrong menu option!");
        }

        return -1;
    }

    /**
     * Prompts the user to create a new battle by adding characters in its team and the enemy's team.
     */
    private void createBattle(){
        System.out.println("\nCreating Battle...\n");

        boolean continueCreating = true;
        List<Entity> team1 = new ArrayList<>();

        System.out.println("Select your team:");

        do{
            System.out.println("Select the character: ");
            Entity character = selectCharacter();

            team1.add(character);

            System.out.println("\nAdd another character? (Y = Yes) (Enter = No)");

            String input = userInput.nextLine();
            System.out.println();

            if(!input.equalsIgnoreCase("y")){
                continueCreating = false;
            }

        }while(continueCreating);


        System.out.println();

        System.out.println("Select your opponent team:");

        continueCreating = true;
        List<Entity> team2 = new ArrayList<>();
        do{
            System.out.println("Select the character: ");
            Entity character = selectCharacter();

            team2.add(character);

            System.out.println("\nAdd another character? (Y = Yes) (Enter = No)");

            String input = userInput.nextLine();
            System.out.println();

            if(!input.equalsIgnoreCase("y")){
                continueCreating = false;
            }

        }while(continueCreating);



        this.battleManager.setBattle(new Battle(team1 , team2));

        System.out.println("\nBattle created!");

    }

    /**
     * Prompts the user to select a character from the registered ones.
     * Check game.EntityManager for more info.
     * @return Entity selected by the user.
     */
    private Entity selectCharacter(){

        Entity character = null;

        do{
            try{
                int counter = 1;

                for(Entity entity : entityManager.getRegisteredEntities()){
                    System.out.println(counter + " - " + entity.getClass().getSimpleName());
                    counter++;
                }

                System.out.print("Character: ");
                String input = userInput.nextLine();
                int selectedCharacter = Integer.parseInt(input);

                selectedCharacter--;

                character = entityManager.getNewEntity(selectedCharacter);

            }catch(NumberFormatException e){
                System.out.println("Wrong input, please input the character number you want to select");
            }catch(IndexOutOfBoundsException e){
                System.out.println("Wrong character selection! Try again");
            }
        }while(character == null);

        return character;
    }

    /**
     * Initializes the battle that was previously created
     * If no battle was created, no battle is initialized, duh.
     */
    private void initBattle(){
        try {
            this.battleManager.startBattle();
            System.out.println("\nStarting battle!\n");
            battleLoop();

        } catch (NoBattleAvailableException | BattleOverException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Main battle loop where the Battle inside the BattleManager is developed.
     */
    private void battleLoop(){
        do {

            battleManager.printBattleState();
            System.out.println();

            if(battleManager.getTurn()){
                managePlayerTurn();
            }else{
                manageIATurn();
            }

            System.out.println("Press Enter to continue");
            userInput.nextLine();

            battleManager.switchTurn();
        }while(!battleManager.isBattleOver());

        System.out.println(battleManager.getWinner());
    }

    /**
     * Prompts the user the inputs and information required to play its turn based on the current game state given
     * by the BattleManager
     */
    private void managePlayerTurn(){
        System.out.println("Your turn!\n");

        //Select which Character to play
        Entity character = null;
        do{
            try{
                System.out.println("Select a character: ");
                int counter = 1;
                for(Entity entity : battleManager.getTeam1()){
                    System.out.println(counter + " - " + entity.getClass().getSimpleName());
                    counter++;
                }

                System.out.print("Character: ");
                character = battleManager.selectTurnCharacter(getPlayerInputInteger());
                System.out.println();

            } catch (DefeatedCharacterException | InvalidCharacterPositionException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }while(character == null);

        //Select what the Character at play will do
        int option;

        do {
            try{
                System.out.println("What will " + character.getClass().getSimpleName() + " do?");
                System.out.println("1. Attack");
                System.out.println("2. Special Ability");
                System.out.println("3. Defend");
                System.out.print("Option: ");
                option = getPlayerInputInteger();
                System.out.println();

                if(option < 1 || option > 3){
                    System.out.println("Wrong option, try again!");
                }

                if(option == 2){
                    battleManager.specialAbility();
                }
            }catch(OutOfEnergyException | InvalidInputException e){
                System.out.println();
                System.out.println(e.getMessage());
                System.out.println();
                option = -1;
            }
        }while(option < 1 || option > 3);

        if(option != 3){
            //Select the target IF applicable
            Entity targetCharacter = null;
            do{
                try{
                    System.out.println("Select a target: ");
                    int counter = 1;
                    for(Entity entity : battleManager.getTeam2()){
                        System.out.println(counter + " - " + entity.getClass().getSimpleName());
                        counter++;
                    }

                    System.out.print("Target: ");
                    targetCharacter = battleManager.selectTargetCharacter(getPlayerInputInteger());
                    System.out.println();

                } catch (DefeatedCharacterException | InvalidCharacterPositionException | InvalidInputException e) {
                    System.out.println("\n" + e.getMessage() + "\n");
                }
            }while(targetCharacter == null);

            int damageDealt = battleManager.playAttack(character , targetCharacter);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(damageDealt > 0){
                System.out.println("\nYou dealt " + damageDealt + " damage");
            }else if(damageDealt == 0){
                System.out.println(targetCharacter.getClass().getSimpleName() + " Defended the attack!");
            }else{
                System.out.println(targetCharacter.getClass().getSimpleName() + " Dodged the attack!");
            }
        }else{
            battleManager.playDefense();
        }

        System.out.println();
    }

    /**
     * Manages the turn of the 'IA' which is a chance based IA where every decision is made based on the Java Random API.
     */
    private void manageIATurn(){
        System.out.println("Enemy's turn!\n");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Selects a random character to play
        Entity character = null;
        do{
            try{

                character = battleManager.selectTurnCharacter(random.nextInt(battleManager.getTeam2().size() + 1));

            } catch (DefeatedCharacterException | InvalidCharacterPositionException e) {
                //Not showing AI mistakes
            }
        }while(character == null);

        //Select what the Character at play will do
        int option = random.nextInt(3) + 1;
        String characterName = character.getClass().getSimpleName();

        switch(option){
            case 1:
                System.out.println(characterName + " is attacking!");
                break;
            case 2:
                try{
                    battleManager.specialAbility();
                    System.out.println(characterName + " used a special ability!");
                } catch (OutOfEnergyException e) {
                    System.out.println(characterName + " is attacking!");
                    option = 1;
                }
                break;
            case 3:
                System.out.println(characterName + " is defending!");
                break;
        }


        if(option != 3){
            //Select the target IF applicable
            Entity targetCharacter = null;
            do{
                try{
                    targetCharacter = battleManager.selectTargetCharacter(random.nextInt(battleManager.getTeam1().size() + 1));

                } catch (DefeatedCharacterException | InvalidCharacterPositionException e) {
                    //Not showing AI mistakes
                }
            }while(targetCharacter == null);

            int damageDealt = battleManager.playAttack(character , targetCharacter);

            if(damageDealt > 0){
                System.out.println("Enemy dealt " + damageDealt + " damage");
            }else if(damageDealt == 0){
                System.out.println(targetCharacter.getClass().getSimpleName() + " Defended the attack!");
            }else{
                System.out.println(targetCharacter.getClass().getSimpleName() + " Dodged the attack!");
            }
        }else{
            battleManager.playDefense();
        }

        System.out.println();
    }

    /**
     * Indicates to this interface that it should shut down, stopping the main loop of this interface.
     */
    private void turnOff(){
        this.gameOn = false;
        System.out.println("\nClosing Program. . .");
    }

    /**
     * Utility method used to serve the same function as Scanner.nextInt() but with a different approach to
     * be able to control the exceptions thrown.
     * @return Integer inputted by the user.
     * @throws InvalidInputException Exception if the user tries to input something that it's not a number.
     */
    private int getPlayerInputInteger() throws InvalidInputException {
        int input;
        try{
            input =  Integer.parseInt(userInput.nextLine());
        }catch (NumberFormatException e){
            throw new InvalidInputException("Not a number, try again!");
        }

        return input;
    }
}
