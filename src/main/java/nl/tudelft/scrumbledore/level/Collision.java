package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.element.DynamicElement;
import nl.tudelft.scrumbledore.level.element.LevelElement;
import nl.tudelft.scrumbledore.level.modifier.KineticsLevelModifier;

/**
 * Class representing a collision between two level elements.
 * 
 * @author Jesse Tilro
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Collision {
  private DynamicElement collider;
  private LevelElement collidee;
  private double delta;
  private KineticsLevelModifier kinetics;

  /**
   * Constructs a new Collision between a collider and a collidee.
   * 
   * @param collider
   *          The (moving) LevelElement colliding.
   *          
   * @param collidee
   *          The (static) LevelElement the collider is colliding with.
   *          
   * @param delta
   *          The number of steps passed since this method was last called.
   */
  public Collision(DynamicElement collider, LevelElement collidee, double delta) {
    this.collider = collider;
    this.collidee = collidee;
    this.delta = delta;

    this.kinetics = new KineticsLevelModifier();
  }

  /**
   * Check whether collider and collidee are colliding at all.
   * 
   * @return Whether a collision from two sides has occurred.
   */
  public boolean colliding() {
    return (collidingTop() || collidingBottom()) && (collidingLeft() || collidingRight());
  }

  /**
   * A collider is colliding with a collidee from the top if it will collide either in the next step
   * while it is moving down, or its bottom is making contact with the collidee's top with a given
   * precision.
   * 
   * @return Whether the collider is colliding with the collidee from the top.
   */
  public boolean collidingFromTop() {
    if (!touchingLeft() && !touchingRight()) {
      if (collider.getBottom() < collidee.getTop() && collider.getSpeed().getY() > 0) {
        kinetics.move(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        kinetics.revertMove(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      return touchingTop();
    }
    return false;
  }

  /**
   * A collider is colliding with a collidee from the bottom if it will collide either in the next
   * step while it is moving up, or its top is making contact with the collidee's bottom with a
   * given precision.
   * 
   * @return Whether the collider is colliding with the collidee from the bottom.
   */
  public boolean collidingFromBottom() {
    if (!touchingLeft() && !touchingRight()) {
      if (collider.getTop() > collidee.getBottom() && collider.getSpeed().getY() < 0) {
        kinetics.move(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        kinetics.revertMove(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      return touchingBottom();
    }
    return false;
  }

  /**
   * A collider is colliding with a collidee from the left if it will collide either in the next
   * step while it is moving right, or its right side is making contact with the collidee's left
   * side with a given precision.
   * 
   * @return Whether the collider is colliding with the collidee from the left.
   */
  public boolean collidingFromLeft() {
    if (!touchingTop() && !touchingBottom()) {
      if (collider.getRight() < collidee.getLeft() && collider.getSpeed().getX() > 0) {
        kinetics.move(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        kinetics.revertMove(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      return touchingLeft();
    }
    return false;
  }

  /**
   * A collider is colliding with a collidee from the right if it will collide either in the next
   * step while it is moving left, or its left side is making contact with the collidee's right side
   * with a given precision.
   * 
   * @return Whether the collider is colliding with the collidee from the right.
   */
  public boolean collidingFromRight() {
    if (!touchingTop() && !touchingBottom()) {
      if (collider.getLeft() > collidee.getRight() && collider.getSpeed().getX() < 0) {
        kinetics.move(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        kinetics.revertMove(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      return touchingRight();
    }
    return false;
  }

  /**
   * Check whether the bottom edge of the collider just touches the top edge of the collidee.
   * 
   * @return Boolean if touching the top edge.
   */
  private boolean touchingTop() {
    boolean c1 = collider.getBottom() >= collidee.getTop();
    boolean c2 = collider.getBottom() <= (collidee.getTop() + Constants.COLLISION_PRECISION);
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the top edge of the collider just touches the bottom edge of the collidee.
   * 
   * @return Boolean if touching the bottom edge.
   */
  private boolean touchingBottom() {
    boolean c1 = collider.getTop() >= collidee.getBottom() - Constants.COLLISION_PRECISION;
    boolean c2 = collider.getTop() <= collidee.getBottom();
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the right edge of the collider just touches the left edge of the collidee.
   * 
   * @return Boolean if touching the left edge.
   */
  private boolean touchingLeft() {
    boolean c1 = collider.getRight() >= collidee.getLeft();
    boolean c2 = collider.getRight() <= (collidee.getLeft() + Constants.COLLISION_PRECISION);
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the left edge of the collider just touches the right edge of the collidee.
   * 
   * @return Boolean if touching the right edge.
   */
  private boolean touchingRight() {
    boolean c1 = collider.getLeft() >= collidee.getRight() - Constants.COLLISION_PRECISION;
    boolean c2 = collider.getLeft() <= collidee.getRight();
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the left edge of the collider collides with the collidee.
   * 
   * @return Boolean if colliding with the left edge.
   */
  private boolean collidingLeft() {
    return collider.getLeft() >= collidee.getLeft() && collider.getLeft() <= collidee.getRight();
  }

  /**
   * Check whether the right edge of the collider collides with the collidee.
   * 
   * @return Boolean if colliding with the right edge.
   */
  private boolean collidingRight() {
    return collider.getRight() >= collidee.getLeft() && collider.getRight() <= collidee.getRight();
  }

  /**
   * Check whether the top edge of the collider collides with the collidee.
   * 
   * @return Boolean if colliding with the top edge.
   */
  private boolean collidingTop() {
    return collider.getTop() >= collidee.getTop() && collider.getTop() <= collidee.getBottom();
  }

  /**
   * Check whether the bottom edge of the collider collides with the collidee.
   * 
   * @return Boolean if colliding with the bottom edge.
   */
  private boolean collidingBottom() {
    return collider.getBottom() >= collidee.getTop()
        && collider.getBottom() <= collidee.getBottom();
  }

}
