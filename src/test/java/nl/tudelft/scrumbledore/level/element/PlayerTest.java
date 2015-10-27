package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test Suite for the Player class.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
@SuppressWarnings("PMD.TooManyMethods")
public class PlayerTest extends LevelElementTest {

  private Player player;

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Player(position, size);
  }

  /**
   * Set the bubble field to a new Bubble instance to use as a test object.
   */
  @Before
  public void setUp() {
    player = new Player(new Vector(0, 0), new Vector(0, 0));
  }

  /**
   * When a new Player element is created, initially it should have gravity, be alive, should not be
   * firing and looking right.
   */
  @Test
  public void testConstuctor() {
    assertTrue(player.hasGravity());
    assertTrue(player.isAlive());
    assertFalse(player.isFiring());
    assertEquals(LevelElementAction.MoveRight, player.getLastMove());
  }

  /**
   * When a player action is added to a bubble's action queue, a call to hasAction for that action
   * should return true.
   */
  @Test
  public void testHasActionTrue() {
    player.addAction(LevelElementAction.MoveLeft);
    assertTrue(player.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When a player action was not added to a bubble's action queue, a call to hasAction for that
   * action should return false.
   */
  @Test
  public void testHasActionFalse() {
    player.addAction(LevelElementAction.MoveLeft);
    assertFalse(player.hasAction(LevelElementAction.MoveRight));
  }

  /**
   * When a player action queue is cleared, it should not have the actions anymore which were added
   * before.
   */
  @Test
  public void testClearActions() {
    player.addAction(LevelElementAction.MoveLeft);
    player.clearActions();
    assertFalse(player.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When a player has an action removed from its action queue, it should no longer have the action.
   */
  @Test
  public void testRemoveAction() {
    player.addAction(LevelElementAction.MoveLeft);
    player.addAction(LevelElementAction.MoveLeft);
    player.removeAction(LevelElementAction.MoveLeft);
    assertFalse(player.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When the Player.getActions is called it should return all the actions 
   * assigned to a player object.
   */
  @Test
  public void testGetActions() {
    player.clearActions();
    player.addAction(LevelElementAction.MoveLeft);
    player.addAction(LevelElementAction.MoveRight);
    assertEquals(2, player.getActions().size());
    assertEquals(LevelElementAction.MoveLeft, player.getActions().get(0));
    assertEquals(LevelElementAction.MoveRight, player.getActions().get(1));
  }
  
  /**
   * Test the firing field getter/setter.
   */
  @Test
  public void testFiring() {
    player.setFiring(true);
    assertTrue(player.isFiring());
  }

  /**
   * Test the alive field getter/setter.
   */
  @Test
  public void testAlive() {
    player.setAlive(false);
    assertFalse(player.isAlive());
  }

  /**
   * Test the id field getter/setter.
   */
  @Test
  public void testPlayerNumber() {
    player.setPlayerNumber(42);
    assertEquals(42, player.getPlayerNumber());
  }

  /**
   * Test the lastMove field getter/setter.
   */
  @Test
  public void testLastMove() {
    player.setLastMove(LevelElementAction.MoveLeft);
    assertEquals(LevelElementAction.MoveLeft, player.getLastMove());
    player.setLastMove(LevelElementAction.MoveRight);
    assertEquals(LevelElementAction.MoveRight, player.getLastMove());
    // Actions other than MoveLeft and MoveRight should be ignored.
    player.setLastMove(LevelElementAction.Jump);
    assertEquals(LevelElementAction.MoveRight, player.getLastMove());
  }

  /**
   * Two player instances with the same position and size should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    Player player2 = new Player(player.getPosition(), player.getSize());
    assertEquals(player, player2);
  }

  /**
   * Two player instances with a different position or size should not be considered equal.
   */
  @Test
  public void testEqualsFalse() {
    Player player2 = new Player(new Vector(42, 42), player.getSize());
    Player player3 = new Player(player.getPosition(), new Vector(42, 42));
    assertFalse(player.equals(player2));
    assertFalse(player.equals(player3));
  }

  /**
   * A Player instance and an instance of another class should not be considered equal.
   */
  @Test
  public void testEqualsFalseOtherClass() {
    Fruit fruit = new Fruit(player.getPosition(), player.getSize());
    assertFalse(player.equals(fruit));
  }

  /**
   * The stubbed method hashCode should just return zero (line coverage).
   */
  @Test
  public void testHashCode() {
    assertEquals(0, player.hashCode());
  }

  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images\\sprites\\player-green-move-right/frame-01.png", sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned when it is shooting to the right.
   */
  @Test
  public void testGetSpritesShootingRight() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveRight);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("player-shoot-right", sprites.get(0).getID());
    assertEquals("images\\sprites\\player-green-shoot-right/player-shoot-right.png", 
        sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned when it is shooting to the left.
   */
  @Test
  public void testGetSpritesShootingLeft() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveLeft);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("player-shoot-left", sprites.get(0).getID());
    assertEquals("images\\sprites\\player-green-shoot-left/player-shoot-left.png", 
        sprites.get(0).getPath());
  }
  
  /**
   * Test the getter/setter methods of the lifetime attribute of a Player object.
   */
  @Test
  public void testLifetime() {
    player.setLifetime(42d);
    assertEquals(42d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the decreasing of the lifetime of a player by a given number of steps.
   */
  @Test
  public void testDecreaseLifetime() {
    player.setLifetime(42d);
    player.decreaseLifetime(1d);
    assertEquals(41d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }
}
