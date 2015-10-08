package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.SpriteStore;
import nl.tudelft.scrumbledore.StepTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
  private static Label scoreLabel;
  private static Label highScoreLabel;
  private static Label levelLabel;
  private static Label powerUpLabel;

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
    prepareInterfaceTop();
    prepareInterfaceBottom();

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
   * Prepares the ontent at the top of the user interface.
   */
  @SuppressWarnings("PMD.AvoidDuplicateLiterals")
  private static void prepareInterfaceTop() {
    HBox topLabels = new HBox(Constants.GAME_PADDING);
    topLabels.setId("gameviewbar");

    Label scoreQuery = new Label(Constants.GAME_SCORELABEL);
    Label highQuery = new Label(Constants.GAME_HISCORELABEL);
    Label powerUpQuery = new Label(Constants.GAME_POWERUPLABEL);
    Label levelQuery = new Label(Constants.GAME_LEVELLABEL);

    scoreLabel = new Label(currentGame.getScore());
    scoreLabel.setId("gameviewscores");
    highScoreLabel = new Label(currentGame.getHighScore());
    highScoreLabel.setId("gameviewscores");
    powerUpLabel = new Label("NONE");
    powerUpLabel.setId("gameviewscores");
    levelLabel = new Label(currentGame.getCurrentLevelNumber());
    levelLabel.setId("gameviewscores");

    topLabels.getChildren().addAll(scoreQuery, scoreLabel, powerUpQuery, powerUpLabel, highQuery,
        highScoreLabel, levelQuery, levelLabel);
    topLabels.setAlignment(Pos.CENTER);
    currentLayout.setTop(topLabels);
  }

  /**
   * Prepares the content at the bottom of the user interface.
   */
  private static void prepareInterfaceBottom() {
    HBox bottomButtons = new HBox(Constants.GAME_PADDING);
    bottomButtons.setId("gameviewbar");

    Button startStopButton = new Button(Constants.GAME_STARTBUTTON);
    Button settingsButton = new Button(Constants.GAME_SETTINGSBUTTON);
    mapSettingsButton(settingsButton);
    Button exitButton = new Button(Constants.GAME_EXITBUTTON);
    mapExitButton(exitButton);

    bottomButtons.getChildren().addAll(startStopButton, settingsButton, exitButton);
    bottomButtons.setAlignment(Pos.CENTER);
    currentLayout.setBottom(bottomButtons);
  }

  /**
   * Maps the functionality of the settings button to the button itself.
   * 
   * @param passedButton
   *          The button which must recieve the functionality.
   */
  private static void mapSettingsButton(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        Settingsmenu.settingsHandle();
      }

    });
  }
  
  /**
   * Maps the functionality of the exit/quit button to the button itself.
   * 
   * @param passedButton
   *          The button which must recieve the functionality.
   */
  private static void mapExitButton(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        Mainmenu.mainMenuHandle(currentStage);
      }

    });
  }

}
