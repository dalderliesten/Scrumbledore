package nl.tudelft.scrumbledore.sprite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Suite for the Sprite Store class.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
public class SpriteStoreTest {

  private static final String DIR = "src/test/resources/sprites/";
  
  /**
   * Test the file system reading functionality that is performed when the SpriteStore is
   * instantiated.
   */
  @Test
  public void testRead() {
    // Actual fixtures, execution and assertion.
    SpriteStore test = new SpriteStore(DIR);
    assertEquals(1, test.getAll().size());
    assertTrue(test.getAll().get(0).getID().equals("test"));

    // Additionally test constructor with zero parameters, just for the line coverage.
    SpriteStore additionalTest = SpriteStore.getInstance();
    assertFalse(additionalTest.getAll().isEmpty());
  }

  /**
   * When calling the get method provided with a valid sprite ID, a Sprite instance with the
   * corresponding ID should be returned.
   */
  @Test
  public void testGet() {
    SpriteStore test = new SpriteStore(DIR);
    assertEquals("test", test.get("test").getID());
  }

  /**
   * When there is no Sprite in the Sprite Store with the given ID, the get method should return
   * null.
   */
  @Test
  public void testGetNull() {
    SpriteStore test = new SpriteStore(DIR);
    assertEquals(null, test.get("non_existant_sprite"));
  }

  /**
   * When there is no AnimatedSprite in the Sprite Store with the given ID, the get method should
   * return null.
   */
  @Test
  public void testGetAnimatedNull() {
    SpriteStore test = new SpriteStore(DIR);
    assertEquals(null, test.getAnimated("non_existant_sprite"));
  }

}
