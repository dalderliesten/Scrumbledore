package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test suite for the scoreCounter class.
 * 
 * @author Niels Warnars
 */
public class ScoreCounterTest {

  /**
   * Test whether the constructor sets a new score to 0.
   */
  @Test
  public void testScoreCounter() {
    ScoreCounter sc = new ScoreCounter();
    
    assertEquals(0, sc.getScore());
  }

  /**
   * Test whether the score stays the same if 0 is added. 
   */
  @Test
  public void testUpdateScoreZero() {
    ScoreCounter sc = new ScoreCounter();
    sc.updateScore(0);
    
    assertEquals(0, sc.getScore());
  }
  
  /**
   * Test whether the score is updated accordingly based on a given number of points. 
   */
  @Test
  public void testUpdateScore() {
    ScoreCounter sc = new ScoreCounter();
    sc.updateScore(42);
    
    assertEquals(42, sc.getScore());
  }

}
