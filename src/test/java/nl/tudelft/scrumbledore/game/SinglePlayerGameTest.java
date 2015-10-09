package nl.tudelft.scrumbledore.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.Level;

/**
 * Test Suite for the SinglePlayerGame class.
 * 
 * @author Jesse Tilro
 *
 */
public class SinglePlayerGameTest extends GameTest {

  @Override
  protected Game make(ArrayList<Level> levels) {
    return new SinglePlayerGame(levels);
  }

  /**
   * The SinglePlayerGame should make levels with only one player.
   */
  @Test
  public void testMakeLevels() {
    Game game = new SinglePlayerGame();
    ArrayList<Level> levels = game.makeLevels();
    assertEquals(1, levels.get(0).getPlayers().size());
  }

}
