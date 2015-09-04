package nl.tudelft.scrumbledore;

/**
 * Class representing a Fruit in a game.
 * 
 * @author Niels Warnars
 */
public class Fruit extends LevelElement {

  /**
   * Create a new Fruit instance.
   * 
   * @param position
   *          Position of the fruit in the level.
   * @param size
   *          Size of the fruit.
   */
  public Fruit(Vector position, Vector size) {
    super(position, size);
  }

}
