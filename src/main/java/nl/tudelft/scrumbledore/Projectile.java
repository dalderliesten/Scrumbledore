package nl.tudelft.scrumbledore;

/**
 * This abstract class represent subclasses like Bubble.
 * @author Floris Doolaard
 *
 */
public abstract class Projectile extends LevelElement {

  /**
   * The Projectile constructor creates a new projectile instance.
   * @param position
   *           Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public Projectile(Vector position, Vector size) {
    super(position, size);
  }
}
