package nl.tudelft.scrumbledore.level.element;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * An abstract class as a basic class for represententing static level elements.
 * 
 * @author Floris Doolaard
 */
public abstract class BasicStaticElement implements StaticElement {
  private Vector position;
  private Vector size;

  /**
   * Create a new LevelElement instance.
   * 
   * @param position
   *          Position of the element in the level.
   *          
   * @param size
   *          Size of the element.
   */
  public BasicStaticElement(Vector position, Vector size) {
    this.position = position;
    this.size = size;
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
   * Get the X coordinate of the element.
   * 
   * @return X coordinate.
   */
  public double posX() {
    return position.getX();
  }

  /**
   * Get the Y coordinate of the element.
   * 
   * @return Y coordinate.
   */
  public double posY() {
    return position.getY();
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
   * Get the width of the element.
   * 
   * @return Width of the element.
   */
  public double width() {
    return size.getX();
  }

  /**
   * Get the height of the element.
   * 
   * @return Height of the element.
   */
  public double height() {
    return size.getY();
  }

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  public double getTop() {
    return position.getY() - size.getY() / 2;
  }

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  public double getBottom() {
    return position.getY() + size.getY() / 2;
  }

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  public double getLeft() {
    return position.getX() - size.getX() / 2;
  }

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  public double getRight() {
    return position.getX() + size.getX() / 2;
  }

  /**
   * Get the distance to another LevelElement.
   * 
   * @param other
   *          The other element to measure the distance to.
   *          
   * @return The distance.
   */
  public double distance(LevelElement other) {
    return getPosition().distance(other.getPosition());
  }

  /**
   * Check whether another element is within range of this element using a circular radius by
   * computing the distance.
   * 
   * @param other
   *          The other element.
   *          
   * @param range
   *          The range (of the circle).
   *          
   * @return Whether another element is within range of this element.
   */
  public boolean inRadiusRangeOf(LevelElement other, double range) {
    return distance(other) <= range;
  }

  /**
   * Check whether another element is within range of this element using a box. The box is a square
   * axis-aligned bounding box, with dimensions of twice the given range. It has the position of
   * this element as its center.
   * 
   * @param other
   *          The other element.
   *          
   * @param range
   *          The range (a half of the dimensions of the square box).
   *          
   * @return Whether another element is within range of this element.
   */
  public boolean inBoxRangeOf(LevelElement other, double range) {
    boolean inX = (other.posX() >= posX() - range && other.posX() <= posX() + range);
    boolean inY = (other.posY() >= posY() - range && other.posY() <= posY() + range);
    return inX && inY;
  }

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   *          
   * @return Sprites to be drawn.
   */
  public abstract ArrayList<Sprite> getSprites(double steps);
  
}
