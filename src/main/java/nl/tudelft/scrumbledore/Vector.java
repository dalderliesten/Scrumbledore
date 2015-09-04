package nl.tudelft.scrumbledore;

/**
 * Class representing a 2D vector, to be used in the game to represent for example a vector,
 * position or size.
 * 
 * @author Jesse Tilro
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
   * Summing two vectors into one.
   * @param vector
   *          The vector to add to the current one.
   */
  public void sum(Vector vector) {
    this.entryX += vector.getX();
    this.entryY += vector.getY();
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

  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Vector) {
      Vector that = (Vector) other;
      return (this.getX() == that.getX() && this.getY() == that.getY());
    }
    return false;
  }

}
