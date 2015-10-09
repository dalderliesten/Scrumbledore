package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * A game that can be played by a single player.
 * 
 * @author Jesse Tilro
 *
 */
public class SinglePlayerGame extends Game {

  /**
   * Construct a new SinglePlayerGame.
   */
  public SinglePlayerGame() {
    super();
  }

  /**
   * Construct a new SinglePlayerGame with given levels, used for testing purposes.
   * 
   * @param levels
   *          The levels this Game needs to consist of.
   * 
   */
  public SinglePlayerGame(ArrayList<Level> levels) {
    super(levels);
  }

  /**
   * Turn this Game's Levels into Single Player Game Levels by removing the second Player.
   */
  private void makeLevelsSinglePlayer() {

  }

  /**
   * Make new Levels for this Game.
   * 
   * @return A list of Levels.
   */
  protected ArrayList<Level> makeLevels() {
    LevelParser parser = new LevelParser();
    ArrayList<Level> levels = parser.getLevels();

    for (Level level : levels) {
      if (level.getPlayers().size() > 1) {
        for (int i = 1; i < level.getPlayers().size(); i++) {
          level.getPlayers().remove(i);
        }
      }
    }

    return levels;
  }

}
