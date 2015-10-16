package nl.tudelft.scrumbledore.level;

/**
 * Class representing a Fruit in a game.
 * 
 * @author Niels Warnars, Floris Doolaard
 */
public class Fruit extends LevelElement {
  private int value;
  private Boolean pickable;

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
    pickable = false;
  }

  /**
   * Dummy HashCode method to satisfy code quality tools.
   */
  @Override
  public int hashCode() {
    return 0;
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
      return this.getPosition().equals(that.getPosition())
          && this.getSize().equals(that.getSize());
    }

    return false;
  }

  /**
   * Get the value of a fruit.
   * 
   * @return The value of a fruit
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
   * Return whether the fruit instance is pickable or not.
   * 
   * @return Boolean pickable.
   */
  public Boolean isPickable() {
    return pickable;
  }

  /**
   * Setting whether the fruit instance is pickable.
   * 
   * @param bool
   *          true or false.
   */
  public void setPickable(Boolean bool) {
    pickable = bool;
  }

}
