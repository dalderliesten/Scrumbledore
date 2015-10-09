package nl.tudelft.scrumbledore.game;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.LevelParser;

/**
 * A Game that can be played by multiple players.
 * 
 * @author Jesse Tilro
 *
 */
public class MultiPlayerGame extends Game {

  /**
   * Construct a new MultiPlayerGame.
   */
  public MultiPlayerGame() {
    super();
  }

  /**
   * Construct a new MultiPlayerGame with given levels, used for testing purposes.
   * 
   * @param levels
   *          The levels this Game needs to consist of.
   * 
   */
  public MultiPlayerGame(ArrayList<Level> levels) {
    super(levels);
  }

  /**
   * Make new Levels for this Game.
   * 
   * @return A list of Levels.
   */
  protected ArrayList<Level> makeLevels() {
    LevelParser parser = new LevelParser();
    return parser.getLevels();
  }

}
