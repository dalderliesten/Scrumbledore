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
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  void decreaseLifetime(double delta);

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  double getLifetime();

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  void setLifetime(double newTime);
  
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
   * Checking wether the element is alive.
   * 
   * @return The boolean if the element is alive.
   */
  Boolean isAlive();

  /**
   * Setting the life of the element.
   * 
   * @param bool
   *          Can be True or False, stated on situation of element.
   */
  void setAlive(Boolean bool);

  /**
   * Gets the id of the current player.
   * 
   * @return Integer that represents the players number in the game.
   */
  int getPlayerNumber();

  /**
   * Sets the id of the current player.
   * 
   * @param id
   *          Integer that represents the players number in the game.
   */
  void setPlayerNumber(int id);

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

  /**
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  Boolean isFiring();

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  void setFiring(Boolean isFiring);
}
