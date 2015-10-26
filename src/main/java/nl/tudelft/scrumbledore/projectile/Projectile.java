package nl.tudelft.scrumbledore.projectile;

import nl.tudelft.scrumbledore.level.DynamicElement;
/**
 * Represents a projectile object.
 * 
 * @author Floris Doolaard
 *
 */
public interface Projectile extends DynamicElement {

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
  
}
