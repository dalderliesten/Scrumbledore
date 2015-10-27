package nl.tudelft.scrumbledore.level.element;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Abstract class representing an element that can be placed in a Level.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public interface LevelElement {

  /**
   * Get the position vector of this element.
   * 
   * @return Position Vector.
   */
  Vector getPosition();

  /**
   * Get the X coordinate of the element.
   * 
   * @return double
   */
  double posX();

  /**
   * Get the Y coordinate of the element.
   * 
   * @return double
   */
  double posY();

  /**
   * Get the size vector of this element.
   * 
   * @return Size Vector.
   */
  Vector getSize();

  /**
   * Get the width of the element.
   * 
   * @return double
   */
  double width();

  /**
   * Get the height of the element.
   * 
   * @return double
   */
  double height();

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  double getTop();

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  double getBottom();

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  double getLeft();

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  double getRight();

  /**
   * Get the distance to another LevelElement.
   * 
   * @param other
   *          The other element to measure the distance to.
   * @return The distance.
   */
  double distance(LevelElement other);

  /**
   * Check whether another element is within range of this element using a circular radius by
   * computing the distance.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (of the circle).
   * @return A boolean.
   */
  boolean inRadiusRangeOf(LevelElement other, double range);

  /**
   * Check whether another element is within range of this element using a box. The box is a square
   * axis-aligned bounding box, with dimensions of twice the given range. It has the position of
   * this element as its center.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (a half of the dimensions of the square box).
   * @return A boolean.
   */
  boolean inBoxRangeOf(LevelElement other, double range);

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   * @return Sprites to be drawn.
   */
  ArrayList<Sprite> getSprites(double steps);

}
