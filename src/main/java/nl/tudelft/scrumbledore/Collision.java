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
  private CollisionType type;

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
    findType();
  }

  /**
   * Determines the CollisionType of this Collision and sets the type attribute correspondingly.
   */
  private void findType() {

    type = CollisionType.None;
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
   * Get the type of this collision.
   * 
   * @return A CollisionType.
   */
  public CollisionType getType() {
    return type;
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
