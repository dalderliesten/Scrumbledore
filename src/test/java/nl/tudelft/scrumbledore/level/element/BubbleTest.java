package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test Suite for the Bubble class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings({"PMD.TooManyMethods", "PMD.AvoidDuplicateLiterals"})
public class BubbleTest extends LevelElementTest {

  private Bubble test;

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Bubble(position, size);
  }

  /**
   * Set the bubble field to a new Bubble instance to use as a test object.
   */
  @Before
  public void setUp() {
    test = new Bubble(new Vector(0, 0), new Vector(0, 0));
  }

  /**
   * When a new Bubble element is created, it should initially have a horizontal friction and
   * lifetime according to the constants.
   */
  @Test
  public void testConstuctor() {
    assertEquals(Constants.BUBBLE_FRICTION, test.getFriction().getX(), Constants.DOUBLE_PRECISION);
    assertEquals(Constants.BUBBLE_LIFETIME, test.getLifetime(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Decreasing the lifetime with a given delta should actually decrease the lifetime field value.
   */
  @Test
  public void testDecreaseLifetime() {
    double delta = 42;
    test.decreaseLifetime(delta);
    assertEquals(Constants.BUBBLE_LIFETIME - delta, test.getLifetime(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the lastMove field getter/setter.
   */
  @Test
  public void testLastMove() {
    test.setLastMove(LevelElementAction.MoveLeft);
    assertNull(test.getLastMove());
  }
  
  /**
   * Test the getter/setter methods of the lifetime attribute of a Bubble object.
   */
  @Test
  public void testLifetime() {
    test.setLifetime(42d);
    assertEquals(42d, test.getLifetime(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the getter/setter methods of the hasNPC attribute of a Bubble object.
   */
  @Test
  public void testHasNPC() {
    assertFalse(test.hasNPC());
    test.setHasNPC(true);
    assertTrue(test.hasNPC());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    ArrayList<Sprite> sprites = test.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "bubble-green/frame-01.png", sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct  sprite(s) is/are being returned 
   * if a bubble has a lifetime of less than 40 steps, but it still ends up red.
   */
  @Test
  public void testGetSpritesRedBubble() {
    test.setLifetime(7);
    ArrayList<Sprite> sprites = test.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "bubble-red/frame-01.png", sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct  sprite(s) is/are being returned 
   * if a bubble has a lifetime of less than 5 steps.
   */
  @Test
  public void testGetSpritesGreenBurst() {
    test.setLifetime(2);
    ArrayList<Sprite> sprites = test.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "bubble-green-burst/frame-01.png", sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned if a bubble has an NPC.
   */
  @Test
  public void testGetSpritesHasNPC() {
    test.setHasNPC(true);
    ArrayList<Sprite> sprites = test.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "bubble-zenchan-green/frame-01.png", sprites.get(0).getPath());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned if a red bubble has an NPC.
   */
  @Test
  public void testGetSpritesHasNPCRed() {
    test.setHasNPC(true);
    test.setLifetime(50);
    ArrayList<Sprite> sprites = test.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("frame-01", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "bubble-zenchan-red/frame-01.png", sprites.get(0).getPath());
  }
}
