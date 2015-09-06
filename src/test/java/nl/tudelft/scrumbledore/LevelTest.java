package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test case for the Level class.
 * 
 * @author Niels Warnars
 */
public class LevelTest {
  private final Vector basicVt = new Vector(0, 0);

  /**
   * Test case in which the addElement is tested
   * using only one moving element.
   */
  @Test
  public void testAddElementMovingElements() {
    Level level = new Level();
    Fruit fr = new Fruit(basicVt, basicVt);
    NPC npc = new NPC(basicVt, basicVt);
    
    level.addElement(fr);
    level.addElement(npc);
    
    ArrayList<LevelElement> movingElements = level.getMovingElements();

    // Check whether other elements did not get created
    assertNull(level.getPlayer());
    assertEquals(level.getPlatforms().size(), 0);
    
    assertEquals(movingElements.size(), 2);
    assertEquals(movingElements.get(0).getClass(), Fruit.class);
    assertEquals(movingElements.get(1).getClass(), NPC.class);
  }

  
  /**
   * Test case in which the addElement is tested
   * using only one platform object.
   */
  @Test
  public void testAddElementPlatforms() {
    Level level = new Level();
    Platform p1 = new Platform(basicVt, basicVt);
    Platform p2 = new Platform(basicVt, basicVt);
    
    level.addElement(p1);
    level.addElement(p2);
    
    ArrayList<Platform> platforms = level.getPlatforms();

    // Check whether other elements did not get created
    assertNull(level.getPlayer());
    assertEquals(level.getMovingElements().size(), 0);

    assertEquals(platforms.size(), 2);
    assertEquals(platforms.get(0).getClass(), Platform.class);
    assertEquals(platforms.get(1).getClass(), Platform.class);
  }
  

  /**
   * Test case in which the addElement is tested
   * using a player object.
   */
  @Test
  public void testAddElementPlayer() {
    Level level = new Level();
    Player player = new Player(basicVt, basicVt);
    Player testPlayer = new Player(basicVt, basicVt);

    level.addElement(player);

    // Check whether other elements did not get created
    assertEquals(level.getMovingElements().size(), 0);
    assertEquals(level.getPlatforms().size(), 0);

    assertEquals(level.getPlayer(), testPlayer);
  }


  /**
   * Test case in which the addElement is tested
   * using with different objects.
   */
  @Test
  public void testAddElementMixed() {
    Level level = new Level();
    
    Platform platform = new Platform(basicVt, basicVt);
    Player player = new Player(basicVt, basicVt);
    NPC npc = new NPC(basicVt, basicVt);

    level.addElement(platform);
    level.addElement(player);
    level.addElement(npc);
    
    ArrayList<LevelElement> movingElements = level.getMovingElements();
    ArrayList<Platform> platforms = level.getPlatforms();
    Player testPlayer = new Player(basicVt, basicVt);
    
    // Assert Platform
    assertEquals(platforms.size(), 1);
    assertEquals(platforms.get(0).getClass(), Platform.class);
    
    // Assert Player
    assertEquals(level.getPlayer(), testPlayer);

    // Assert Moving Elements
    assertEquals(movingElements.size(), 1);
    assertEquals(movingElements.get(0).getClass(), NPC.class);
  }

}

