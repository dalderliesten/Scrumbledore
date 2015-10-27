package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a Single Player Game.
 * 
 * @author Niels Warnars
 */
public class SinglePlayerGameFactory extends GameFactory {

  /**
   * Creates a new instance of a Single Player Game.
   * 
   * @return a new instance of a Single Player Game.
   */
  Game createGame() {
    return new SinglePlayerGame();
  }

}
