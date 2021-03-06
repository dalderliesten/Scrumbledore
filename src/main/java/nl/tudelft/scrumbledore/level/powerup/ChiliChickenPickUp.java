package nl.tudelft.scrumbledore.level.powerup;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * This class represents a pickup item of the ChiliChicken power-up.
 * 
 * @author Floris Doolaard
 */
public class ChiliChickenPickUp extends PowerupPickUp {

  /**
   * The constructor creates a pickup item in the level.
   * 
   * @param position
   *          location of the object.
   * 
   * @param size
   *          size of the object.
   */
  public ChiliChickenPickUp(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();

    result.add(store.get("powerup-chili-chicken"));
    return result;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction to be performed.
   */
  public void addAction(LevelElementAction action) {
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction to be checked.
   *          
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return false;
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction to be removed.
   */
  public void removeAction(LevelElementAction action) {
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return null;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
  }

  /**
   * Gives a list of current actions of the player.
   * 
   * @return a list of actions.
   */
  public ArrayList<LevelElementAction> getActions() {
    return null;
  }

}
