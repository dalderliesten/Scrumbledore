package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * TurtleTaco is a power-up that gives the player invulnerability for 5 seconds.
 * @author Floris Doolaard
 *
 */
public class TurtleTaco extends Powerup{

  /**
   * 
   * @param position
   * @param size
   */
  public TurtleTaco(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
