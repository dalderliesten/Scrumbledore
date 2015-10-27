package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class representing a Platform in a game.
 * 
 * @author Niels Warnars
 */
public class Platform extends BasicStaticElement {
  private boolean isPassable;

  /**
   * Create a new Platform instance.
   * 
   * @param position
   *          Position of the platform in the level.
   * 
   * @param size
   *          Size of the platform.
   */
  public Platform(Vector position, Vector size) {
    super(position, size);

    isPassable = false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Platform) {
      Platform that = (Platform) other;
      return this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize())
          && this.isPassable() == that.isPassable();
    }

    return false;
  }

  /**
   * Return whether a plaform is passable.
   * 
   * @return Whether a platform is passable.
   */
  public boolean isPassable() {
    return isPassable;
  }

  /**
   * Set a Platform to passable.
   * 
   * @param isPassable
   *          A platform is passable.
   */
  public void setPassable(boolean isPassable) {
    this.isPassable = isPassable;
  }

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   * 
   * @return Sprites to be drawn.
   */
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    String id = "wall-1";
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    result.add(store.get(id));
    return result;
  }

}
