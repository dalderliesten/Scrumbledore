package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class representing an NPC in a game.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
 */
public class NPC extends BasicDynamicElement {
  private LevelElementAction lastMove;
  private ArrayList<LevelElementAction> actions;

  /**
   * Create a new NPC instance.
   * 
   * @param position
   *          Position of the NPC in the level.
   * @param size
   *          Size of the NPC.
   */
  public NPC(Vector position, Vector size) {
    super(position, size);

    setGravity(true);

    actions = new ArrayList<LevelElementAction>();
    addAction(LevelElementAction.MoveLeft);
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  public void addAction(LevelElementAction action) {
    if (!hasAction(action)) {
      actions.add(action);
      setLastMove(action);
    }
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
    actions.clear();
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          An NPCAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          An NPCAction.
   */
  public void removeAction(LevelElementAction action) {
    actions.remove(action);
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NPC) {
      NPC that = (NPC) other;
      return this.getPosition().equals(that.getPosition()) 
          && this.getSize().equals(that.getSize());
    }

    return false;
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
    if (action == LevelElementAction.MoveLeft || action == LevelElementAction.MoveRight) {
      lastMove = action;
    }
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
    String id = "zenchan-move-right";
    if (getLastMove().equals(LevelElementAction.MoveLeft)) {
      id = "zenchan-move-left";
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
