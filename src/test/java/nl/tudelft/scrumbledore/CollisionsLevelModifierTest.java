package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the CollisionsLevelModifier class.
 * 
 * @author Niels Warnars
 */
public class CollisionsLevelModifierTest {
  private KineticsLevelModifier klm;
  private ScoreCounter sc;
  private CollisionsLevelModifier clm;
  
  /**
   * Set up mock objects and an CollisionsLevelModifier instance.
   */
  @Before
  public void setUp() {
    klm = mock(KineticsLevelModifier.class);
    sc = mock(ScoreCounter.class);
    
    clm = new CollisionsLevelModifier(klm, sc);
  }
  
  /**
   * Test the CollisionsLevelModifier constructor and the getter methods.
   */
  @Test
  public void testCollisionsLevelModifier() {
    KineticsLevelModifier klm = new KineticsLevelModifier();
    ScoreCounter sc = new ScoreCounter();
    
    CollisionsLevelModifier clm = new CollisionsLevelModifier(klm, sc);
    assertEquals(klm, clm.getKinetics());
    assertEquals(sc, clm.getScore());
  }

  /**
   * Test the collision between a fruit and a platform.
   */
  @Test
  public void testDetectFruitPlatform() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    fruit.getSpeed().setY(4);
    Level level = new Level();
    level.addElement(fruit);
    level.addElement(platform);
   
    clm.detectFruitPlatform(level, 1000);
    verify(klm).stopVertically(fruit);
    verify(klm).snapTop(fruit, platform);
  }

  /**
   * Test the collision between a platform and a player colliding from the top.
   */
  @Test
  public void testDetectPlayerPlatformFromTop() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    player.getSpeed().setY(4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1000);   
    verify(klm).stopVertically(player);
    verify(klm).snapTop(player, platform);
  }
  
  /**
   * Test the collision between a platform and a player colliding from the bottom.
   */
 /* @Test
  public void testDetectPlayerPlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 33), new Vector(32, 32));
    player.getSpeed().setY(-4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1000);   
    verify(klm).stopVertically(player);
    verify(klm).snapBottom(player, platform);
  }*/
  
  /**
   * Test the collision between a platform and a player colliding from the left.
   */
  @Test
  public void testDetectPlayerPlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    
    player.getSpeed().setX(4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1000);   
    verify(klm).stopHorizontally(player);
  }
  
  /**
   * Test the collision between a platform and a player colliding from the right.
   */
  /*@Test
  public void testDetectPlayerPlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(32, 0), new Vector(32, 32));
    
    player.getSpeed().setX(-4);
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1000);   
    verify(klm).stopHorizontally(player);
  }*/

  /**
   * Test the collision between a platform and a bubble colliding from the bottom.
   */
  /*@Test
  public void testDetectBubblePlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
        
    Level level = new Level();
    level.addElement(bubble);
    level.addElement(platform);
    
    clm.detectBubblePlatform(level, 1000);
    verify(klm).snapBottom(bubble, platform);  
    assertEquals(bubble.vSpeed(), Constants.BUBBLE_BOUNCE, Constants.DOUBLE_PRECISION);

  }*/
  
  /**
   * Test the collision between a platform and a bubble colliding from the left.
   */
  @Test
  public void testDetectBubblePlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
        
    Level level = new Level();
    level.addElement(bubble);
    level.addElement(platform);
    
    clm.detectBubblePlatform(level, 1000);
    verify(klm).snapLeft(bubble, platform);
    assertEquals(bubble.hSpeed(), -Constants.BUBBLE_BOUNCE, Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the collision between a platform and a bubble colliding from the right.
   */
  /*@Test
  public void testDetectBubblePlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(32, 0), new Vector(32, 32));
        
    Level level = new Level();
    level.addElement(bubble);
    level.addElement(platform);
    
    clm.detectBubblePlatform(level, 1000);
    verify(klm).snapRight(bubble, platform);
    assertEquals(bubble.hSpeed(), Constants.BUBBLE_BOUNCE, Constants.DOUBLE_PRECISION);

  }*/
  
  /**
   * Test the collision between a bubble and a player colliding from the top.
   */
  @Test
  public void testDetectPlayerBubbleFromTop() {
    Bubble bubble = new Bubble(new Vector(0, 32), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    player.getSpeed().setY(4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);

    clm.detectPlayerBubble(level, 1000);   
    verify(klm).snapTop(player, bubble);
    assertEquals(-Constants.PLAYER_JUMP, player.vSpeed(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the collision between a bubble and a bubble colliding.
   */
  @Test
  public void testDetectPlayerBubbleTwoSameBubbles() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble2 = new Bubble(new Vector(0, 0), new Vector(32, 32));
    
    bubble.getSpeed().setX(2);
    bubble2.getSpeed().setX(4);

    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);
    level.addElement(bubble2);

    clm.detectPlayerBubble(level, 1000);   
    assertEquals(Constants.BUBBLE_BOUNCE, bubble.hSpeed(), Constants.DOUBLE_PRECISION);
    assertEquals(-Constants.BUBBLE_BOUNCE, bubble2.hSpeed(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a bubble and a bubble colliding.
   */
  @Test
  public void testDetectPlayerBubbleTwoDifferentBubbles() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble2 = new Bubble(new Vector(32, 0), new Vector(32, 32));
    
    bubble.getSpeed().setX(2);
    bubble2.getSpeed().setX(4);

    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);
    level.addElement(bubble2);

    clm.detectPlayerBubble(level, 1000);   
    assertEquals(-Constants.BUBBLE_BOUNCE, bubble.hSpeed(), Constants.DOUBLE_PRECISION);
    assertEquals(Constants.BUBBLE_BOUNCE, bubble2.hSpeed(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test the collision between a bubble and an enemy.
   */
  @Test
  public void testDetectBubbleEnemy() {
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 32), new Vector(32, 32));
  
    Level level = new Level();
    level.addElement(bubble);
    level.addElement(npc);
    
    assertEquals(0, level.getFruits().size());
    clm.detectBubbleEnemy(level, 1000);   
    assertEquals(0, level.getNPCs().size());
    assertEquals(1, level.getFruits().size());

  }

  /**
   * Test the collision between a player and a fruit.
   */
  @Test
  public void testDetectPlayerFruit() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 32), new Vector(32, 32));
  
    Level level = new Level();
    level.addElement(player);
    level.addElement(fruit);  
    
    clm.detectPlayerFruit(level, 1000);   
    assertEquals(0, level.getFruits().size());
    verify(sc).updateScore(100);
  }

  /**
   * Test the collision between a player and an enemy.
   */
  @Test
  public void testDetectPlayerEnemy() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 32), new Vector(32, 32));
  
    Level level = new Level();
    level.addElement(player);
    level.addElement(npc);
    
    assertTrue(player.isAlive());
    clm.detectPlayerEnemy(level, 1000);   
    assertFalse(player.isAlive());
  }

}
