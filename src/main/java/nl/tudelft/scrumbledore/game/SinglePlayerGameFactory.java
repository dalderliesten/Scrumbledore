package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a Single Player Game.
 * 
 * @author Niels Warnars
 */
public class SinglePlayerGameFactory extends GameFactory {

  
  @Override
  Game createGame() {
    return new SinglePlayerGame();
  }

}
