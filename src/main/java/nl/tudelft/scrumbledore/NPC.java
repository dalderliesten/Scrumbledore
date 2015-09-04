package nl.tudelft.scrumbledore;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {

  /**
   * Create a new NPC instance.
   * 
   * @param position
   *          Position of the NPC in the level.
   * @param size
   *          Size of the NPC.
   */
  public NPC(Vector position, Vector size) {
    super(position, size);
  }

}
