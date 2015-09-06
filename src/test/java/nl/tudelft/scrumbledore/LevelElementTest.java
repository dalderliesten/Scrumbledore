package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for LevelElement class.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class LevelElementTest {
  private Vector position;
  private Vector size;
  
  /**
   * Setting up properties used in the test class.
   */
  @Before
  public void before() {
    position = new Vector(16, 32);
    size = new Vector(42, 42);
  }

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
    LevelElement testElement = make(position, size);

    assertEquals(position, testElement.getPosition());
    assertEquals(size, testElement.getSize());
    assertEquals(new Vector(0, 0), testElement.getSpeed());
  }

  /**
   * The Y-coordinate of the top of the element should be calculated correctly given the center
   * position and height.
   */
  @Test
  public void testGetTop() {
    int y = 64;
    int height = 32;
    int top = y - height / 2;

    Vector position = new Vector(128, y);
    Vector size = new Vector(32, height);
    LevelElement testElement = make(position, size);

    assertEquals(top, testElement.getTop(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The Y-coordinate of the bottom of the element should be calculated correctly given the center
   * position and height.
   */
  @Test
  public void testGetBottom() {
    int y = 128;
    int height = 32;
    int bottom = y + height / 2;

    Vector position = new Vector(256, y);
    Vector size = new Vector(64, height);
    LevelElement testElement = make(position, size);

    assertEquals(bottom, testElement.getBottom(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The X-coordinate of the left side of the element should be calculated correctly given the
   * center position and width.
   */
  @Test
  public void testGetLeft() {
    int x = 64;
    int width = 32;
    int left = x - width / 2;

    Vector position = new Vector(x, 128);
    Vector size = new Vector(width, 96);
    LevelElement testElement = make(position, size);

    assertEquals(left, testElement.getLeft(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The X-coordinate of the right side of the element should be calculated correctly given the
   * center position and width.
   */
  @Test
  public void testGetRight() {
    int x = 128;
    int width = 32;
    int right = x + width / 2;

    Vector position = new Vector(x, 64);
    Vector size = new Vector(width, 32);
    LevelElement testElement = make(position, size);

    assertEquals(right, testElement.getRight(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Cleaning up test properties after testing.
   */
  @After
  public void after() {
    position = null;
    size = null;
  }

}
