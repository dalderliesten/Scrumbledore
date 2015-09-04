package nl.tudelft.scrumbledore;

/**
 * Class representing a Platform in a game.
 * 
 * @author Niels Warnars
 */
public class Platform extends LevelElement {

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
  }

}
