package nl.tudelft.scrumbledore.game;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.Level;

/**
 * Test Suite for the MultiPlayerGameTest.
 * 
 * @author Jesse Tilro
 *
 */
public class MultiPlayerGameTest extends GameTest {

  @Override
  protected Game make(ArrayList<Level> levels) {
    return new MultiPlayerGame(levels);
  }

  /**
   * The MultiPlayerGame should make levels with at least two players.
   */
  @Test
  public void testMakeLevels() {
    Game game = new MultiPlayerGame();
    ArrayList<Level> levels = game.makeLevels();
    assertTrue(levels.get(0).getPlayers().size() > 1);
  }

}
