package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.SpriteStore;
import nl.tudelft.scrumbledore.StepTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class responsible for displaying, running, updating, and interacting with the game for one or two
 * players.
 * 
 * @author David Alderliesten
 */
public class GameDisplay {
  private Stage currentStage;
  private Scene currentScene;
  private StepTimer currentTimer;
  private Game currentGame;
  private SpriteStore sprites;

  /**
   * 
   * @param passedStage
   *          The stage used by the game client.
   */
  public GameDisplay(Stage passedStage) {
    currentStage = passedStage;
    prepareGame();
  }

  /**
   * Prepares the game by launching the sprite storage, game instance, and timer instance.
   */
  private void prepareGame() {
    sprites = new SpriteStore();
    currentGame = new Game();

    currentTimer = new StepTimer(Constants.REFRESH_RATE, currentGame);
    currentTimer.start();
  }

}
