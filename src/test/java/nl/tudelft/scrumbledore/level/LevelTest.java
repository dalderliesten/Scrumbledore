package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.element.Fruit;
import nl.tudelft.scrumbledore.level.element.NPC;
import nl.tudelft.scrumbledore.level.element.Platform;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.projectile.Bubble;
import nl.tudelft.scrumbledore.level.projectile.Bubble;

import org.junit.Test;

/**
 * Test case for the Level class.
 * 
 * @author Niels Warnars
 */
public class LevelTest {
  private final Vector basicVt = new Vector(0, 0);

  /**
   * Test case in which the addElement is tested using only one moving element.
   */
  @Test
  public void testAddElementNPCs() {
    Level level = new Level();
    Fruit fr = new Fruit(basicVt, basicVt);
    NPC npc = new NPC(basicVt, basicVt);

    level.addElement(fr);
    level.addElement(npc);

    ArrayList<NPC> npcs = level.getNPCs();
    assertEquals(npcs.size(), 1);
    assertEquals(npcs.get(0).getClass(), NPC.class);
  }

  /**
   * Test case in which the addElement is tested using only one platform object.
   */
  @Test
  public void testAddElementPlatforms() {
    Level level = new Level();
    Platform p1 = new Platform(basicVt, basicVt);
    Platform p2 = new Platform(basicVt, basicVt);

    level.addElement(p1);
    level.addElement(p2);

    ArrayList<Platform> platforms = level.getPlatforms();
    assertEquals(platforms.size(), 2);
    assertEquals(platforms.get(0).getClass(), Platform.class);
    assertEquals(platforms.get(1).getClass(), Platform.class);
  }

  /**
   * Test case in which the addElement is tested using a player object.
   */
  @Test
  public void testAddElementPlayer() {
    Level level = new Level();
    Player player = new Player(basicVt, basicVt);
    Player testPlayer = new Player(basicVt, basicVt);

    level.addElement(player);
    assertEquals(level.getPlayers().get(0), testPlayer);
  }

  /**
   * Test case in which the addElement is tested using Fruit objects.
   */
  @Test
  public void testAddElementFruits() {
    Level level = new Level();
    Fruit f1 = new Fruit(basicVt, basicVt);
    Fruit f2 = new Fruit(basicVt, basicVt);

    level.addElement(f1);
    level.addElement(f2);

    ArrayList<Fruit> fruits = level.getFruits();
    assertEquals(fruits.size(), 2);
    assertEquals(fruits.get(0).getClass(), Fruit.class);
    assertEquals(fruits.get(1).getClass(), Fruit.class);
  }

  /**
   * Test case in which the addElement is tested using Bubble objects.
   */
  @Test
  public void testAddElementBubbles() {
    Level level = new Level();
    Bubble b1 = new Bubble(basicVt, basicVt);
    Bubble b2 = new Bubble(basicVt, basicVt);

    level.addElement(b1);
    level.addElement(b2);

    ArrayList<Bubble> bubbles = level.getBubbles();
    assertEquals(bubbles.size(), 2);
    assertEquals(bubbles.get(0).getClass(), Bubble.class);
    assertEquals(bubbles.get(1).getClass(), Bubble.class);
  }
}
