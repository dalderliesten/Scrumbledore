package nl.tudelft.scrumbledore.game;

/**
 * Creates an instance of a Multi Player Game.
 * 
 * @author Niels Warnars
 */
public class MultiPlayerGameFactory extends GameFactory {

  
  @Override
  Game createGame() {
    return new MultiPlayerGame();
  }

}
