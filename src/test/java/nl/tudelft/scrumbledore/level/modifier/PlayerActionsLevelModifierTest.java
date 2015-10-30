package nl.tudelft.scrumbledore.level.modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.powerup.ChiliChicken;
import nl.tudelft.scrumbledore.level.powerup.TurtleTaco;

/**
 * Test Suite for the Player Actions Level Modifier class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
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
    player.addAction(LevelElementAction.MoveLeft);
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
    player.addAction(LevelElementAction.MoveRight);
    modifier.modify(level, .5);
    double expectedOut = Constants.PLAYER_SPEED;
    assertEquals(expectedOut, player.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified, the Player is a powerup and its Player has the action to move left,
   * then the player's speed vector x entry should be set negative.
   */
  @Test
  public void testModifyMoveLeftChiliChicken() {
    player.addAction(LevelElementAction.MoveLeft);
    level.getPlayers().set(0, new ChiliChicken(player));
    modifier.modify(level, .5);
    double expectedOut = -Constants.PLAYER_SPEED * Constants.PLAYER_CHILI_MULTIPLIER;
    assertEquals(expectedOut, player.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified, the Player is a powerup and its Player has the action to move right,
   * then the player's speed vector x entry should be set positive.
   */
  @Test
  public void testModifyMoveRightChiliChicken() {
    player.addAction(LevelElementAction.MoveRight);
    level.getPlayers().set(0, new ChiliChicken(player));
    modifier.modify(level, .5);
    double expectedOut = Constants.PLAYER_SPEED * Constants.PLAYER_CHILI_MULTIPLIER;
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
    player.addAction(LevelElementAction.MoveStop);
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
    player.addAction(LevelElementAction.Jump);
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
    player.addAction(LevelElementAction.Jump);
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
    player.addAction(LevelElementAction.Shoot);
    player.addAction(LevelElementAction.MoveStop);
    modifier.modify(level, .5);
    assertFalse(player.hasAction(LevelElementAction.MoveStop));
    assertFalse(player.hasAction(LevelElementAction.Shoot));
  }

  /**
   * When a Level is modified and its Player has the action to stop shooting, then the shooting
   * action should be removed and the player should no longer be firing.
   */
  @Test
  public void testModifyStopShooting() {
    player.setFiring(true);
    player.addAction(LevelElementAction.ShootStop);
    modifier.modify(level, .5);
    assertFalse(player.isFiring());
    assertFalse(player.hasAction(LevelElementAction.ShootStop));
  }

  /**
   * When a player is not alive, after the modify the actions list should be cleared.
   */
  @Test
  public void testModifyNotAlive() {
    player.setAlive(false);
    player.addAction(LevelElementAction.Jump);
    modifier.modify(level, .5);
    assertTrue(player.getActions().isEmpty());
  }

  /**
   * When a Level is modified and the player is a powerup, then the lifetime of said powerup should
   * decrease.
   */
  @Test
  public void testPowerUpCountDown() {
    level.getPlayers().set(0, new ChiliChicken(player));
    assertEquals(player.getLifetime(), 90, Constants.DOUBLE_PRECISION);
    modifier.modify(level, 0.5);
    assertTrue(player.getLifetime() < 90);
  }

  /**
   * When a Level is modified and the powerup countdown is smaller or equal to zero, the
   * PlayerElement should be a player again.
   */
  @Test
  public void testChiliChickenCountDownEnd() {
    level.getPlayers().set(0, new ChiliChicken(player));
    modifier.modify(level, 250);
    modifier.modify(level, 0.5);
    assertTrue(level.getPlayers().get(0) instanceof Player);
  }

  /**
   * When a Level is modified and the powerup countdown is smaller or equal to zero, the
   * PlayerElement should be a player again.
   */
  @Test
  public void testTurtleTacoCountDownEnd() {
    level.getPlayers().set(0, new TurtleTaco(player));
    modifier.modify(level, 250);
    modifier.modify(level, 0.5);
    assertTrue(level.getPlayers().get(0) instanceof Player);
  }

  /**
   * When a Level is modified and the Player has a shoot action, a bubble should be spawned.
   */
  @Test
  public void testShoot() {
    player.addAction(LevelElementAction.MoveLeft);
    modifier.modify(level, 0.5);
    player.addAction(LevelElementAction.Shoot);
    modifier.modify(level, 0.5);
    assertEquals(level.getBubbles().size(), 1);
  }
}
