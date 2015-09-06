package nl.tudelft.scrumbledore;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {
  private int life;
  private int status;
  private boolean hasFruit;
  
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
    
    setGravity(true);
  }

  
  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof NPC) {
      NPC that = (NPC) other;
      return (this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }


  /**
   * Get the life of an NPC.
   * 
   * @return
   *        The life of an NPC
   */
  public int getLife() {
    return life;
  }


  /**
   * Set the life of an NPC.
   * 
   * @param life
   *        The life of an NPC
   */
  public void setLife(int life) {
    this.life = life;
  }


  /**
   * Get the status of an NPC.
   * 
   * @return
   *        The status of an NPC
   */
  public int getStatus() {
    return status;
  }


  /**
   * Set the status of an NPC.
   * 
   * @param status
   *        The status of an NPC
   */
  public void setStatus(int status) {
    this.status = status;
  }


  /**
   * Check whether an NPC has a fruit.
   * 
   * @return
   *        Whether an NPC has a fruit
   */
  public boolean hasFruit() {
    return hasFruit;
  }


  /**
   * Set whether an NPC has a fruit.
   * 
   * @param hasFruit
   *         Whether an NPC has a fruit.
   */
  public void setHasFruit(boolean hasFruit) {
    this.hasFruit = hasFruit;
  }
  
}
