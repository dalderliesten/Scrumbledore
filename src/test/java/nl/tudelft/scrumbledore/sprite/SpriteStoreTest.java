package nl.tudelft.scrumbledore.sprite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Test Suite for the Sprite Store class.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
public class SpriteStoreTest {

  /**
   * Test the file system reading functionality that is performed when the SpriteStore is
   * instantiated.
   */
  @Test
  public void testRead() {
    // Actual fixtures, execution and assertion.
    String dir = "src/test/resources/sprites/";
    SpriteStore test = new SpriteStore(dir);
    assertEquals(1, test.getAll().size());
    assertTrue(test.getAll().get(0).getID().equals("test"));

    // Additionally test constructor with zero parameters, just for the line coverage.
    SpriteStore additionalTest = new SpriteStore();
    assertFalse(additionalTest.getAll().isEmpty());
  }

  /**
   * When calling the get method provided with a valid sprite ID, a Sprite instance with the
   * corresponding ID should be returned.
   */
  @Test
  public void testGet() {
    String dir = "src/test/resources/sprites/";
    SpriteStore test = new SpriteStore(dir);
    assertEquals("test", test.get("test").getID());
  }

  /**
   * When there is no Sprite in the Sprite Store with the given ID, the get method should return
   * null.
   */
  @Test
  public void testGetNull() {
    String dir = "src/test/resources/sprites/";
    SpriteStore test = new SpriteStore(dir);
    assertEquals(null, test.get("non_existant_sprite"));
  }

}
