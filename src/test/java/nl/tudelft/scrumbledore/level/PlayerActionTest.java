package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * Test suite for the Player Action enum.
 * 
 * @author Niels Warnars
 */
public class PlayerActionTest {
  
  /**
   * Test the invertAction method with a given
   * MoveRight action. 
   * Expected outcome: MoveStop.
   */
  @Test
  public final void testInvertActionMoveRight() {
    PlayerAction result = 
        PlayerAction.invertAction(PlayerAction.MoveRight);
    assertEquals(PlayerAction.MoveStop, result);
  }

  /**
   * Test the invertAction method with a given
   * MoveLeft action.
   * Expected outcome: MoveStop.
   */
  @Test
  public final void testInvertActionMoveLeft() {
    PlayerAction result = 
        PlayerAction.invertAction(PlayerAction.MoveLeft);
    assertEquals(PlayerAction.MoveStop, result);
  }
  
  /**
   * Test the invertAction method with a given
   * Shoot action.
   * Expected outcome: ShootStop.
   */
  @Test
  public final void testInvertActionShoot() {
    PlayerAction result = 
        PlayerAction.invertAction(PlayerAction.Shoot);
    assertEquals(PlayerAction.ShootStop, result);
  }
  
  /**
   * Test the invertAction method with an unsupported 
   * action.
   * Expected outcome: null.
   */
  @Test
  public final void testInvertActionUnsupported() {
    PlayerAction result = 
        PlayerAction.invertAction(PlayerAction.Jump);
    assertEquals(null, result);
  }

  /**
   * Test the invertAction method with null given.
   * Expected outcome: null.
   */
  @Test
  public final void testInvertActionNull() {
    PlayerAction result = 
        PlayerAction.invertAction(null);
    assertEquals(null, result);
  }
}
