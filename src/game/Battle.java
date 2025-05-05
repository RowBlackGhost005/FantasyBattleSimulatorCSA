package game;

import entities.Entity;
import exceptions.DefeatedCharacterException;
import exceptions.InvalidCharacterPositionException;
import exceptions.OutOfEnergyException;

import java.util.List;
import java.util.Random;

/**
 * Battle class that holds the information and logic for a battle to be executed.
 *
 * Developed by: Luis Marin
 */
public class Battle {

    private List<Entity> team1;
    private List<Entity> team2;

    private int teamOneTurn;
    private int teamTwoTurn;

    private boolean battleOver;

    private boolean winner = false;

    Random random;

    /**
     * Creates a new battle with the given teams.
     * @param team1 Characters in team 1.
     * @param team2 Character in team 2.
     */
    public Battle(List<Entity> team1, List<Entity> team2) {
        this.team1 = team1;
        this.team2 = team2;
        battleOver = false;
        teamOneTurn = 0;
        teamTwoTurn = 0;
        random = new Random();
    }

    /**
     * Returns team 1.
     * @return List of entities in team 1.
     */
    public List<Entity> getTeam1() {
        return team1;
    }

    /**
     * Returns team 2.
     * @return List of entities in team 2.
     */
    public List<Entity> getTeam2() {
        return team2;
    }

    /**
     * Returns whether the battle is over or not
     * (All characters in one team defeated)
     * @return True if the battle is over, False otherwise
     */
    public boolean isBattleOver() {
        return battleOver;
    }

    /**
     * Checks if the battle has change is state
     */
    public void updateStatus(){
        if(team1.stream().allMatch(Entity::isDefeated)){
            battleOver = true;
            winner = false;
        }

        if(team2.stream().allMatch(Entity::isDefeated)){
            battleOver = true;
            winner = true;
        }
    }

    /**
     * Returns the winner team
     * @return TRUE if team 1 won, FALSE if team 2 won.
     */
    public boolean getWinner(){
        return winner;
    }

    /**
     * Returns the Entity that sits at the given index in team 1.
     * @param index Index to retrieve the Entity from.
     * @return Entity that sits on Index.
     * @throws InvalidCharacterPositionException If the given index is out of bounds of the current team size.
     * @throws DefeatedCharacterException If the Entity at the given index is defeated.
     */
    public Entity getCharacterTeam1(int index) throws InvalidCharacterPositionException, DefeatedCharacterException {
        index--;
        if(index >= team1.size() || index < 0){
            throw new InvalidCharacterPositionException("No player in such position");
        }

        Entity character = team1.get(index);

        if(character.isDefeated()){
            throw new DefeatedCharacterException("The character is defeated!");
        }

        return character;
    }

    /**
     * Returns the Entity that sits at the given index in team 2.
     * @param index Index to retrieve the Entity from.
     * @return Entity that sits on Index.
     * @throws InvalidCharacterPositionException If the given index is out of bounds of the current team size.
     * @throws DefeatedCharacterException If the Entity at the given index is defeated.
     */
    public Entity getCharacterTeam2(int index) throws InvalidCharacterPositionException, DefeatedCharacterException {
        index--;
        if(index >= team2.size() || index < 0){
            throw new InvalidCharacterPositionException("No character in such position");
        }

        Entity character = team2.get(index);

        if(character.isDefeated()){
            throw new DefeatedCharacterException("The character is defeated!");
        }

        return character;
    }

    /**
     * Plays an attack of Damage at the given Character.
     *
     * The outcome can be:
     * Dodge (-1): The character avoided the attack altogether.
     * Resisted (0): The defense of the character is greater than the damage.
     * Hit (N): Health points will be deducted based on damage minus defense.
     * @param character Character to receive damage.
     * @param damage Damage to deal.
     * @return -1 If attack avoided or 0 If damage blocked or Damage dealt at the character
     */
    public int playAttack(Entity character , int damage){

        //Character dodged the attack!
        if(random.nextInt(100) < character.getDexterity()){
            return -1;
        }

        int defense = character.getDefense();

        int totalDamage = damage - defense;

        if(totalDamage > 0){
            character.setHealth(character.getHealth() - totalDamage);
        }else{
            totalDamage = 0;
        }

        return totalDamage;
    }

    /**
     * Attempts to activate the special ability of the given character.
     *
     * @param character Character to activate its ability.
     * @throws OutOfEnergyException If the character has not enough energy.
     */
    public void useSpecialAbility(Entity character) throws OutOfEnergyException{
        if(character.getEnergy() < 5){
            throw new OutOfEnergyException(character.getClass().getSimpleName() + " Is out of energy!");
        }

        character.setEnergy((byte) 0);
        character.specialAbility();
    }

    /**
     * Flips the turn of the battle.
     */
    public void updateTurn(){
        for(Entity character : team1){
            if(!character.isDefeated()){
                character.rechargeEnergy();
            }
        }

        for(Entity character : team2){
            if(!character.isDefeated()){
                character.rechargeEnergy();
            }
        }
    }

    /**
     * Used for debug purposes
     *
     * Returns the characters at team 1 that it's going to attack in this turn.
     * Used for automatic sequential gameplay.
     * @return Entities whose turns follows
     */
    public Entity getNextTeam1(){
        if(teamOneTurn < team1.size()){
            return team1.get(teamOneTurn++);
        }
        teamOneTurn = 0;
        return team1.get(teamOneTurn);
    }

    /**
     * Used for debug purposes
     *
     * Returns the characters at team 2 that it's going to attack in this turn.
     * Used for automatic sequential gameplay.
     * @return Entities whose turns follows
     */
    public Entity getNextTeam2(){
        if(teamTwoTurn < team2.size()){
            return team2.get(teamTwoTurn++);
        }
        teamTwoTurn = 0;
        return team2.get(teamTwoTurn);
    }

}
