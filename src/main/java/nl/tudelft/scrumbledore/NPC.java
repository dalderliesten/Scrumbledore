package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {
  private int life;
  private int status;
  private boolean hasFruit;
  private NPCAction movementDirection;
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
    movementDirection = NPCAction.MoveLeft;
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
      return (this.getPosition().equals(that.getPosition()) && this.getSize()
          .equals(that.getSize()));
    }

    return false;
  }

  /**
   * Get the life of an NPC.
   * 
   * @return The life of an NPC
   */
  public int getLife() {
    return life;
  }

  /**
   * Set the life of an NPC.
   * 
   * @param life
   *          The life of an NPC
   */
  public void setLife(int life) {
    this.life = life;
  }

  /**
   * Get the status of an NPC.
   * 
   * @return The status of an NPC
   */
  public int getStatus() {
    return status;
  }

  /**
   * Set the status of an NPC.
   * 
   * @param status
   *          The status of an NPC
   */
  public void setStatus(int status) {
    this.status = status;
  }

  /**
   * Check whether an NPC has a fruit.
   * 
   * @return Whether an NPC has a fruit
   */
  public boolean hasFruit() {
    return hasFruit;
  }

  /**
   * Set whether an NPC has a fruit.
   * 
   * @param hasFruit
   *          Whether an NPC has a fruit.
   */
  public void setHasFruit(boolean hasFruit) {
    this.hasFruit = hasFruit;
  }

  /**
   * Sets the moving direction of an NPC.
   * 
   * @param direction
   *          The moving direction of an NPC
   */
  public void setMovementDirection(NPCAction direction) {
    this.movementDirection = direction;
  }

  /**
   * Returns the moving direction of an NPC.
   * 
   * @return The moving direction of an NPC
   */
  public NPCAction getMovementDirection() {
    return this.movementDirection;
  }

}
