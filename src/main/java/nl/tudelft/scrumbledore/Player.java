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
  }

}
