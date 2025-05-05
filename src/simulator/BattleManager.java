package simulator;

import entities.Entity;
import exceptions.DefeatedCharacterException;
import exceptions.InvalidCharacterPositionException;
import exceptions.NoBattleAvailableException;
import exceptions.OutOfEnergyException;
import game.Battle;
import game.EntityManager;
import modifiers.Defense;

import java.util.List;
import java.util.Random;

/**
 * Class that wraps a Battle to manage its states and provides the complex functionality of a battle taking
 * into consideration all the entities, modifiers and game states of the battle.
 *
 * Developed by: Luis Marin
 */
public class BattleManager {

    private Battle battle;
    private EntityManager entityManager;
    //Defines weather is Team 1 turn or Team 2 (True or False respectively )
    private boolean turn;

    Random random;

    private Entity characterTurn;
    private Entity characterTarget;

    /**
     * Creates a new Battle Manager with an empty battle that must be added later in order to function.
     */
    public BattleManager() {
        this.entityManager = new EntityManager();
        this.random = new Random();
        characterTurn = null;
        characterTarget = null;
    }

    /**
     * Starts the battle inside this BattleManager by randomly selecting the team who has the first turn.
     * @throws NoBattleAvailableException If no battle is inside this BattleManager yet
     */
    public void startBattle() throws NoBattleAvailableException {

        if(this.battle == null){
            throw new NoBattleAvailableException("No battle has been created yet!");
        }

        if(this.battle.isBattleOver()){
            throw new BattleOverException("The current battle is over!");
        }

        //Randomly assigns first turn
        turn = random.nextInt(2) == 0;
    }

    /**
     * Selects and sets in this game state the character who is going to be active this turn.
     * Meaning the character who perform its actions.
     * @param position Position inside the formation of the Entity inside the team who is at turn.
     * @return Entity at the given position of the team at turn.
     * @throws DefeatedCharacterException If the wanted character is defeated.
     * @throws InvalidCharacterPositionException If the position is out of bounds of the formation size.
     */
    public Entity selectTurnCharacter(int position) throws DefeatedCharacterException, InvalidCharacterPositionException {

        if(turn){
            this.characterTurn = battle.getCharacterTeam1(position);
        }else{
            this.characterTurn = battle.getCharacterTeam2(position);
        }


        return characterTurn;
    }

    /**
     * Selects and sets in this game state the character who is the target of the character at turn.
     * Meaning the target character of the current character at turn.
     * @param position Position inside the formation of the Entity inside the target team.
     * @return Entity at the given position of the target team.
     * @throws DefeatedCharacterException If the wanted character is defeated.
     * @throws InvalidCharacterPositionException If the position is out of bound of the formation size.
     */
    public Entity selectTargetCharacter(int position) throws DefeatedCharacterException, InvalidCharacterPositionException {

        if(turn){
            this.characterTarget = battle.getCharacterTeam2(position);
        }else{
            this.characterTarget = battle.getCharacterTeam1(position);
        }


        return characterTarget;
    }

    /**
     * Plays the attack of the Character at the Target to be reviewed and performed inside the Battle of this Manager.
     * @param character Character at Turn.
     * @param target Target character
     * @return Result damage to the attack.
     */
    public int playAttack(Entity character , Entity target){
        return battle.playAttack(target , character.getDamage());
    }

    /**
     * Plays the defense action of the Character at turn
     */
    public void playDefense(){
        this.characterTurn.addModifier(new Defense(5));
    }

    /**
     * Performs the special ability of the Character at turn.
     * @throws OutOfEnergyException If the Character is out of energy.
     */
    public void specialAbility() throws OutOfEnergyException {
        battle.useSpecialAbility(this.characterTurn);
    }

    /**
     * Returns at the base that of the Battle where there is no Character at turns neither target Character
     */
    private void flushTurn(){
        this.characterTurn = null;
        this.characterTarget = null;
    }

    /**
     * Flips the turn based on the current state of the Battle.
     */
    public void switchTurn(){
        turn = !turn;
        flushTurn();
        checkBattleStatus();
        battle.updateTurn();
    }

    /**
     * Updates the Battle status given by the Battle in this manager.
     */
    private void checkBattleStatus(){
        battle.updateStatus();
    }

    /**
     * Prints each team formation information.
     */
    public void printBattleState(){
        System.out.println("==========");
        System.out.println("Team 1:");
        for(Entity character : battle.getTeam1()){

            if(character.getEnergy() == 5){
                System.out.print(character + " *\n");
            }else{
                System.out.println(character);
            }
        }

        System.out.println("----------");

        System.out.println("Team 2:");
        for(Entity character : battle.getTeam2()){

            if(character.getEnergy() == 5){
                System.out.print(character + " *\n");
            }else{
                System.out.println(character);
            }
        }

        System.out.println("==========");
    }

    /**
     * For debug purposes only
     *
     * Used for automatic gameplay
     */
    public void nextTurn(){
        Entity character;

        if(turn){
            character = battle.getNextTeam1();
        }else{
            character = battle.getNextTeam2();
        }

        turn = !turn;
    }


    /**
     * Returns whether the battle is over or not.
     * @return True if the battle is over, False otherwise
     */
    public boolean isBattleOver(){
        return battle.isBattleOver();
    }

    /**
     * Returns a descriptive winner of the match.
     * @return Descriptive text of the winner of the match.
     */
    public String getWinner(){
        if(battle.getWinner()){
            return "Team 1 Wins!";
        }else{
            return "Team 2 Wins!";
        }
    }

    /**
     * Set the Battle to be managed by this Manager.
     * @param battle
     */
    public void setBattle(Battle battle){
        this.battle = battle;
    }

    /**
     * Returns the formation of team 1.
     * @return List of Entities in team 1.
     */
    public List<Entity> getTeam1(){
        return battle.getTeam1();
    }

    /**
     * Returns the formation of team 2.
     * @return List of Entities in team 2.
     */
    public List<Entity> getTeam2(){
        return battle.getTeam2();
    }

    /**
     * Returns the team whose turn is on based on the current game state.
     * @return TRUE for team 1, FALSE for team 2.
     */
    public boolean getTurn(){
        return turn;
    }
}
