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
 * Tests the ChiliChickenPickUp class which makes use of the LevelElementTest class.
 * @author Floris Doolaard
 *
 */
public class ChiliChickenPickUpTest extends PowerupPickUpTest {
  private ChiliChickenPickUp cP1;
  private Vector position;
  private Vector size;

  /**
   * Initializes the ChiliChickenPickUp object before testing.
   */
  @Before
  public void setUp() {
    position = new Vector(0, 0);
    size = new Vector(0, 0);
    cP1 = new ChiliChickenPickUp(position, size);
  }
  
  @Override
  public LevelElement make(Vector position, Vector size) {
    return new ChiliChickenPickUp(position, size);
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    ArrayList<Sprite> sprites = cP1.getSprites(1);
    System.out.println(sprites.get(0).getPath());
    assertEquals(1, sprites.size());
    assertEquals("powerup-chili-chicken", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "powerup-chili-chicken.png", sprites.get(0).getPath());
  }
  
  /**
   * Tests if the actions of ChiliChickenPickUp are actually non-exsistent.
   */
  @Test
  public void testActions() {
    cP1.addAction(LevelElementAction.MoveLeft);
    assertFalse(cP1.hasAction(LevelElementAction.MoveLeft));
    cP1.removeAction(LevelElementAction.MoveLeft);
    assertFalse(cP1.hasAction(LevelElementAction.MoveLeft));
    
    assertEquals(null, cP1.getActions());
  }
  
  /**
   * Tests whether ChiliChickenPickUp has really not moves.
   */
  @Test
  public void testMoves() {
    cP1.setLastMove(LevelElementAction.MoveLeft);
    assertEquals(null, cP1.getLastMove());
  }

}
