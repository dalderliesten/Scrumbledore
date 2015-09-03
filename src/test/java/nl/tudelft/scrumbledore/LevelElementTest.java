package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for LevelElement class.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class LevelElementTest {

  /**
   * Returns an instance of a LevelElement subclass to be tested.
   * 
   * @return A LevelElement test object.
   * @param position
   *          The position to be passed into the constructor of the LevelElement.
   * @param size
   *          The size to be passed into the constructor of the LevelElement.
   */
  public abstract LevelElement make(Vector position, Vector size);

  /**
   * The constructor of a LevelElement should properly set the object properties.
   */
  @Test
  public void testConstructor() {
    Vector position = new Vector(16, 32);
    Vector size = new Vector(42, 42);
    LevelElement testElement = make(position, size);

    assertEquals(position, testElement.getPosition());
    assertEquals(size, testElement.getSize());
    assertEquals(false, testElement.hasGravity());
    assertEquals(new Vector(0, 0), testElement.getSpeed());
  }
}
