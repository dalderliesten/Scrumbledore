package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * ChiliChicken is a power-up that gives a Player object more speed for 5 seconds.
 * @author Floris Doolaard
 *
 */
public class ChiliChicken extends Powerup {

  private Player player;
  
  /**
   * Creates a ChiliChicken instance.
   * @param position , location of the ChiliChicken.
   * @param size , size of the object.
   */
  public ChiliChicken(Vector position, Vector size) {
    super(position, size);
  }
  
  /**
   * As soon as a power-up instance gets picked up by a player,
   * it will get wrapped (and altered elsewhere).
   * @param player , a Player object
   */
  public void setWrappedPlayer(Player player) {
    this.player = player;
  }
  
  @Override
  public Vector getSpeed() {
    if (player != null) {
      Vector alteredSpeed = player.getSpeed();
      alteredSpeed.scale(1.5);
      return alteredSpeed;
    } 
    return null;
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    
    result.add(store.get("powerup-chili-chicken"));
    return result;
  }

}
