package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
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

}
