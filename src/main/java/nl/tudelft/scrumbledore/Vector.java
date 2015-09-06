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
   * 
   * @param vector
   *          The vector to add to the current one.
   */
  public void sum(Vector vector) {
    this.entryX += vector.getX();
    this.entryY += vector.getY();
  }

  /**
   * Returns a new Vector which is the sum of two given vectors.
   * 
   * @param v1
   *          The first vector.
   * @param v2
   *          The second vector.
   * @return The sum of the first and second vector.
   */
  public static Vector sum(Vector v1, Vector v2) {
    return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
  }

  /**
   * Scale this vector using a scalar.
   * 
   * @param scalar
   *          A scalar.
   */
  public void scale(int scalar) {
    this.entryX *= scalar;
    this.entryY *= scalar;
  }
  
  /**
   * Returns a new Vector which is the product of a given vector and a scalar.
   * 
   * @param vector
   *          The vector to be scaled.
   * @param scalar
   *          The scalar.
   * @return The scaled vector.
   */
  public static Vector scale(Vector vector, int scalar) {
    return new Vector(vector.getX() * scalar, vector.getY() * scalar);
  }

  /**
   * Returns the result of the dotproduct between this vector and another one.
   * 
   * @param vector
   *          The other vector.
   * @return The result of the dotproduct.
   */
  public int dotProduct(Vector vector) {
    return entryX * vector.getX() + entryY * vector.getY();
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
