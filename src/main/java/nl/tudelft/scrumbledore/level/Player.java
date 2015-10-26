package nl.tudelft.scrumbledore.level;

/**
 * Class representing a Player in a game.
 * 
 * @author Niels Warnars
 * @author Jesse Tilro
 * @author Floris Doolaard
 */
public class Player extends BasicPlayer {

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
  }
  
}
