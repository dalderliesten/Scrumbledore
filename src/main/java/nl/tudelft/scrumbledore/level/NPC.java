package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {
  private NPCAction lastMove;
  private ArrayList<NPCAction> actions;

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

    actions = new ArrayList<NPCAction>();
    addAction(NPCAction.MoveLeft);
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  public void addAction(NPCAction action) {
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
  public boolean hasAction(NPCAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          An NPCAction.
   */
  public void removeAction(NPCAction action) {
    actions.remove(action);
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
   * @return A Boolean.
   */
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
  public NPCAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(NPCAction action) {
    if (action == NPCAction.MoveLeft || action == NPCAction.MoveRight) {
      lastMove = action;
    }
  }

}
