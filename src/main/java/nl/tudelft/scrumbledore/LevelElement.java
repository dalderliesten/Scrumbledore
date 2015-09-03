package nl.tudelft.scrumbledore;

/**
 * Abstract class representing an element that can be placed in a Level.
 * 
 * @author Tilro
 *
 */
public abstract class LevelElement {

  private Vector position;
  private Vector size;
  private Vector speed;
  private boolean gravity;

  /**
   * Create a new LevelElement instance.
   * 
   * @param position
   *          Position of the element in the level.
   * @param size
   *          Size of the element.
   * @param speed
   *          Speed vector of the element, describing the direction and size of its movement.
   */
  public LevelElement(Vector position, Vector size, Vector speed) {
    this.position = position;
    this.size = size;
    this.speed = speed;
    // By default not affected by Gravity.
    this.gravity = false;
  }

  /**
   * Get the position vector of this element.
   * 
   * @return Position Vector.
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * Get the size vector of this element.
   * 
   * @return Size Vector.
   */
  public Vector getSize() {
    return size;
  }

  /**
   * Get the speed vector of this element.
   * 
   * @return Speed Vector.
   */
  public Vector getSpeed() {
    return speed;
  }

  /**
   * Check whether this LevelElement is affected by Gravity.
   * 
   * @return Boolean
   */
  public boolean hasGravity() {
    return gravity;
  }

  /**
   * Set the property determining whether this LevelElement is affected by gravity.
   * 
   * @param gravity
   *          A boolean
   */
  public void setGravity(boolean gravity) {
    this.gravity = gravity;
  }

}
