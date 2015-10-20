package nl.tudelft.scrumbledore.powerup;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * 
 * @author Floris Doolaard
 *
 */
public abstract class Powerup extends LevelElement {
  
  /**
   * 
   * @param position
   * @param size
   */
  public Powerup(Vector position, Vector size) {
    super(position, size);
  }

}
