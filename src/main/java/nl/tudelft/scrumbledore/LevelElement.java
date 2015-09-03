package nl.tudelft.scrumbledore;

/**
 * Abstract class representing an element that can be placed in a Level.
 * 
 * @author Jesse Tilro
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
   */
  public LevelElement(Vector position, Vector size) {
    this.position = position;
    this.size = size;
    // Initial speed is 0.
    this.speed = new Vector(0, 0);
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

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  public int getTop() {
    return position.getY() - size.getY() / 2;
  }

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  public int getBottom() {
    return position.getY() + size.getY() / 2;
  }

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  public int getLeft() {
    return position.getX() - size.getX() / 2;
  }

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  public int getRight() {
    return position.getX() + size.getX() / 2;
  }

}
