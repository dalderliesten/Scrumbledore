package nl.tudelft.scrumbledore.level.powerup;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.BasicDynamicElement;

/**
 * This class represents a pickup item of a power-up object.
 * 
 * @author Floris Doolaard
 */
public abstract class PowerupPickUp extends BasicDynamicElement {

  /**
   * The basic constructor of a PowerupPickable object.
   * 
   * @param position
   *          location of the object.
   *          
   * @param size
   *          size of the object.
   */
  public PowerupPickUp(Vector position, Vector size) {
    super(position, size);
  }

}
