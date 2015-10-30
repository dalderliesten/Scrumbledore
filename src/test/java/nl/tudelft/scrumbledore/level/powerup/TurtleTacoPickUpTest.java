package nl.tudelft.scrumbledore.level.powerup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElement;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Tests the TurtleTacoPickUp class which makes use of the LevelElementTest class.
 * 
 * @author Floris Doolaard
 *
 */
public class TurtleTacoPickUpTest extends PowerupPickUpTest {

  private TurtleTacoPickUp tT1;
  private Vector position;
  private Vector size;

  /**
   * Initializes the TurtleTacoPickUp object before testing.
   */
  @Before
  public void setUp() {
    position = new Vector(0, 0);
    size = new Vector(0, 0);
    tT1 = new TurtleTacoPickUp(position, size);
  }

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new TurtleTacoPickUp(position, size);
  }

  /**
   * Test the getSprites method to verify whether the correct sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    ArrayList<Sprite> sprites = tT1.getSprites(1);

    assertEquals(1, sprites.size());
    assertEquals("powerup-turtle-taco", sprites.get(0).getID());
    assertEquals(
        "images" + File.separator + "sprites" + File.separator + "powerup-turtle-taco.png", sprites
            .get(0).getPath());
  }

  /**
   * Tests if the actions of TurtleTacoPickUp are actually non-exsistent.
   */
  @Test
  public void testActions() {
    tT1.addAction(LevelElementAction.MoveLeft);
    assertFalse(tT1.hasAction(LevelElementAction.MoveLeft));
    tT1.removeAction(LevelElementAction.MoveLeft);
    assertFalse(tT1.hasAction(LevelElementAction.MoveLeft));

    assertEquals(null, tT1.getActions());
  }

  /**
   * Tests whether TurtleTacoPickUp has really not moves.
   */
  @Test
  public void testMoves() {
    tT1.setLastMove(LevelElementAction.MoveLeft);
    assertEquals(null, tT1.getLastMove());
  }

}
