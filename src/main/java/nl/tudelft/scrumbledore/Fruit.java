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
  @SuppressWarnings("FindBugs.HE_EQUALS_USE_HASHCODE")
  public boolean equals(Object other) {
    if (other instanceof Fruit) {
      Fruit that = (Fruit) other;
      return (this.getPosition().equals(that.getPosition()) 
          && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }


  /**
   * Get the value of a fruit.
   * 
   * @return
   *        The value of a fruit
   */
  public int getValue() {
    return value;
  }


  /**
   * Set the value of a fruit.
   * 
   * @param value
   *          The value of a fruit
   */
  public void setValue(int value) {
    this.value = value;
  }


  /**
   * Get the appearance time of a fruit.
   * 
   * @return
   *        The appearance time of a fruit
   */
  public int getTime() {
    return time;
  }


  /**
   * Set the appearance time of a fruit.
   * 
   * @param time
   *        the appearance time of a fruit
   */
  public void setTime(int time) {
    this.time = time;
  }
  
  
}
