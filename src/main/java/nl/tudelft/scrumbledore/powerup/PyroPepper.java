package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * PyroPepper is a power-up that gives the Player object 1 fireball projectile to shoot.
 * @author Floris Doolaard
 *
 */
public class PyroPepper extends LevelElement implements Powerup {

  /**
   * Creates a PyroPepper instance.
   * @param position , location of the PyroPepper instance.
   * @param size , the size of the object.
   */
  public PyroPepper(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    // TODO Auto-generated method stub
    return null;
  }

}
