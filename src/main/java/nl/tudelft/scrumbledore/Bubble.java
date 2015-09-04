package nl.tudelft.scrumbledore;

/**
 * This class represents a Bubble object that the player is able to shoot.
 * @author Floris Doolaard
 *
 */
public class Bubble extends Projectile {

  /**
   * The Bubble constructor creates a new Bubble instance.
   * @param position
   *           Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public Bubble(Vector position, Vector size) {
    super(position, size);
  }
}
