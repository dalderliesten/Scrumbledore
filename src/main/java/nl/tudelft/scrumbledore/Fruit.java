package nl.tudelft.scrumbledore;

/**
 * Class representing a Fruit in a game.
 * 
 * @author Niels Warnars
 */
public class Fruit extends LevelElement {
  private int value;
  private int time;
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
      return (this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }


  public int getValue() {
    return value;
  }


  public void setValue(int value) {
    this.value = value;
  }


  public int getTime() {
    return time;
  }


  public void setTime(int time) {
    this.time = time;
  }
  
  
}
