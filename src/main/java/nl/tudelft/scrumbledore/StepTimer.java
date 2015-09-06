package nl.tudelft.scrumbledore;

/**
 * Class responsible for invoking the step method at a given rate on the Game.
 * 
 * @author Jesse Tilro
 *
 */
public class StepTimer {

  private int rate;
  private Game game;

  /**
   * Construct a new StepTimer.
   * 
   * @param rate
   *          The target number of steps to be executed a second.
   * @param game
   *          The game whose step should be executed.
   */
  public StepTimer(int rate, Game game) {
    this.rate = rate;
    this.game = game;
  }

  /**
   * Get the number of steps to be executed a second.
   * 
   * @return The step rate.
   */
  public int getRate() {
    return rate;
  }

  /**
   * Set the number of steps to be executed a second.
   * 
   * @param rate
   *          The step rate.
   */
  public void setRate(int rate) {
    this.rate = rate;
  }

  /**
   * Get the game whose step method should be invoked.
   * 
   * @return The game.
   */
  public Game getGame() {
    return game;
  }

  /**
   * Set the game whose step method should be invoked.
   * 
   * @param game
   *          The game.
   */
  public void setGame(Game game) {
    this.game = game;
  }

}
