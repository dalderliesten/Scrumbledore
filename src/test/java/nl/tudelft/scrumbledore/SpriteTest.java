package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
