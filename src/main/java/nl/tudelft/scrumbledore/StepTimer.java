package nl.tudelft.scrumbledore;

/**
 * Class responsible for invoking the step method at a given rate on the Game.
 * 
 * @author Jesse Tilro
 * @author Jeroen Meijer
 * @author David Alderliesten
 */
public class StepTimer {

  private int rate;
  private Game game;
  private boolean running;
  private boolean paused;
  private long prevLoopTime;
  private long optimalTime;

  /**
   * Construct a new StepTimer.
   * 
   * @param rate
   *          The target number of steps to be executed a second.
   * @param game
   *          The game whose step should be executed.
   */
  public StepTimer(int rate, Game game) {
    this.game = game;
    this.prevLoopTime = System.nanoTime();
    this.optimalTime = 1000000000 / rate;
    this.rate = rate;
    this.running = false;
    this.paused = false;
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
    this.optimalTime = 1000000000 / rate;
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

  /**
   * Starts the game thread.
   */
  public void start() {
    assert !running;
    running = true;
    Thread loop = new Thread() {
      public void run() {
        loopRunner();
      }
    };
    loop.start();
  }

  /**
   * Stops the game thread.
   */
  public void stop() {
    running = false;
  }

  /**
   * Resumes the game.
   */
  public void resume() {
    paused = false;
  }

  /**
   * Pauses the game.
   */
  public void pause() {
    paused = true;
  }

  /**
   * Checks if the game is paused.
   * 
   * @return boolean true if paused
   */
  public boolean isPaused() {
    return paused;
  }

  /**
   * Keeps looping over the game loop as long as the game is running.
   */
  public void loopRunner() {
    while (running) {
      gameLoop();
    }
  }

  /**
   * The game loop runs the loop within the thread for the game.
   */
  public void gameLoop() {
    // Timing related operations.
    long now = System.nanoTime();
    long elapsedTime = now - prevLoopTime;
    prevLoopTime = now;
    double d = elapsedTime / ((double) optimalTime);

    if (!paused) {
      game.step(d);
    }
    try {
      long timeout = (prevLoopTime - System.nanoTime() + optimalTime) / 1000000;
      if (timeout < 0) {
        timeout = 0;
      }
      Thread.sleep(timeout);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }

  }
}
