package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * TurtleTaco is a power-up that gives the player invulnerability for 5 seconds.
 * @author Floris Doolaard
 *
 */
public class TurtleTaco extends Powerup {

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
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    
    result.add(store.get("powerup-turtle-taco"));
    return result;
  }
  
}
