package nl.tudelft.scrumbledore.level;

/**
 * Class representing a Platform in a game.
 * 
 * @author Niels Warnars
 */
public class Platform extends LevelElement {
  private boolean isPassable;
  
  /**
   * Create a new Platform instance.
   * 
   * @param position
   *          Position of the platform in the level.
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
      return this.getPosition().equals(that.getPosition()) 
          && this.getSize().equals(that.getSize())
          && this.isPassable() == that.isPassable();
    }
    
    return false;
  }


  /**
   * Return whether a plaform is passable.
   * 
   * @return
   *        Whether a platform is passable.
   */
  public boolean isPassable() {
    return isPassable;
  }


  /**
   * Set a Platform to passable.
   * 
   * @param isPassable
   *        A platform is passable
   *            
   */
  public void setPassable(boolean isPassable) {
    this.isPassable = isPassable;
  }

}
