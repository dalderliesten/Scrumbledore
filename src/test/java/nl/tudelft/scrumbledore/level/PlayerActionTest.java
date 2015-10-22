package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test suite for the Player Action enum.
 * 
 * @author Niels Warnars
 */
public class PlayerActionTest {

  /**
   * Test the invertAction method with a given MoveRight action. Expected outcome: MoveStop.
   */
  @Test
  public final void testInvertActionMoveRight() {
    LevelElementAction result = LevelElementAction.invertAction(LevelElementAction.MoveRight);
    assertEquals(LevelElementAction.MoveStop, result);
  }

  /**
   * Test the invertAction method with a given MoveLeft action. Expected outcome: MoveStop.
   */
  @Test
  public final void testInvertActionMoveLeft() {
    LevelElementAction result = LevelElementAction.invertAction(LevelElementAction.MoveLeft);
    assertEquals(LevelElementAction.MoveStop, result);
  }

  /**
   * Test the invertAction method with a given Shoot action. Expected outcome: ShootStop.
   */
  @Test
  public final void testInvertActionShoot() {
    LevelElementAction result = LevelElementAction.invertAction(LevelElementAction.Shoot);
    assertEquals(LevelElementAction.ShootStop, result);
  }

  /**
   * Test the invertAction method with an unsupported action. Expected outcome: null.
   */
  @Test
  public final void testInvertActionUnsupported() {
    LevelElementAction result = LevelElementAction.invertAction(LevelElementAction.Jump);
    assertEquals(null, result);
  }

  /**
   * Test the invertAction method with null given. Expected outcome: null.
   */
  @Test
  public final void testInvertActionNull() {
    LevelElementAction result = LevelElementAction.invertAction(null);
    assertEquals(null, result);
  }
}
