package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing a Player in a game.
 * 
 * @author Niels Warnars
 * @author Jesse Tilro
 */
public class Player extends LevelElement {

  private ArrayList<PlayerAction> actions;

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
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
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
}
