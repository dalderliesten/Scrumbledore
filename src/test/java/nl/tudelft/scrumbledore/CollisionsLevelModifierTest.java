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
   
    clm.detectFruitPlatform(level, 1);
    verify(klm).stopVertically(fruit);
    verify(klm).snapTop(fruit, platform);
  }

  /**
   * Test the collision between a platform and an NPC colliding from the top.
   */
  @Test
  public void testDetectNPCPlatformFromTop() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    npc.getSpeed().setY(4);
    
    Level level = new Level();
    level.addElement(platform);
    level.addElement(npc);
    
    clm.detectNPCPlatform(level, 1);   
    verify(klm).stopVertically(npc);
    verify(klm).snapTop(npc, platform);
  }
  
  /**
   * Test the collision between a platform and an NPC colliding from the left.
   */
  @Test
  public void testDetectNPCPlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    npc.getSpeed().setX(4);
    
    Level level = new Level();
    level.addElement(platform);
    level.addElement(npc);
    
    clm.detectNPCPlatform(level, 1);   
    verify(klm).stopHorizontally(npc);
    verify(klm).snapLeft(npc, platform);
    assertTrue(npc.hasAction(NPCAction.MoveLeft));
  }
  
  /**
   * Test the collision between a platform and an NPC colliding from the right.
   */
  @Test
  public void testDetectNPCPlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(33, 0), new Vector(32, 32));
    npc.getSpeed().setX(-4);
    
    Level level = new Level();
    level.addElement(platform);
    level.addElement(npc);
    
    clm.detectNPCPlatform(level, 1);   
    verify(klm).stopHorizontally(npc);
    verify(klm).snapRight(npc, platform);
    assertTrue(npc.hasAction(NPCAction.MoveRight));
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

    clm.detectPlayerPlatform(level, 1);   
    verify(klm).stopVertically(player);
    verify(klm).snapTop(player, platform);
  }
  
  /**
   * Test the collision between a platform and a player colliding from the bottom.
   */
  @Test
  public void testDetectPlayerPlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 33), new Vector(32, 32));
    player.getSpeed().setY(-4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1);   
    verify(klm).stopVertically(player);
    verify(klm).snapBottom(player, platform);
  }
  
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

    clm.detectPlayerPlatform(level, 1);   
    verify(klm).stopHorizontally(player);
  }
  
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
    
    clm.detectBubblePlatform(level, 1);
    verify(klm).snapLeft(bubble, platform);
    assertEquals(bubble.hSpeed(), -Constants.BUBBLE_BOUNCE, Constants.DOUBLE_PRECISION);
  }
  
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

    clm.detectPlayerBubble(level, 1);   
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

    clm.detectPlayerBubble(level, 1);   
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

    clm.detectPlayerBubble(level, 1);   
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
    clm.detectBubbleEnemy(level, 1);   
    assertEquals(0, level.getNPCs().size());
  }

  /**
   * Test the collision between a player and a fruit.
   */
  @Test
  public void testDetectPlayerFruit() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 32), new Vector(32, 32));
    fruit.setIsPickable(true);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(fruit);  
    
    clm.detectPlayerFruit(level, 1);   
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
    clm.detectPlayerEnemy(level, 1);   
    assertFalse(player.isAlive());
  }

}
