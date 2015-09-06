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
  private CollisionType type;

  /**
   * Constructs a new Collision between a collider and a collidee.
   * 
   * @param collider
   *          The (moving) LevelElement colliding.
   * @param collidee
   *          The (static) LevelElement the collider is colliding with.
   */
  public Collision(LevelElement collider, LevelElement collidee) {
    this.collider = collider;
    this.collidee = collidee;
    findType();
  }

  /**
   * Determines the CollisionType of this Collision and sets the type attribute correspondingly.
   */
  private void findType() {
    if (!colliding()) {
      type = CollisionType.None;
    } else {
      // Check for colliding direction.
    }
  }

  /**
   * Check whether collider and collidee are colliding at all.
   * 
   * @return A boolean.
   */
  public boolean colliding() {
    // Top edge of collider in between top and bottom edge of collidee.
    boolean top = (collider.getTop() >= collidee.getTop() && collider.getTop() <= collidee
        .getBottom());
    // Bottom edge of collider in between top and bottom edge of collidee.
    boolean bottom = (collider.getBottom() >= collidee.getTop() && collider.getBottom() <= collidee
        .getBottom());
    // Left edge of collider in between left and right edge of collidee.
    boolean left = (collider.getLeft() >= collidee.getLeft() && collider.getLeft() <= collidee
        .getRight());
    // Right edge of collider in between left and right edge of collidee.
    boolean right = (collider.getRight() >= collidee.getLeft() && collider.getRight() <= collidee
        .getRight());

    // When at least one vertical and one horizontal edge of the collider are inside the collidee,
    // then collider and collidee are colliding.
    return ((top || bottom) && (left || right));
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

}
