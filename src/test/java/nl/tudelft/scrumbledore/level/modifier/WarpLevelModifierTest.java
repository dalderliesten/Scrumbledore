package nl.tudelft.scrumbledore.level.modifier;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.Fruit;
import nl.tudelft.scrumbledore.level.element.LevelElement;
import nl.tudelft.scrumbledore.level.element.Player;

/**
 * Test suite for the WarpLevelModifier class.
 * 
 * @author Jesse Tilro
 *
 */
public class WarpLevelModifierTest {

  private WarpLevelModifier warp;

  /**
   * Set up the test object.
   */
  @Before
  public void setUp() {
    warp = new WarpLevelModifier();
  }

  /**
   * When a Level Element has gotten outside the bottom of the level and is subsequently being
   * warped, it should reappear just outside the top of the level with the same X coordinate.
   */
  @Test
  public void testWarpVerticallyBottomToTop() {
    LevelElement el = new Fruit(new Vector(0, 0), new Vector(32, 32));
    el.getPosition().setY(Constants.LEVELY + 17);
    warp.warpVertically(el);
    assertEquals(-16, el.posY(), Constants.DOUBLE_PRECISION);
    assertEquals(0, el.posX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level Element has gotten outside the top of the level and is subsequently being warped,
   * it should reappear just outside the bottom of the level with the same X coordinate.
   */
  @Test
  public void testWarpVerticallyTopToBottom() {
    LevelElement el = new Fruit(new Vector(0, 0), new Vector(32, 32));
    el.getPosition().setY(-17);
    warp.warpVertically(el);
    assertEquals(Constants.LEVELY + 16, el.posY(), Constants.DOUBLE_PRECISION);
    assertEquals(0, el.posX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level Element has gotten outside the right side of the level and is subsequently being
   * warped, it should reappear just outside the left side of the level with the same Y coordinate.
   */
  @Test
  public void testWarpHorizontallyRightToLeft() {
    LevelElement el = new Fruit(new Vector(0, 0), new Vector(32, 32));
    el.getPosition().setX(Constants.LEVELX);
    warp.warpHorizontally(el);
    assertEquals(16, el.posX(), Constants.DOUBLE_PRECISION);
    assertEquals(0, el.posY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level Element has gotten outside the left side of the level and is subsequently being
   * warped, it should reappear just outside the right side of the level with the same Y coordinate.
   */
  @Test
  public void testWarpHorizontallyLeftToRight() {
    LevelElement el = new Fruit(new Vector(0, 0), new Vector(32, 32));
    el.getPosition().setX(0);
    warp.warpHorizontally(el);
    assertEquals(Constants.LEVELX - 16, el.posX(), Constants.DOUBLE_PRECISION);
    assertEquals(0, el.posY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and the Player is moving outside the bottom of the Level, the Player
   * should be warped to the top of the Level.
   */
  @Test
  public void testModifyPlayerWarp() {
    Player player = new Player(new Vector(0, Constants.LEVELY + 1), new Vector(0, 0));
    Level level = new Level();
    level.addElement(player);
    Vector expectedPosition = new Vector(0, 0);

    warp.modify(level, .5);

    assertEquals(expectedPosition, player.getPosition());
  }

}
