package nl.tudelft.scrumbledore;


import static org.junit.Assert.assertEquals;
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
   * 
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
  /*@Test
  public void testDetectPlayerPlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 32), new Vector(32, 32));
    player.getSpeed().setY(-4);
    
    Level level = new Level();
    level.addElement(player);
    level.addElement(platform);

    clm.detectPlayerPlatform(level, 1000);   
    verify(klm).stopVertically(player);
    verify(klm).snapBottom(player, platform);
  }
  */
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
  }
*/
  /**
   * 
   */
  @Test
  public void testDetectBubblePlatform() {
  }

  /**
   * 
   */
  @Test
  public void testDetectPlayerBubble() {
  }

  /**
   * 
   */
  @Test
  public void testDetectBubbleEnemy() {
  }

  /**
   * 
   */
  @Test
  public void testDetectPlayerFruit() {
  }

  /**
   * 
   */
  @Test
  public void testDetectPlayerEnemy() {
  }

}
