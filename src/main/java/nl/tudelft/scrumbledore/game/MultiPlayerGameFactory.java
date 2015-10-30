package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a MultiPlayerGame.
 * 
 * @author Niels Warnars
 */
public class MultiPlayerGameFactory extends GameFactory {

  /**
   * Creates a new instance of a MultiPlayerGame.
   * 
   * @return a new instance of a MultiPlayerGame.
   */
  Game createGame() {
    return new MultiPlayerGame();
  }

}
