package nl.tudelft.scrumbledore.game;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.game.SinglePlayerGame;
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

}
