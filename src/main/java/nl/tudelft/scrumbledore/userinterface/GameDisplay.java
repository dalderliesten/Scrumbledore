package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.SpriteStore;
import nl.tudelft.scrumbledore.StepTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Class responsible for displaying, running, updating, and interacting with the game for one or two
 * players.
 * 
 * @author David Alderliesten
 */
public final class GameDisplay {
  private static Stage currentStage;
  private static Scene currentScene;
  private static BorderPane currentLayout;
  private static StepTimer currentTimer;
  private static Game currentGame;
  private static SpriteStore sprites;

  /**
   * Constructor is set to private, as only one instance of the main menu should exist.
   */
  private GameDisplay() {
  }

  /**
   * Handles the creation of a game and the associated interface.
   * 
   * @param passedStage
   *          The stage used by the game client.
   */
  public static void launchGame(Stage passedStage) {
    currentStage = passedStage;
    currentLayout = new BorderPane();

    prepareGame();
    prepareInterfaceElements();

    currentScene = new Scene(currentLayout);
    currentScene.getStylesheets().add(Constants.CSS_GAMEVIEW);
    passedStage.setScene(currentScene);
    
    passedStage.show();
  }

  /**
   * Prepares the game by launching the sprite storage, game instance, and timer instance.
   */
  private static void prepareGame() {
    sprites = new SpriteStore();
    currentGame = new Game();

    currentTimer = new StepTimer(Constants.REFRESH_RATE, currentGame);
    currentTimer.start();
  }

  /**
   * Prepares the non-refreshing content of the user interface.
   */
  private static void prepareInterfaceElements() {
    HBox topLabels = new HBox(Constants.GAME_PADDING);
    topLabels.setId("gameviewtop");
    
    Label scoreQuery = new Label(Constants.GAME_SCORELABEL);
    Label highQuery = new Label(Constants.GAME_HISCORELABEL);
    Label powerupQuery = new Label(Constants.GAME_POWERUPLABEL);
    Label levelQuery = new Label(Constants.GAME_LEVELLABEL);

    topLabels.getChildren().addAll(scoreQuery, powerupQuery, highQuery, levelQuery);
    currentLayout.setTop(topLabels);
  }

}
