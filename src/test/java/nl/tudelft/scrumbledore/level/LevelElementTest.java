package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;

/**
 * Test Suite for LevelElement class.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
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
   * Test whether the distance to another LevelElement is calculated correctly.
   */
  @Test
  public void testDistance() {
    LevelElement l1 = make(new Vector(0, 0), size);
    LevelElement l2 = make(new Vector(3, 4), new Vector(32, 32));
    assertEquals(5, l1.distance(l2), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test for the inRadiusRangeOf method.
   */
  @Test
  public void testInRadiusRangeOfTrue() {
    LevelElement l1 = make(new Vector(0, 0), size);
    LevelElement l2 = make(new Vector(0, 8), size);

    assertTrue(l1.inRadiusRangeOf(l2, 8));
  }

  /**
   * Test for the inRadiusRangeOf method.
   */
  @Test
  public void testInRadiusRangeOfFalse() {
    LevelElement l1 = make(new Vector(0, 0), size);
    LevelElement l2 = make(new Vector(0, 9), size);

    assertFalse(l1.inRadiusRangeOf(l2, 8));
  }

  /**
   * Test for the inRadiusRangeOf method.
   */
  @Test
  public void testInBoxRangeOfTrue() {
    LevelElement l1 = make(new Vector(0, 0), size);
    LevelElement l2 = make(new Vector(0, 8), size);

    assertTrue(l1.inBoxRangeOf(l2, 8));
  }

  /**
   * Test for the inRadiusRangeOf method.
   */
  @Test
  public void testInBoxRangeOfFalse() {
    LevelElement l1 = make(new Vector(0, 0), size);
    LevelElement l2 = make(new Vector(0, 9), size);

    assertFalse(l1.inBoxRangeOf(l2, 8));
  }

  /**
   * Test the posX and posY methods.
   */
  @Test
  public void testPos() {
    LevelElement l1 = make(new Vector(42, 21), size);

    assertEquals(42, l1.posX(), Constants.DOUBLE_PRECISION);
    assertEquals(21, l1.posY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test width() method.
   */
  @Test
  public void testWidth() {
    LevelElement l1 = make(new Vector(42, 21), new Vector(16, 32));
    assertEquals(16, l1.width(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the height() method.
   */
  @Test
  public void testHeight() {
    LevelElement l1 = make(position, new Vector(16, 32));
    assertEquals(32, l1.height(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the vSpeed and hSpeed methods.
   */
  @Test
  public void testSpeed() {
    LevelElement l1 = make(position, size);
    l1.getSpeed().setX(5);
    l1.getSpeed().setY(7);

    assertEquals(5, l1.hSpeed(), Constants.DOUBLE_PRECISION);
    assertEquals(7, l1.vSpeed(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the hFric and vFric methods.
   */
  @Test
  public void testFric() {
    LevelElement l1 = make(position, size);
    l1.getFriction().setX(5);
    l1.getFriction().setY(7);

    assertEquals(5, l1.hFric(), Constants.DOUBLE_PRECISION);
    assertEquals(7, l1.vFric(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When stopping a Level Element horizontally, its horizontal speed should be zero.
   */
  @Test
  public void testStopHorizontally() {
    LevelElement el = new Bubble(new Vector(0, 0), new Vector(0, 0));
    el.getSpeed().setX(42);
    el.stopHorizontally();
    assertEquals(0, el.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When stopping a Level Element vertically, its vertical speed should be zero.
   */
  @Test
  public void testStopVertically() {
    LevelElement el = new Bubble(new Vector(0, 0), new Vector(0, 0));
    el.getSpeed().setY(42);
    el.stopVertically();
    assertEquals(0, el.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the left side of another one using the snapLeft
   * method.
   */
  @Test
  public void testSnapLeft() {
    LevelElement snapper = new Player(new Vector(0, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(64, 0), new Vector(32, 32));

    snapper.snapLeft(snapTo);

    assertEquals(32, snapper.getPosition().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the right side of another one using the snapRight
   * method.
   */
  @Test
  public void testSnapRight() {
    LevelElement snapper = new Player(new Vector(64, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 0), new Vector(32, 32));

    snapper.snapRight(snapTo);

    assertEquals(32, snapper.getPosition().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the top side of another one using the snapTop
   * method.
   */
  @Test
  public void testSnapTop() {
    LevelElement snapper = new Player(new Vector(0, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 64), new Vector(32, 32));

    snapper.snapTop(snapTo);

    assertEquals(32, snapper.getPosition().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the top side of another one using the snapTop
   * method.
   */
  @Test
  public void testSnapBottom() {
    LevelElement snapper = new Player(new Vector(0, 64), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 0), new Vector(32, 32));

    snapper.snapBottom(snapTo);

    assertEquals(32, snapper.getPosition().getY(), Constants.DOUBLE_PRECISION);
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
