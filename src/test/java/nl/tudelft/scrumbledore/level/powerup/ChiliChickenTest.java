package nl.tudelft.scrumbledore.level.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.element.PlayerElement;
import nl.tudelft.scrumbledore.level.element.PlayerElementTest;
import nl.tudelft.scrumbledore.sprite.Sprite;

@SuppressWarnings("PMD.TooManyMethods")
public class ChiliChickenTest extends PlayerElementTest {

  private Player player;
  private Vector position;
  private Vector size;

  /**
   * Initiatlizes objects.
   */
  @Before
  public void before() {
    position = new Vector(16, 32);
    size = new Vector(42, 42);
    player = new Player(position, size);
  }

  @Override
  public ChiliChicken make(Vector position, Vector size) {
    PlayerElement tmpPlayer = new Player(position, size);
    return new ChiliChicken(tmpPlayer);
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
    assertEquals(player, player2);
  }

  @Override
  public void testEqualsFalseOtherClass() {
    Player player2 = new Player(new Vector(42, 42), player.getSize());
    Player player3 = new Player(player.getPosition(), new Vector(42, 42));
    assertFalse(player.equals(player2));
    assertFalse(player.equals(player3));
  }

  @Override
  public void testHashCode() {
    assertEquals(0, player.hashCode());
  }

  @Override
  public void testGetSprites() {
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "player-green-move-right/frame-01.png", sprites.get(0).getPath());
  }

  @Override
  public void testGetSpritesShootingRight() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveRight);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("player-shoot-right", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "player-green-shoot-right/player-shoot-right.png", sprites.get(0).getPath());
  }

  @Override
  public void testGetSpritesShootingLeft() {
    player.setFiring(true);
    player.setLastMove(LevelElementAction.MoveLeft);
    ArrayList<Sprite> sprites = player.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("player-shoot-left", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "player-green-shoot-left/player-shoot-left.png", sprites.get(0).getPath());
  }

  @Override
  public void testLifetime() {
    player.setLifetime(42d);
    assertEquals(42d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }

  @Override
  public void testDecreaseLifetime() {
    player.setLifetime(42d);
    player.decreaseLifetime(1d);
    assertEquals(41d, player.getLifetime(), Constants.DOUBLE_PRECISION);
  }
}
