package nl.tudelft.scrumbledore.projectile;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.BasicDynamicElement;
import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * This class creates a Bubble object that the player can shoot.
 * 
 * @author Floris Doolaard
 *
 */
public class Bubble extends BasicDynamicElement implements Projectile{
  private ArrayList<LevelElementAction> actions;
  private Boolean hasNPC;

  private double lifetime;

  /**
   * The Bubble constructor creates a new Bubble instance.
   * 
   * @param position
   *          Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public Bubble(Vector position, Vector size) {
    super(position, size);
    getFriction().setX(Constants.BUBBLE_FRICTION);
    lifetime = Constants.BUBBLE_LIFETIME;
    actions = new ArrayList<LevelElementAction>();
    hasNPC = false;
  }

  /**
   * Add an action the Bubble has to perform.
   * 
   * @param action
   *          The action (MoveLeft or MoveRight) the Bubble has to perform.
   */
  public void addAction(LevelElementAction action) {
    actions.clear();
    actions.add(action);
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A BubbleAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return actions.contains(action);
  }

  /**
   * Clear all queued actions for this Bubble.
   */
  public void clearActions() {
    actions.clear();
  }

  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  public void decreaseLifetime(double delta) {
    lifetime -= delta;
  }

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  public double getLifetime() {
    return lifetime;
  }

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  public void setLifetime(double newTime) {
    lifetime = newTime;
  }

  /**
   * Return a boolean wether to see if a bubble has an NPC in it.
   * 
   * @return Boolean of hasNPC.
   */
  public Boolean hasNPC() {
    return hasNPC;
  }

  /**
   * Setting a boolean to hasNPC.
   * 
   * @param bool
   *          The boolean that hasNPC has to be.
   */
  public void setHasNPC(Boolean bool) {
    hasNPC = bool;
  }

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   * @return Sprites to be drawn.
   */
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    String id = "bubble-green";
    if (hasNPC()) {
      id = "bubble-zenchan-green";
      if (lifetime < 60 && lifetime % 15 < 8) {
        id = "bubble-zenchan-red";
      }
    } else if (lifetime > 5 && lifetime < 40 && lifetime % 15 < 8) {
      id = "bubble-red";
    } else if (lifetime <= 5) {
      id = "bubble-green-burst";
    }
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    result.add(store.getAnimated(id).getFrame(steps));
    return result;
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
    return actions;
  }

}
