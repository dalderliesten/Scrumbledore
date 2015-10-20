package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * BlueberryBubble creates a large bubble if the player uses this powerup.
 * The large bubble can encapsulate up to three NPC's.
 * @author Floris Doolaard
 *
 */
public class BlueberryBubble extends Powerup {

  /**
   * Creates a BlueberryBubble instance.
   * @param position , the location of the power-up.
   * @param size , the size of power-up.
   */
  public BlueberryBubble(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    // TODO Auto-generated method stub
    return null;
  }
}
