package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a SinglePlayerGame.
 * 
 * @author Niels Warnars
 */
public class SinglePlayerGameFactory extends GameFactory {

  /**
   * Creates a new instance of a SinglePlayerGame.
   * 
   * @return a new instance of a SinglePlayerGame.
   */
  Game createGame() {
    return new SinglePlayerGame();
  }

}
