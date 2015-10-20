package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * ChiliChicken is a power-up that gives a Player object more speed for 5 seconds.
 * @author Floris Doolaard
 *
 */
public class ChiliChicken extends Powerup {
  
  /**
   * Creates a ChiliChicken instance.
   * @param position , location of the ChiliChicken.
   * @param size , size of the object.
   */
  public ChiliChicken(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    
    result.add(store.get("powerup-chili-chicken"));
    return result;
  }

}
