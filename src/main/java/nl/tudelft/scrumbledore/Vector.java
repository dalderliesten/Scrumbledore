package nl.tudelft.scrumbledore;

/**
 * Class representing a 2D vector, to be used in the game to represent for example a vector,
 * position or size.
 * 
 * @author Tilro
 *
 */
public class Vector {

  private int entryX;
  private int entryY;

  /**
   * Create a new 2D Vector with given X and Y entries.
   * 
   * @param entryX
   *          The X entry of the vector.
   * @param entryY
   *          The Y entry of the vector.
   */
  public Vector(int entryX, int entryY) {
    this.entryX = entryX;
    this.entryY = entryY;
  }

  /**
   * Get the X entry of the vector.
   * 
   * @return X entry
   */
  public int getX() {
    return entryX;
  }

  /**
   * Get the Y entry of the vector.
   * 
   * @return Y entry
   */
  public int getY() {
    return entryY;
  }

  /**
   * Set the X entry of the vector.
   * 
   * @param entryX
   *          X entry
   */
  public void setX(int entryX) {
    this.entryX = entryX;
  }

  /**
   * Set the Y entry of the vector.
   * 
   * @param entryY
   *          Y entry
   */
  public void setY(int entryY) {
    this.entryY = entryY;
  }

}
