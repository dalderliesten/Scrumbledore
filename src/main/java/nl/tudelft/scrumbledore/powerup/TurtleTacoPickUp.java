package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * This class represents a pickup item of the BlueberryBubble power-up.
 * 
 * @author Floris Doolaard
 *
 */
public class TurtleTacoPickUp extends PowerupPickUp {

  /**
   * The consctrutor creates a pickup item in the level.
   * @param position , location of the object.
   * @param size , size of the object.
   */
  public TurtleTacoPickUp(Vector position, Vector size) {
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
