package nl.tudelft.scrumbledore.powerup;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * This is an abstract decorator class which alters certain attributes 
 * of a Player object as a bonus.
 * @author Floris Doolaard
 *
 */
public abstract class Powerup extends LevelElement {
  
  /**
   * The basic constructor of a powerup.
   * @param position , location in the level.
   * @param size , size of the powerup.
   */
  public Powerup(Vector position, Vector size) {
    super(position, size);
  }

}
