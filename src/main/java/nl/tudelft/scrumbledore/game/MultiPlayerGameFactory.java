package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a Multi Player Game.
 * 
 * @author Niels Warnars
 */
public class MultiPlayerGameFactory extends GameFactory {

  /**
   * Creates a new instance of a Multi Player Game.
   * 
   * @return a new instance of a Multi Player Game.
   */
  Game createGame() {
    return new MultiPlayerGame();
  }

}
