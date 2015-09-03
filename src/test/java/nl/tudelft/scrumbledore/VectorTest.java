package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Case for Vector Class.
 * 
 * @author Jesse Tilro
 *
 */
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
   * The setX method should set the entryX property properly.
   */
  @Test
  public void testSetX() {
    Vector testVector = new Vector(0, 0);
    testVector.setX(1);
    assertEquals(1, testVector.getX());
  }

  /**
   * The setY method should set the entryX property properly.
   */
  @Test
  public void testSetY() {
    Vector testVector = new Vector(0, 0);
    testVector.setY(2);
    assertEquals(2, testVector.getY());
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
