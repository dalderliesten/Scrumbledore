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


  public int getLife() {
    return life;
  }


  public void setLife(int life) {
    this.life = life;
  }


  public int getStatus() {
    return status;
  }


  public void setStatus(int status) {
    this.status = status;
  }


  public boolean isHasFruit() {
    return hasFruit;
  }


  public void setHasFruit(boolean hasFruit) {
    this.hasFruit = hasFruit;
  }
  
}
