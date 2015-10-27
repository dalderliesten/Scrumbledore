package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test suite for the Platform class.
 * 
 * @author Niels Warnars
 */
public class PlatformTest {

  /**
   * Test the dummy hashcode method of the Platform class and verify that 0 should be returned.
   */
  @Test
  public void testHashCode() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));

    assertEquals(0, platform.hashCode());
  }

  /**
   * Test whether values have correctly been set by the Platform(pos, sz) constructor.
   */
  @Test
  public void testPlatform() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));

    assertEquals(new Vector(0, 0), platform.getPosition());
    assertEquals(new Vector(32, 32), platform.getSize());
    assertFalse(platform.isPassable());
  }

  /**
   * Test the equals method with two identical objects.
   */
  @Test
  public void testEqualsTrue() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Platform platformExpected = new Platform(new Vector(0, 0), new Vector(32, 32));

    assertEquals(platformExpected, platform);
  }

  /**
   * Test the equals method with two different objects.
   */
  @Test
  public void testEqualsFalse() {
    Platform p1 = new Platform(new Vector(0, 0), new Vector(32, 32));
    Platform p2 = new Platform(new Vector(0, 0), new Vector(32, 32));
    p2.setPassable(true);

    assertFalse(p1.equals(p2));
  }

  /**
   * Test the equals method with two different objects.
   */
  @Test
  public void testEqualsOtherObject() {
    Platform platform = new Platform(new Vector(21, 21), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));

    assertFalse(platform.equals(npc));
  }

  /**
   * Test the get and set method of the isPassable attribute.
   */
  @Test
  public void testIsPassable() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    platform.setPassable(true);

    assertTrue(platform.isPassable());
  }

  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));

    ArrayList<Sprite> sprites = platform.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("wall-1", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "wall-1.png", sprites.get(0).getPath());
  }
}
