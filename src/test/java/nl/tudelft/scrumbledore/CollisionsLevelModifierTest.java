package nl.tudelft.scrumbledore;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test suite for the CollisionsLevelModifier class.
 * 
 * @author Niels Warnars
 */
public class CollisionsLevelModifierTest {

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
   * 
   */
  @Test
  public void testDetectFruitPlatform() {
  }

  /**
   * 
   */
  @Test
  public void testDetectPlayerPlatform() {
  }

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
