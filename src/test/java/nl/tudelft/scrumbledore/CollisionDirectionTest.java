package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test Suite for the Collision class.
 * 
 * @author Jesse Tilro
 *
 */
@RunWith(Parameterized.class)
public class CollisionDirectionTest {

  private boolean expectedTop;
  private boolean expectedBottom;
  private boolean expectedLeft;
  private boolean expectedRight;

  private LevelElement collider;
  private LevelElement collidee;

  private Collision collision;

  /**
   * Constructs a new Test Suite instance for the next test case, using parameterized values.
   * 
   * @param colliderX
   *          X-coordinate of the position of the collider LevelElement.
   * @param colliderY
   *          Y-coordinate of the position of the collider LevelElement.
   * @param collideeX
   *          X-coordinate of the position of the collidee LevelElement.
   * @param collideeY
   *          Y-coordinate of the position of the collidee LevelElement.
   * @param colliderSpeedX
   *          The horizontal speed component of the collider LevelElement.
   * @param colliderSpeedY
   *          The vertical speed component of the collider LevelElement.
   * @param expectedTop
   *          Expected outcome of the collision from top check.
   * @param expectedBottom
   *          Expected outcome of the collision from top check.
   * @param expectedLeft
   *          Expected outcome of the collision from top check.
   * @param expectedRight
   *          Expected outcome of the collision from top check.
   */
  public CollisionDirectionTest(double colliderX, double colliderY, double collideeX,
      double collideeY, double colliderSpeedX, double colliderSpeedY, boolean expectedTop,
      boolean expectedBottom, boolean expectedLeft, boolean expectedRight) {
    this.expectedTop = expectedTop;
    this.expectedBottom = expectedBottom;
    this.expectedLeft = expectedLeft;
    this.expectedRight = expectedRight;

    this.collider = new Player(new Vector(colliderX, colliderY), new Vector(32, 32));
    this.collidee = new Platform(new Vector(collideeX, collideeY), new Vector(32, 32));

    this.collider.getSpeed().setX(colliderSpeedX);
    this.collider.getSpeed().setY(colliderSpeedY);

    this.collision = new Collision(this.collider, this.collidee, 1);
  }

  /**
   * Test the collidingFromTop method.
   */
  @Test
  public void testCollidingFromTop() {
    boolean actualOut = collision.collidingFromTop();
    assertEquals(expectedTop, actualOut);
  }

  /**
   * Test the collidingFromBottom method.
   */
  @Test
  public void testCollidingFromBottom() {
    boolean actualOut = collision.collidingFromBottom();
    assertEquals(expectedBottom, actualOut);
  }

  /**
   * Test the collidingFromLeft method.
   */
  @Test
  public void testCollidingFromLeft() {
    boolean actualOut = collision.collidingFromLeft();
    assertEquals(expectedLeft, actualOut);
  }

  /**
   * Test the collidingFromRight method.
   */
  @Test
  public void testCollidingFromRight() {
    boolean actualOut = collision.collidingFromRight();
    assertEquals(expectedRight, actualOut);
  }

  /**
   * Data for the boundary tests.
   * 
   * @return Collection of tuples of input values.
   */
  @Parameters
  public static Collection<Object[]> data() {
    Collection<Object[]> input = new ArrayList<Object[]>();
    // Same position should not classify as a collision from any direction.
    input.add(new Object[] { 0, 0, 0, 0, 0, 0, false, false, false, false });

    // Touching within collision precision.
    // Touching Top.
    input.add(new Object[] { 33, 32, 32, 64, 0, 0, true, false, false, false });
    input.add(new Object[] { 32, 32 - Constants.COLLISION_PRECISION, 32, 64, 0, 0, false, false,
        false, false });
    // Touching Left.
    input.add(new Object[] { 0, 32, 32, 32, 0, 0, false, false, true, false });
    input.add(new Object[] { 0 - Constants.COLLISION_PRECISION, 32, 32, 32, 0, 0, false, false,
        false, false });

    // Anticipation of predicted collision.
    // From Top.
    input.add(new Object[] { 32, 25, 32, 64, 0, 8, true, false, false, false });
    input.add(new Object[] { 32, 20, 32, 64, 0, 8, false, false, false, false });
    input.add(new Object[] { 0, 31, 32, 64, 0, 8, false, false, false, false });
    // From Left.
    input.add(new Object[] { 0, 32, 48, 32, 20, 0, false, false, true, false });

    return input;
  }
}
