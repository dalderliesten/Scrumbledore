package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.tudelft.scrumbledore.game.ScoreCounter;

/**
 * Test suite for the scoreCounter class.
 * 
 * @author David Alderliesten
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
   * Test whether the high score of a game is set correctly if the current score is higher than the
   * previous high score.
   */
  @Test
  public void testGetHighScore() {
    ScoreCounter sc = new ScoreCounter();
    assertEquals(0, sc.getHighScore());

    sc.updateScore(42);
    assertEquals(42, sc.getHighScore());
  }

  /**
   * Check whether the string returned by the getScoreString method has the expected format.
   */
  @Test
  public void testGetScoreString() {
    ScoreCounter sc = new ScoreCounter();
    assertEquals("0", sc.getScoreString());

    sc.updateScore(42);
    assertEquals("42", sc.getScoreString());
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

  /**
   * Test whether the score is updated accordingly if it is incremented twice.
   */
  @Test
  public void testUpdateScoreTwice() {
    ScoreCounter sc = new ScoreCounter();

    sc.updateScore(1);
    assertEquals(1, sc.getScore());

    sc.updateScore(1);
    assertEquals(2, sc.getScore());
  }

  /**
   * Test whether the reset maintains a score of zero at zero.
   */
  @Test
  public void testResetOnZero() {
    ScoreCounter sc = new ScoreCounter();

    sc.resetScore();
    assertEquals(0, sc.getScore());
  }

  /**
   * Test whether the score reset works after one incrementation.
   */
  @Test
  public void testResetOnce() {
    ScoreCounter sc = new ScoreCounter();

    sc.updateScore(1);

    sc.resetScore();
    assertEquals(0, sc.getScore());
  }

}
