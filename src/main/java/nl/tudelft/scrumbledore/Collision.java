package nl.tudelft.scrumbledore;

/**
 * Class representing a collision between two level elements.
 * 
 * @author Jesse Tilro
 *
 */
public class Collision {

  private LevelElement collider;
  private LevelElement collidee;
  private double delta;

  /**
   * Constructs a new Collision between a collider and a collidee.
   * 
   * @param collider
   *          The (moving) LevelElement colliding.
   * @param collidee
   *          The (static) LevelElement the collider is colliding with.
   */
  public Collision(LevelElement collider, LevelElement collidee, double delta) {
    this.collider = collider;
    this.collidee = collidee;
    this.delta = delta;
  }

  /**
   * Check whether collider and collidee are colliding at all.
   * 
   * @return A boolean.
   */
  public boolean colliding() {
    // When at least one vertical and one horizontal edge of the collider are inside the collidee,
    // then collider and collidee are colliding.
    return ((collidingTop() || collidingBottom()) && (collidingLeft() || collidingRight()));
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
      // If the collider is about to collide, anticipate.
      if (collider.getBottom() < collidee.getTop() && collider.getSpeed().getY() > 0) {
        // Anticipate movement
        Kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        Kinetics.removeSpeed(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      // Otherwise, check whether the collider is touching the collidee.
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
      // If the collider is about to collide, anticipate.
      if (collider.getTop() > collidee.getBottom() && collider.getSpeed().getY() < 0) {
        // Anticipate movement
        Kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingLeft() && !touchingRight());
        Kinetics.removeSpeed(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      // Otherwise, check whether the collider is touching the collidee.
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
      // If the collider is about to collide, anticipate.
      if (collider.getRight() < collidee.getLeft() && collider.getSpeed().getX() > 0) {
        // Anticipate movement
        Kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        Kinetics.removeSpeed(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      // Otherwise, check whether the collider is touching the collidee.
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
        Kinetics.addSpeed(collider, delta);
        boolean collisionExpected = (colliding() && !touchingTop() && !touchingBottom());
        Kinetics.removeSpeed(collider, delta);

        if (collisionExpected) {
          return true;
        }
      }
      // Otherwise, check whether the collider is touching the collidee.
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
    return (colliding() && c1 && c2);
  }

  /**
   * Check whether the top edge of the collider just touches the bottom edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingBottom() {
    boolean c1 = collider.getTop() >= collidee.getBottom();
    boolean c2 = collider.getTop() <= (collidee.getBottom() - Constants.COLLISION_PRECISION);
    return (colliding() && c1 && c2);
  }

  /**
   * Check whether the right edge of the collider just touches the left edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingLeft() {
    boolean c1 = collider.getRight() >= collidee.getLeft();
    boolean c2 = collider.getRight() <= (collidee.getLeft() + Constants.COLLISION_PRECISION);
    return (colliding() && c1 && c2);
  }

  /**
   * Check whether the left edge of the collider just touches the right edge of the collidee.
   * 
   * @return Boolean.
   */
  private boolean touchingRight() {
    boolean c1 = collider.getLeft() >= collidee.getRight();
    boolean c2 = collider.getLeft() <= (collidee.getRight() - Constants.COLLISION_PRECISION);
    return (colliding() && c1 && c2);
  }

  /**
   * Check whether the left edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingLeft() {
    boolean b = (collider.getLeft() >= collidee.getLeft() && collider.getLeft() <= collidee
        .getRight());
    return b;
  }

  /**
   * Check whether the right edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingRight() {
    boolean b = (collider.getRight() >= collidee.getLeft() && collider.getRight() <= collidee
        .getRight());
    return b;
  }

  /**
   * Check whether the top edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingTop() {
    boolean b = (collider.getTop() >= collidee.getTop() && collider.getTop() <= collidee
        .getBottom());
    return b;
  }

  /**
   * Check whether the bottom edge of the collider collides with the collidee.
   * 
   * @return Boolean.
   */
  private boolean collidingBottom() {
    boolean b = (collider.getBottom() >= collidee.getTop() && collider.getBottom() <= collidee
        .getBottom());
    return b;
  }

}