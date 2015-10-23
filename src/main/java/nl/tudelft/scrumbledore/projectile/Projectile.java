package nl.tudelft.scrumbledore.projectile;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.LevelElementAction;

/**
 * Represents a projectile object.
 * 
 * @author Floris Doolaard
 *
 */
public interface Projectile extends LevelElement {

  /**
   * Return a boolean wether to see if a bubble has an NPC in it.
   * 
   * @return Boolean of hasNPC.
   */
  Boolean hasNPC();

  /**
   * Setting a boolean to hasNPC.
   * 
   * @param bool
   *          The boolean that hasNPC has to be.
   */
  void setHasNPC(Boolean bool);

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
   * Add an action the Bubble has to perform.
   * 
   * @param action
   *          The action (MoveLeft or MoveRight) the Bubble has to perform.
   */
  void addAction(LevelElementAction action);

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A BubbleAction.
   * @return Boolean.
   */
  boolean hasAction(LevelElementAction action);
  
  /**
   * Clear all queued actions for this Bubble.
   */
  void clearActions();
}
