package nl.tudelft.scrumbledore.sprite;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * Test Suite for the Sprite class.
 * 
 * @author Jesse Tilro
 *
 */
public class SpriteTest {

  /**
   * Test the ID field getter.
   */
  @Test
  public void testGetID() {
    Sprite test = new Sprite("test");
    assertEquals("test", test.getID());
  }

  /**
   * The getPath method should combine the id, ext and dir fields correctly to form a path to the
   * image file to be used for the sprite.
   */
  @Test
  public void testGetPath() {
    Sprite test = new Sprite("file", "ext", "dir/");
    assertEquals("dir/file.ext", test.getPath());
  }

  /**
   * The getPath should be using the Constants Sprites Directory if dir was not specified in the
   * constructor.
   */
  @Test
  public void testGetPathConstantsDir() {
    Sprite test = new Sprite("file", "ext");
    String expectedPath = Constants.SPRITES_DIR + "file.ext";
    assertEquals(expectedPath, test.getPath());
  }

  /**
   * Test the getDrawPosition method which should return the middle coordinates of a sprite.
   */
  @Test
  public void testGetDrawPosition() {
    Sprite test = new Sprite("file", "ext", "dir/", new Vector(32, 32));
    Vector res = test.getDrawPosition(new Vector(32, 32));
    assertEquals(new Vector(16.0d, 16.0d), res);
  }
}
