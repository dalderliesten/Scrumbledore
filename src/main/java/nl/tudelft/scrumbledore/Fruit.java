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

  
  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Fruit) {
      Fruit that = (Fruit) other;
      return (this.getPosition().equals(that.getPosition()) && this.getSize() == that.getSize());
    }
    
    return false;
  }
}
