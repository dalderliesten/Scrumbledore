package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.element.DynamicElement;
import nl.tudelft.scrumbledore.level.element.Platform;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.element.StaticElement;

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

  private DynamicElement collider;
  private StaticElement collidee;

  private Collision collision;

  /**
   * Constructs a new Test Suite instance for the next test case, using parameterized values.
   * 
   * @param collider
   *          The position of the collider LevelElement.
   * @param collidee
   *          The position of the collidee LevelElement.
   * @param colliderSpeed
   *          The speed component of the collider LevelElement.
   * @param expectedTop
   *          Expected outcome of the collision from top check.
   * @param expectedBottom
   *          Expected outcome of the collision from top check.
   * @param expectedLeft
   *          Expected outcome of the collision from top check.
   * @param expectedRight
   *          Expected outcome of the collision from top check.
   */
  public CollisionDirectionTest(Vector collider, Vector collidee, Vector colliderSpeed,
      boolean expectedTop, boolean expectedBottom, boolean expectedLeft, boolean expectedRight) {
    this.expectedTop = expectedTop;
    this.expectedBottom = expectedBottom;
    this.expectedLeft = expectedLeft;
    this.expectedRight = expectedRight;

    this.collider = new Player(collider, new Vector(32, 32));
    this.collidee = new Platform(collidee, new Vector(32, 32));

    this.collider.getSpeed().setX(colliderSpeed.getX());
    this.collider.getSpeed().setY(colliderSpeed.getY());

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
  @SuppressWarnings("checkstyle:methodlength")
  @Parameters
  public static Collection<Object[]> data() {
    Collection<Object[]> input = new ArrayList<Object[]>();
    // Same position should not classify as a collision from any direction.
    input.add(new Object[] { new Vector(0, 0), new Vector(0, 0), new Vector(0, 0), false, false,
        false, false });

    // Touching within collision precision.
    // Touching Top.
    input.add(new Object[] { new Vector(33, 32), new Vector(32, 64), new Vector(0, 0), true, false,
        false, false });
    input.add(new Object[] { new Vector(32, 32 - Constants.COLLISION_PRECISION), new Vector(32, 64),
        new Vector(0, 0), false, false, false, false });
    // Touching Left.
    input.add(new Object[] { new Vector(0, 32), new Vector(32, 32), new Vector(0, 0), false, false,
        true, false });
    input.add(new Object[] { new Vector(0 - Constants.COLLISION_PRECISION, 32), new Vector(32, 32),
        new Vector(0, 0), false, false, false, false });

    // Anticipation of predicted collision.
    // From Top.
    input.add(new Object[] { new Vector(32, 25), new Vector(32, 64), new Vector(0, 8), true, false,
        false, false });
    input.add(new Object[] { new Vector(32, 20), new Vector(32, 64), new Vector(0, 8), false, false,
        false, false });
    input.add(new Object[] { new Vector(0, 31), new Vector(32, 64), new Vector(0, 8), false, false,
        false, false });
    // From Left.
    input.add(new Object[] { new Vector(0, 32), new Vector(48, 32), new Vector(20, 0), false, false,
        true, false });

    return input;
  }
}
