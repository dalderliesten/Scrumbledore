package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * TurtleTaco is a power-up that gives the player invulnerability for 5 seconds.
 * @author Floris Doolaard
 *
 */
public class TurtleTaco extends LevelElement implements Powerup {

  /**
   * Constructs a TurtleTaco object.
   * @param position , location of the TurtleTaco.
   * @param size , size of the object.
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
