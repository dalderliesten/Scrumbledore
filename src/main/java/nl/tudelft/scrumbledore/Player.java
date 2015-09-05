package nl.tudelft.scrumbledore;

/**
 * Class representing a Player in a game.
 * 
 * @author Niels Warnars
 */
public class Player extends LevelElement {

  /**
   * Create a new Player instance.
   * 
   * @param position
   *          Position of the player in the level.
   * @param size
   *          Size of the Player.
   */
  public Player(Vector position, Vector size) {
    super(position, size);
    
    setGravity(true);
  }

  
  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Player) {
      Player that = (Player) other;
      return (this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }
}
