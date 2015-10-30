package nl.tudelft.scrumbledore.level.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.element.PlayerElement;
import nl.tudelft.scrumbledore.level.element.PlayerElementTest;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Tests the TurtleTaco powerup class.
 * @author Floris Doolaard
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class TurtleTacoTest extends PlayerElementTest {
  private TurtleTaco player;
  private Player wrapped;
  private Vector position;
  private Vector size;

  /**
   * Initiatlizes objects.
   */
  @Before
  public void before() {
    position = new Vector(16, 32);
    size = new Vector(42, 42);   
    wrapped = new Player(position, size);
    player = new TurtleTaco(wrapped);
  }

  @Override
  public TurtleTaco make(Vector position, Vector size) {
    PlayerElement tmpPlayer = new Player(position, size);
    return new TurtleTaco(tmpPlayer);
  }

  @Override
  public void testHasActionTrue() {
    player.addAction(LevelElementAction.MoveLeft);
    assertTrue(player.hasAction(LevelElementAction.MoveLeft));
  }

  @Override
  public void testFiring() {
    player.setFiring(true);
    assertTrue(player.isFiring());
  }

  @Override
  public void testAlive() {
    player.setAlive(false);
    assertFalse(player.isAlive());
  }

  @Override
  public void testPlayerNumber() {
    player.setPlayerNumber(42);
    assertEquals(42, player.getPlayerNumber());
  }

  @Override
  public void testLastMove() {
    player.setPlayerNumber(42);
    assertEquals(42, player.getPlayerNumber());
  }

  @Override
  public void testEqualsTrue() {
    player.setLastMove(LevelElementAction.MoveLeft);
    assertEquals(LevelElementAction.MoveLeft, player.getLastMove());
    player.setLastMove(LevelElementAction.MoveRight);
    assertEquals(LevelElementAction.MoveRight, player.getLastMove());
    // Actions other than MoveLeft and MoveRight should be ignored.
    player.setLastMove(LevelElementAction.Jump);
    assertEquals(LevelElementAction.MoveRight, player.getLastMove());
  }

  @Override
  public void testEqualsFalse() {
    Player player2 = new Player(player.getPosition(), player.getSize());
    assertEquals(wrapped, player2);
  }

  @Override
  public void testEqualsFalseOtherClass() {
    Player player2 = new Player(new Vector(42, 42), player.getSize());
    assertFalse(player.equals(player2));
  }

  @Override
  public void testHashCode() {
    assertEquals(0, player.hashCode());
  }

  @Override
  public void testGetSprites() {
    ArrayList<Sprite> sprites = player.getSprites(1);

    assertEquals(2, sprites.size());
    assertEquals("fire-green-00", sprites.get(0).getID());
    assertEquals("frame-01", sprites.get(1).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "fire-green/fire-green-00.png", sprites.get(0).getPath());
  }

  @Override
  public void testGetSpritesShootingRight() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveRight);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(2, sprites.size());
    assertEquals("player-shoot-right", sprites.get(1).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "player-green-shoot-right/player-shoot-right.png", sprites.get(1).getPath());
  }

  @Override
  public void testGetSpritesShootingLeft() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveLeft);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(2, sprites.size());
    assertEquals("player-shoot-left", sprites.get(1).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "player-green-shoot-left/player-shoot-left.png", sprites.get(1).getPath());
  }

  @Override
  public void testLifetime() {
    player.setLifetime(42d);
    assertEquals(42d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }

  @Override
  public void testDecreaseLifetime() {
    player.setLifetime(10d);
    player.decreaseLifetime(1d);
    assertEquals(9.3d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Tests the gravity of the object.
   */
  @Test
  public void testGravity() {
    assertTrue(player.hasGravity());
    player.setGravity(false);
    assertFalse(player.hasGravity());
  }
  
  /**
   * Tests whether object clears all actions.
   */
  @Test
  public void testClearAction() {
    player.addAction(LevelElementAction.MoveLeft);
    assertTrue(player.hasAction(LevelElementAction.MoveLeft));
    player.clearActions();
    assertEquals(0, player.getActions().size());
  }
}
