package nl.tudelft.scrumbledore.level;

/**
 * Class representing a 2D vector, to be used in the game to represent for example a vector,
 * position or size.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class Vector implements Cloneable {
  private double entryX;
  private double entryY;

  /**
   * Create a new 2D Vector with given X and Y entries.
   * 
   * @param d
   *          The X entry of the vector.
   * @param e
   *          The Y entry of the vector.
   */
  public Vector(double d, double e) {
    this.entryX = d;
    this.entryY = e;
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
   * Subtracting a given vector from this vector.
   * 
   * @param vector
   *          The vector to subtract from to this vector.
   */
  public void difference(Vector vector) {
    sum(Vector.scale(vector, -1));
  }

  /**
   * Returns a new Vector which is the difference between two given vectors.
   * 
   * @param v1
   *          The first vector.
   * @param v2
   *          The second vector.
   * @return The difference between the first and second vector.
   */
  public static Vector difference(Vector v1, Vector v2) {
    return Vector.sum(v1, Vector.scale(v2, -1));
  }

  /**
   * Scale this vector using a scalar.
   * 
   * @param scalar
   *          A scalar.
   */
  public void scale(double scalar) {
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
  public static Vector scale(Vector vector, double scalar) {
    return new Vector(vector.getX() * scalar, vector.getY() * scalar);
  }

  /**
   * Returns the result of the dotproduct between this vector and another one.
   * 
   * @param vector
   *          The other vector.
   * @return The result of the dotproduct.
   */
  public double dotProduct(Vector vector) {
    return this.entryX * vector.getX() + this.entryY * vector.getY();
  }

  /**
   * Returns the result the length of this vector.
   * 
   * @return The length.
   */
  public double length() {
    return Math.sqrt(dotProduct(this));
  }

  /**
   * Returns the distance between this and a given other vector.
   * 
   * @param vector
   *          The other vector.
   * @return The distance.
   */
  public double distance(Vector vector) {
    Vector dv = Vector.difference(this, vector);
    return dv.length();
  }

  /**
   * Returns a newly cloned Vector object.
   */
  @Override
  public Vector clone() throws CloneNotSupportedException {
    return (Vector) super.clone();
  }

  /**
   * Get the X entry of the vector.
   * 
   * @return X entry
   */
  public double getX() {
    return entryX;
  }

  /**
   * Get the Y entry of the vector.
   * 
   * @return Y entry
   */
  public double getY() {
    return entryY;
  }

  /**
   * Set the X entry of the vector.
   * 
   * @param entryX
   *          X entry
   */
  public void setX(double entryX) {
    this.entryX = entryX;
  }

  /**
   * Set the Y entry of the vector.
   * 
   * @param entryY
   *          Y entry
   */
  public void setY(double entryY) {
    this.entryY = entryY;
  }

  /**
   * Dummy HashCode method to satisfy code quality tools.
   */
  @Override
  public int hashCode() {
    return 0;
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
