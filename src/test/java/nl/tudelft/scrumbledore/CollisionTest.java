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
public class CollisionTest {

  private boolean expectedOut;

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
   * @param expectedOut
   *          Expected outcome of the assertion.
   */
  public CollisionTest(double colliderX, double colliderY, double collideeX, double collideeY,
      boolean expectedOut) {
    this.expectedOut = expectedOut;

    this.collider = new Player(new Vector(colliderX, colliderY), new Vector(32, 32));
    this.collidee = new Platform(new Vector(collideeX, collideeY), new Vector(32, 32));

    this.collision = new Collision(this.collider, this.collidee);
  }

  /**
   * 
   */
  @Test
  public void testColliding() {
    boolean actualOut = collision.colliding();
    assertEquals(expectedOut, actualOut);
  }

  /**
   * Data for the boundary tests.
   * 
   * @return Collection of tuples of input values.
   */
  @Parameters
  public static Collection<Object[]> data() {
    Collection<Object[]> input = new ArrayList<Object[]>();
    // Same position should be colliding.
    input.add(new Object[] { 0, 0, 0, 0, true });
    // Corners touching
    input.add(new Object[] { 32, 32, 64, 64, true });
    input.add(new Object[] { 31, 32, 64, 64, false });
    input.add(new Object[] { 96, 32, 64, 64, true });
    input.add(new Object[] { 96, 31, 64, 64, false });
    input.add(new Object[] { 96, 96, 64, 64, true });
    input.add(new Object[] { 97, 96, 64, 64, false });
    input.add(new Object[] { 32, 96, 64, 64, true });
    input.add(new Object[] { 32, 97, 64, 64, false });
    // Edges touching
    input.add(new Object[] { 32, 32, 64, 32, true });
    input.add(new Object[] { 31, 32, 64, 32, false });
    input.add(new Object[] { 64, 32, 64, 64, true });
    input.add(new Object[] { 64, 32, 64, 65, false });
    input.add(new Object[] { 64, 32, 32, 32, true });
    input.add(new Object[] { 65, 32, 32, 32, false });
    input.add(new Object[] { 32, 64, 32, 32, true });
    input.add(new Object[] { 32, 65, 32, 32, false });
    // Random
    input.add(new Object[] { 14, 27, 40, 22, true });
    input.add(new Object[] { 16, 12, 45, 60, false });
    return input;
  }
}
