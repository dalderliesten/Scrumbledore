package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing a Player in a game.
 * 
 * @author Niels Warnars
 * @author Jesse Tilro
 * @author David Alderliesten
 */
public class Player extends LevelElement {
  private ArrayList<PlayerAction> actions;
  private PlayerAction lastMove;
  private Boolean firing;
  private Boolean alive;

  /**
   * Create a new Player instance.
   * 
   * @param position
   *          Position of the player in the level.
   * @param size
   *          Size of the Player.
   */
  public Player(Vector position, Vector size) {
    super(position, size);

    setGravity(true);

    actions = new ArrayList<PlayerAction>();
    lastMove = PlayerAction.MoveRight;
    firing = false;
    alive = true;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  public void addAction(PlayerAction action) {
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
   * Checking wether the player is alive.
   * 
   * @return The boolean if the player is alive.
   */
  public Boolean isAlive() {
    return alive;
  }

  /**
   * Setting the life of the player.
   * 
   * @param bool
   *          Can be True or False, stated on situation of player.
   */
  public void setAlive(Boolean bool) {
    alive = bool;
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A PlayerAction.
   * @return Boolean.
   */
  public boolean hasAction(PlayerAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A PlayerAction.
   */
  public void removeAction(PlayerAction action) {
    actions.remove(action);
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public PlayerAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(PlayerAction action) {
    // Mapping the last action to the arraylist with actions.
    if (action == PlayerAction.MoveLeft || action == PlayerAction.MoveRight) {
      lastMove = action;
    }
  }

  /**
   * Dummy HashCode method to satisfy code quality tools.
   */
  @Override
  public int hashCode() {
    return 0;
  }

  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Player) {
      Player that = (Player) other;
      return (this.getPosition().equals(that.getPosition())
          && this.getSize().equals(that.getSize()));
    }

    return false;
  }

  /**
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  public Boolean isFiring() {
    return firing;
  }

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  public void setFiring(Boolean isFiring) {
    this.firing = isFiring;
  }

}
