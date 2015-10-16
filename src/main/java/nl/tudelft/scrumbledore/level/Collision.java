package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;

/**
 * Class representing a collision between two level elements.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Collision {

  private LevelElement collider;
  private LevelElement collidee;
  private double delta;
  private KineticsLevelModifier kinetics;

  /**
   * Constructs a new Collision between a collider and a collidee.
   * 
   * @param collider
   *          The (moving) LevelElement colliding.
   * @param collidee
   *          The (static) LevelElement the collider is colliding with.
   * @param delta
   *          The number of steps passed since this method was last called.
   */
  public Collision(LevelElement collider, LevelElement collidee, double delta) {
    this.collider = collider;
    this.collidee = collidee;
    this.delta = delta;

    this.kinetics = new KineticsLevelModifier();
  }

  /**
   * Check whether collider and collidee are colliding at all.
   * 
   * @return A boolean.
   */
  public boolean colliding() {
    // Since colliding can be defined as having at least one vertical and one horizontal edge
    // of the collider inside the boundary box of the collidee.
    return (collidingTop() || collidingBottom()) && (collidingLeft() || collidingRight());
  }

  /**
   * A collider is colliding with a collidee from the top if it will collide either in the next step
   * while it is moving down, or its bottom is making contact with the collidee's top with a given
   * precision.
   * 
   * @return A boolean.
   */
  public boolean collidingFromTop() {
    if (!touchingLeft() && !touchingRight()) {
      if (collider.getBottom() < collidee.getTop() && collider.getSpeed().getY() > 0) {
        // To anticipate movement in the next step
        kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        kinetics.removeSpeed(collider, delta);

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
   * @return A boolean.
   */
  public boolean collidingFromBottom() {
    if (!touchingLeft() && !touchingRight()) {
      if (collider.getTop() > collidee.getBottom() && collider.getSpeed().getY() < 0) {
        // To anticipate movement in the next step
        kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        kinetics.removeSpeed(collider, delta);

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
   * @return A boolean.
   */
  public boolean collidingFromLeft() {
    if (!touchingTop() && !touchingBottom()) {
      if (collider.getRight() < collidee.getLeft() && collider.getSpeed().getX() > 0) {
        // To anticipate movement in the next step
        kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        kinetics.removeSpeed(collider, delta);

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
   * @return A boolean.
   */
  public boolean collidingFromRight() {
    if (!touchingTop() && !touchingBottom()) {
      // If the collider is about to collide, anticipate.
      if (collider.getLeft() > collidee.getRight() && collider.getSpeed().getX() < 0) {
        // Anticipate movement
        kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        kinetics.removeSpeed(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      return touchingRight();
    }
    return false;
  }

  /**
   * Get the collider.
   * 
   * @return The collider LevelElement.
   */
  public LevelElement getCollider() {
    return collider;
  }

  /**
   * Get the collidee.
   * 
   * @return The collidee LevelElement.
   */
  public LevelElement getCollidee() {
    return collidee;
  }

  /**
   * Check whether the bottom edge of the collider just touches the top edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingTop() {
    boolean c1 = collider.getBottom() >= collidee.getTop();
    boolean c2 = collider.getBottom() <= (collidee.getTop() + Constants.COLLISION_PRECISION);
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the top edge of the collider just touches the bottom edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingBottom() {
    boolean c1 = collider.getTop() >= collidee.getBottom() - Constants.COLLISION_PRECISION;
    boolean c2 = collider.getTop() <= collidee.getBottom();
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the right edge of the collider just touches the left edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingLeft() {
    boolean c1 = collider.getRight() >= collidee.getLeft();
    boolean c2 = collider.getRight() <= (collidee.getLeft() + Constants.COLLISION_PRECISION);
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the left edge of the collider just touches the right edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingRight() {
    boolean c1 = collider.getLeft() >= collidee.getRight() - Constants.COLLISION_PRECISION;
    boolean c2 = collider.getLeft() <= collidee.getRight();
    return colliding() && c1 && c2;
  }

  /**
   * Check whether the left edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingLeft() {
    return collider.getLeft() >= collidee.getLeft() 
        && collider.getLeft() <= collidee.getRight();
  }

  /**
   * Check whether the right edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingRight() {
    return collider.getRight() >= collidee.getLeft() 
        && collider.getRight() <= collidee.getRight();
  }

  /**
   * Check whether the top edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingTop() {
    return collider.getTop() >= collidee.getTop()
        && collider.getTop() <= collidee.getBottom();
  }

  /**
   * Check whether the bottom edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingBottom() {
    return collider.getBottom() >= collidee.getTop()
        && collider.getBottom() <= collidee.getBottom();
  }

}
