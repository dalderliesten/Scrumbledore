package nl.tudelft.scrumbledore;

/**
 * Collision type, describing from which direction collider is colliding collidee if they are
 * colliding at all.
 * 
 * @author Tilro
 *
 */
public enum CollisionType {
  FromTop, FromBottom, FromLeft, FromRight, Overlapping, None
}
