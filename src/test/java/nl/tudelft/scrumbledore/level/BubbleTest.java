package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.projectile.Bubble;

/**
 * Test Suite for the Bubble class.
 * 
 * @author Jesse Tilro
 *
 */
public class BubbleTest extends LevelElementTest {

  private Bubble test;

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Bubble(position, size);
  }

  /**
   * Set the bubble field to a new Bubble instance to use as a test object.
   */
  @Before
  public void setUp() {
    test = new Bubble(new Vector(0, 0), new Vector(0, 0));
  }

  /**
   * When a new Bubble element is created, it should initially have a horizontal friction and
   * lifetime according to the constants.
   */
  @Test
  public void testConstuctor() {
    assertEquals(Constants.BUBBLE_FRICTION, test.getFriction().getX(), Constants.DOUBLE_PRECISION);
    assertEquals(Constants.BUBBLE_LIFETIME, test.getLifetime(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Decreasing the lifetime with a given delta should actually decrease the lifetime field value.
   */
  @Test
  public void testDecreaseLifetime() {
    double delta = 42;
    test.decreaseLifetime(delta);
    assertEquals(Constants.BUBBLE_LIFETIME - delta, test.getLifetime(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a bubble action is added to a bubble's action queue, a call to hasAction for that action
   * should return true.
   */
  @Test
  public void testHasActionTrue() {
    test.addAction(LevelElementAction.MoveLeft);
    assertTrue(test.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When a bubble action was not added to a bubble's action queue, a call to hasAction for that
   * action should return false.
   */
  @Test
  public void testHasActionFalse() {
    test.addAction(LevelElementAction.MoveLeft);
    assertFalse(test.hasAction(LevelElementAction.MoveRight));
  }

  /**
   * When a bubble's action queue is cleared, it should not have the actions anymore which were
   * added before.
   */
  @Test
  public void testClearActions() {
    test.addAction(LevelElementAction.MoveLeft);
    test.clearActions();
    assertFalse(test.hasAction(LevelElementAction.MoveLeft));
  }
}
