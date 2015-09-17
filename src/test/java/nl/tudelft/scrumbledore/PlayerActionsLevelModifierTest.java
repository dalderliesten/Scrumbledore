package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the Player Actions Level Modifier class.
 * 
 * @author Jesse Tilro
 *
 */
public class PlayerActionsLevelModifierTest {

  private Player player;
  private Level level;
  private PlayerActionsLevelModifier modifier;

  /**
   * Setup a test object, Player Actions Level Modifier, and dependencies: a Player and a Level
   * instance.
   */
  @Before
  public void setUp() {
    player = new Player(new Vector(0, 0), new Vector(0, 0));
    level = new Level();
    level.addElement(player);
    modifier = new PlayerActionsLevelModifier();
  }

  /**
   * When a Level is modified and its Player has the action to move left, then the player's speed
   * vector x entry should be set negative.
   */
  @Test
  public void testModifyMoveLeft() {
    player.addAction(PlayerAction.MoveLeft);
    modifier.modify(level, .5);
    double expectedOut = -Constants.PLAYER_SPEED;
    assertEquals(expectedOut, player.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and its Player has the action to move right, then the player's speed
   * vector x entry should be set positive.
   */
  @Test
  public void testModifyMoveRight() {
    player.addAction(PlayerAction.MoveRight);
    modifier.modify(level, .5);
    double expectedOut = Constants.PLAYER_SPEED;
    assertEquals(expectedOut, player.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and its Player has the action to stop moving, then the player's speed
   * vector x entry should be set to zero.
   */
  @Test
  public void testModifyMoveStop() {
    player.getSpeed().setX(42);
    player.getSpeed().setY(1);
    player.addAction(PlayerAction.MoveStop);
    modifier.modify(level, .5);
    double expectedOut = 0;
    assertEquals(expectedOut, player.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and its Player has the action to jump, then the player's speed vector
   * y entry should be set negative.
   */
  @Test
  public void testModifyJump() {
    player.addAction(PlayerAction.Jump);
    modifier.modify(level, .5);
    double expectedOut = -Constants.PLAYER_JUMP;
    assertEquals(expectedOut, player.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and its Player has the action to jump and the player is already
   * vertically moving, then the player's speed vector y entry should not be changed.
   */
  @Test
  public void testModifyJumpNotPossible() {
    player.getSpeed().setY(1);
    player.addAction(PlayerAction.Jump);
    modifier.modify(level, .5);
    double expectedOut = 1;
    assertEquals(expectedOut, player.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified, after processing all actions in the player's action queue, the queue
   * should be cleared.
   */
  @Test
  public void testModifyClear() {
    player.addAction(PlayerAction.Jump);
    player.addAction(PlayerAction.MoveStop);
    player.addAction(PlayerAction.MoveLeft);
    player.addAction(PlayerAction.MoveRight);
    modifier.modify(level, .5);
    assertFalse(player.hasAction(PlayerAction.Jump));
    assertFalse(player.hasAction(PlayerAction.MoveStop));
    assertFalse(player.hasAction(PlayerAction.MoveLeft));
    assertFalse(player.hasAction(PlayerAction.MoveRight));
  }
}