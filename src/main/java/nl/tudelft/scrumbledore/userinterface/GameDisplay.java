package nl.tudelft.scrumbledore.userinterface;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.StepTimer;
import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.game.GameFactory;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.PlayerElement;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Class responsible for displaying, running, updating, and interacting with the game for one or two
 * players.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.TooManyFields", "PMD.CyclomaticComplexity" })
public final class GameDisplay {
  private static Stage currentStage;
  private static Scene currentScene;
  private static BorderPane currentLayout;
  private static Group renderGroup;
  private static StepTimer currentTimer;
  private static double endStepsSnapShot;
  private static Game currentGame;
  private static Canvas staticCanvas;
  private static Canvas dynamicCanvas;
  private static GraphicsContext staticContext;
  private static GraphicsContext dynamicContext;
  private static Label scoreLabel;
  private static Label highScoreLabel;
  private static Label levelLabel;
  private static Label powerUpLabel;
  private static String advanceLabel;

  private static AnimationTimer animationTimer = new AnimationTimer() {
    public void handle(long currentNanoTime) {
      playerStatus();
      levelStatus();
      renderDynamic();
    }
  };

  /**
   * Constructor is set to private, as only one instance of the main menu should exist.
   * 
   * @param passedStage
   *          The stage used by the game client.
   */
  private GameDisplay() {
  }

  /**
   * Creates a new game using a given game factory.
   * 
   * @param factory
   *          The game factory used for creating a new game.
   * @param passedStage
   *          The stage to draw to.
   */
  public static void createGame(GameFactory factory, Stage passedStage) {
    currentStage = passedStage;
    currentGame = factory.makeGame();

    launchGame();
  }


  /**
   * Handles the creation of a game and the associated interface.
   */
  private static void launchGame() {
    currentLayout = new BorderPane();

    prepareGame();
    prepareInterfaceTop();
    prepareInterfaceBottom();
    prepareRenderer();
    currentLayout.setCenter(renderGroup);

    renderStatic();
    animationTimer.start();

    currentScene = new Scene(currentLayout);
    currentScene.getStylesheets().add(Constants.CSS_GAMEVIEW);
    currentStage.setScene(currentScene);

    KeyListeners listeners = new KeyListeners(currentGame, currentScene);
    listeners.init();

    removeSpacebarFunctionality();

    currentStage.show();
  }

  /**
   * Prepares the game by launching the sprite storage, game instance, and timer instance.
   */
  private static void prepareGame() {
    currentTimer = new StepTimer(Constants.REFRESH_RATE, currentGame);
    currentTimer.start();
  }

  /**
   * Prepares the content at the top of the user interface.
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
    levelLabel = new Label(Integer.toString(currentGame.getCurrentLevelNumber()));
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

    Button startStopButton = new Button();
    if (!currentTimer.isPaused()) {
      startStopButton.setText(Constants.GAME_STOPBUTTON);
    } else {
      startStopButton.setText(Constants.GAME_STARTBUTTON);
    }
    mapStartStopButton(startStopButton);
    Button settingsButton = new Button(Constants.GAME_SETTINGSBUTTON);
    mapSettingsButton(settingsButton, startStopButton);
    Button exitButton = new Button(Constants.GAME_EXITBUTTON);
    mapExitButton(exitButton);

    bottomButtons.getChildren().addAll(startStopButton, settingsButton, exitButton);
    bottomButtons.setAlignment(Pos.CENTER);
    currentLayout.setBottom(bottomButtons);
  }

  /**
   * Maps the functionality of the start/stop game button to the button itself.
   * 
   * @param passedButton
   *          The button which must recieve the functionality.
   */
  private static void mapStartStopButton(final Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        if (!currentTimer.isPaused()) {
          passedButton.setText(Constants.GAME_STARTBUTTON);
          currentTimer.pause();
        } else {
          passedButton.setText(Constants.GAME_STOPBUTTON);
          currentTimer.resume();
        }
      }

    });
  }

  /**
   * Maps the functionality of the settings button to the button itself.
   * 
   * @param passedButton
   *          The button which must recieve the functionality.
   * @param startStopbutton
   *          The start/stop button whose label must be updated.
   */
  private static void mapSettingsButton(Button passedButton, final Button startStopButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        currentTimer.pause();
        startStopButton.setText(Constants.GAME_STARTBUTTON);
        SettingsMenu.settingsHandle();
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
        MainMenu.mainMenuHandle(currentStage);
      }

    });
  }

  /**
   * Checks the status of the player(s) in terms of life.
   */
  private static void playerStatus() {
    ArrayList<PlayerElement> players = currentGame.getCurrentLevel().getPlayers();
    Boolean playersLeft = false;
    for (PlayerElement player : players) {
      if (player.isAlive()) {
        playersLeft = true;
      }
    }
    if (!playersLeft) {
      currentGame.restart();
      renderStatic();
    }
  }

  /**
   * Checks the status of the level, and determines if the player should advance to the next level.
   * Upon restarting, notifies the player of time to pick up fruit.
   */
  private static void levelStatus() {
    Level currentLevel = currentGame.getCurrentLevel();
    if (currentLevel.getNPCs().isEmpty() && currentLevel.getEnemyBubbles().isEmpty()) {
      if (endStepsSnapShot == 0) {
        advanceLabel = Constants.ADVANCINGLABEL;
        staticContext.setFill(Color.WHITE);
        staticContext.fillText(advanceLabel, (Constants.LEVELX / 2) - 100,
            (Constants.LEVELY / 2) - 130);
        endStepsSnapShot = currentGame.getSteps();
      }

      if (endStepsSnapShot + Constants.REFRESH_RATE * 4 < currentGame.getSteps()) {
        if (currentGame.remainingLevels() == 0) {
          Logger.getInstance().log("Player completed the game successfully.");

          animationTimer.stop();

          winDialog();
        } else {
          Logger.getInstance().log("Player advanced to the next level.");
          currentGame.goToNextLevel();
          GameDisplay.renderStatic();
        }

        endStepsSnapShot = 0;
      }
    }
  }

  /**
   * Displays the player victory dialog and presents the player with a nice message and the option
   * to go back to the main menu.
   */
  private static void winDialog() {
    VBox currentBox = new VBox(Constants.GAME_PADDING);

    HBox splitterBox = new HBox();
    splitterBox.setId("splitter");

    Label headerVictory = new Label(Constants.GAMEWIN_HEADER);
    headerVictory.setId("winHeader");

    Label bodyVictory = new Label(Constants.GAMEWIN_DIALOG);

    Label pointsView = new Label(Constants.GAMEWIN_POINTS
        + currentGame.getScoreCounter().getScore() + Constants.GAMEWIN_HIGHSCORE
        + currentGame.getScoreCounter().getHighScore() + ".");

    Button returnButton = new Button(Constants.GAMEWIN_TOMAINMENU);
    mapExitButton(returnButton);

    currentBox.getChildren().addAll(headerVictory, splitterBox, bodyVictory, pointsView,
        returnButton);
    currentScene = new Scene(currentBox);
    currentScene.getStylesheets().add(Constants.CSS_VICTORY);
    currentStage.setScene(currentScene);
    currentStage.show();
  }

  /**
   * Prepares the game view renderer.
   */
  private static void prepareRenderer() {
    staticCanvas = new Canvas(Constants.LEVELX, Constants.LEVELY);
    staticContext = staticCanvas.getGraphicsContext2D();

    dynamicCanvas = new Canvas(Constants.LEVELX, Constants.LEVELY);
    dynamicContext = dynamicCanvas.getGraphicsContext2D();

    renderGroup = new Group();
    renderGroup.getChildren().addAll(staticCanvas, dynamicCanvas);
  }

  /**
   * Renders the static elements of the level, such as the platforms.
   */
  private static void renderStatic() {
    staticContext.clearRect(0, 0, Constants.GUIX, Constants.GUIY);

    renderLevelElements(currentGame.getCurrentLevel().getStaticElements(), staticContext);
  }

  /**
   * Renders the dynamic elements of the level, such as the player and enemies.
   */
  private static void renderDynamic() {
    dynamicContext.clearRect(0, 0, Constants.GUIX, Constants.GUIY);

    renderLevelElements(currentGame.getCurrentLevel().getDynamicElements(), dynamicContext);

    scoreLabel.setText(currentGame.getScore());
    highScoreLabel.setText(currentGame.getHighScore());
    levelLabel.setText(Integer.toString(currentGame.getCurrentLevelNumber()));
  }

  /**
   * Render a given list of level elements to a given context of the Game Display.
   * 
   * @param elements
   *          The Level Elements to be rendered.
   * @param context
   *          The Graphics Context in which the elements should be drawn.
   */
  private static void renderLevelElements(List<LevelElement> elements, GraphicsContext context) {
    for (LevelElement element : elements) {
      for (Sprite sprite : element.getSprites(currentGame.getSteps())) {
        Vector drawPos = sprite.getDrawPosition(element.getPosition());
        // Because all Sprites are drawn at their center they need an offset to be in the grid.
        drawPos.sum(Vector.scale(new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE), .5));
        context.drawImage(new Image(sprite.getPath()), drawPos.getX(), drawPos.getY());
      }
    }
  }

  /**
   * Removes the ability to use the spacebar to accidentally pause/play/trigger button events in the
   * game.
   */
  private static void removeSpacebarFunctionality() {
    currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      public void handle(KeyEvent t) {
        if (t.getCode() == KeyCode.SPACE) {
          return;
        }
      }
    });
  }
}
