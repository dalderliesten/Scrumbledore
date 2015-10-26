package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

/**
 * The interface of powerup objects and dynamic elements.
 * This way dynamic elements an powerups are replacable.
 * @author Floris Doolaard
 *
 */
public interface DynamicElement extends LevelElement {
  
  /**
   * Stop this LevelElement's vertical movement.
   */
  void stopVertically();

  /**
   * Stop this LevelElement's horizontal movement.
   */
  void stopHorizontally();
  
  /**
   * Gives a list of current actions of the player.
   * @return a list of actions
   */
  ArrayList<LevelElementAction> getActions();

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  void addAction(LevelElementAction action);

  /**
   * Remove all actions from the queue.
   */
  void clearActions();

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  boolean hasAction(LevelElementAction action);

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
   */
  void removeAction(LevelElementAction action);

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  LevelElementAction getLastMove();

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  void setLastMove(LevelElementAction action);

  @Override
  int hashCode();

  @Override
  boolean equals(Object other);

}
