package game;

import entities.*;
import java.util.List;

/**
 * Manages the entities that are available during the game.
 * Each new Entity that is created inside entities package should be registered here in order to function
 * inside the simulator.
 */
public class EntityManager {

    List<Entity> entities;

    /**
     * Initializes the entities that are going to be available in the game.
     */
    public EntityManager() {
        entities = List.of(
                new Knight(),
                new Mage(),
                new Archer(),
                new Assassin()
        );
    }

    /**
     * Return the list of Entities available in the game.
     * @return List of Entities.
     */
    public List<Entity> getRegisteredEntities(){
        return entities;
    }

    /**
     * Returns a new instance of the entity based on registry order (Index in entities list)
     *
     * @param entity Entity to request an instance of.
     * @return Instance of an Entity (Playable character) based on argument.
     */
    public Entity getNewEntity(int entity){
        switch (entity){
            case 0:
                return new Knight();
            case 1:
                return new Mage();
            case 2:
                return new Archer();
            case 3:
                return new Assassin();
            default:
                return null;
        }
    }
}
