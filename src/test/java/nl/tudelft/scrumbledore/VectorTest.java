package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Suite for Vector Class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class VectorTest {

  /**
   * The constructor of the vector class should set the properties correctly. The getters are also
   * tested this way.
   */
  @Test
  public void testConstructor() {
    Vector testVector = new Vector(1, 2);
    assertTrue(testVector.getX() == 1 && testVector.getY() == 2);
  }

  /**
   * The sum between to vectors should be calculated correctly.
   */
  @Test
  public void testSum() {
    Vector v1 = new Vector(1, 2);
    Vector v2 = new Vector(3, 4);
    v1.sum(v2);
    assertEquals(new Vector(4, 6), v1);
  }

  /**
   * The difference between to vectors should be calculated correctly.
   */
  @Test
  public void testDifference() {
    Vector v1 = new Vector(1, 2);
    Vector v2 = new Vector(3, 4);
    v1.difference(v2);
    assertEquals(new Vector(-2, -2), v1);
  }

  /**
   * The vector should be able to be scaled correctly.
   */
  @Test
  public void testScale() {
    Vector v1 = new Vector(8, 16);
    v1.scale(2);
    assertEquals(new Vector(16, 32), v1);
  }

  /**
   * The sum between to vectors should be calculated correctly.
   */
  @Test
  public void testSumStatic() {
    Vector v1 = new Vector(1, 2);
    Vector v2 = new Vector(3, 4);
    Vector res = Vector.sum(v1, v2);
    assertEquals(new Vector(4, 6), res);
  }

  /**
   * The sum between to vectors should be calculated correctly.
   */
  @Test
  public void testDifferenceStatic() {
    Vector v1 = new Vector(1, 2);
    Vector v2 = new Vector(3, 4);
    Vector res = Vector.difference(v1, v2);
    assertEquals(new Vector(-2, -2), res);
  }

  /**
   * The vector should be able to be scaled correctly.
   */
  @Test
  public void testScaleStatic() {
    Vector v1 = new Vector(8, 16);
    Vector res = Vector.scale(v1, 2);
    assertEquals(new Vector(16, 32), res);
  }

  /**
   * The dot product between to vectors should be calculated correctly.
   */
  @Test
  public void testDotproduct() {
    Vector v1 = new Vector(1, 2);
    Vector v2 = new Vector(3, 4);
    double dot = v1.dotProduct(v2);
    assertEquals(11, dot, Constants.DOUBLE_PRECISION);
  }
  
  /**
   * The length of a vector should be calculated correctly.
   */
  @Test
  public void testLength() {
    Vector vec = new Vector(3, 4);
    assertEquals(5, vec.length(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * The distance between two vectors should be calculated correctly.
   */
  @Test
  public void testDistance() {
    Vector v1 = new Vector(3, 4);
    Vector v2 = new Vector(6, 8);
    assertEquals(5, v1.distance(v2), Constants.DOUBLE_PRECISION);
  }

  /**
   * The setX method should set the entryX property properly.
   */
  @Test
  public void testSetX() {
    Vector testVector = new Vector(0, 0);
    testVector.setX(1);
    assertEquals(1, testVector.getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The setY method should set the entryX property properly.
   */
  @Test
  public void testSetY() {
    Vector testVector = new Vector(0, 0);
    testVector.setY(2);
    assertEquals(2, testVector.getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Two Vectors with the same X and Y should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    Vector testVector1 = new Vector(1, 2);
    Vector testVector2 = new Vector(1, 2);
    assertEquals(testVector1, testVector2);
  }

  /**
   * Two Vectors with different X and Y should not be considered equal.
   */
  @Test
  public void testEqualsFalse() {
    Vector testVector1 = new Vector(1, 2);
    Vector testVector2 = new Vector(1, 3);
    assertFalse(testVector1.equals(testVector2));
  }

}
