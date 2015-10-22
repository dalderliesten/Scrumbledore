package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * This class represents a pickup item of the BlueberryBubble power-up.
 * 
 * @author Floris Doolaard
 *
 */
public class BlueberryBubblePickUp extends PowerupPickUp {

  /**
   * The consctrutor creates a pickup item in the level.
   * @param position , location of the object.
   * @param size , size of the object.
   */
  public BlueberryBubblePickUp(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();

    result.add(store.get("powerup-blueberry-bubble"));
    return result;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  public void addAction(LevelElementAction action) {
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
  }

  /**
   * Checking wether the element is alive.
   * 
   * @return The boolean if the element is alive.
   */
  public Boolean isAlive() {
    return false;
  }

  /**
   * Setting the life of the element.
   * 
   * @param bool
   *          Can be True or False, stated on situation of element.
   */
  public void setAlive(Boolean bool) {
  }

  /**
   * Gets the id of the current player.
   * 
   * @return Integer that represents the players number in the game.
   */
  public int getPlayerNumber() {
    return 0;
  }

  /**
   * Sets the id of the current player.
   * 
   * @param id
   *          Integer that represents the players number in the game.
   */
  public void setPlayerNumber(int id) {
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return false;
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
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
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  public Boolean isFiring() {
    return false;
  }

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  public void setFiring(Boolean isFiring) {
  }

  /**
   * Gives a list of current actions of the player.
   * @return a list of actions
   */
  public ArrayList<LevelElementAction> getActions() {
    return null;
  }
  
  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  public void decreaseLifetime(double delta) {
  }

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  public double getLifetime() {
    return 0;
  }

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  public void setLifetime(double newTime) {
  }


}
