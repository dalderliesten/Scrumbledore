package nl.tudelft.scrumbledore.powerup;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * This class represents a pickup item of a power-up object.
 * 
 * @author Floris Doolaard
 *
 */
public abstract class PowerupPickUp extends LevelElement {

  /**
   * The basic constructor of a PowerupPickable object.
   * 
   * @param position
   *          , location of the object.
   * @param size
   *          , size of the object.
   */
  public PowerupPickUp(Vector position, Vector size) {
    super(position, size);
  }
}
