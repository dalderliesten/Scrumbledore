package nl.tudelft.scrumbledore;

/**
 * The player class extends LevelElement and is the object 
 * that will be the main character in the game.
 * @author Floris
 *
 */
public class Player extends LevelElement {
  
  private Vector speed;
  private boolean gravity;

  /**
   * Constructor of the player object.
   * 
   * @param position
   *          Position (as a Vector) of the player in the level.
   * @param size
   *          The size (as a Vector) of the player in the level.
   */
  public Player(Vector position, Vector size) {
    super(position, size);
    // Initial speed is 0.
    this.speed = new Vector(0, 0);
    // By default not affected by Gravity.
    this.gravity = false;
  }
  
}
