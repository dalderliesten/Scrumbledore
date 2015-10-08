package nl.tudelft.scrumbledore.userinterface;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Bubble;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Fruit;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.NPC;
import nl.tudelft.scrumbledore.NPCAction;
import nl.tudelft.scrumbledore.Platform;
import nl.tudelft.scrumbledore.Player;
import nl.tudelft.scrumbledore.PlayerAction;
import nl.tudelft.scrumbledore.SpriteStore;
import nl.tudelft.scrumbledore.StepTimer;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Class responsible for displaying, running, updating, and interacting with the game for one or two
 * players.
 * 
 * @author David Alderliesten
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.TooManyFields" })
public final class GameDisplay {
  private static Stage currentStage;
  private static Scene currentScene;
  private static BorderPane currentLayout;
  private static Group renderGroup;
  private static StepTimer currentTimer;
  private static Game currentGame;
  private static Canvas staticCanvas;
  private static Canvas dynamicCanvas;
  private static GraphicsContext staticContext;
  private static GraphicsContext dynamicContext;
  private static SpriteStore sprites;
  private static Label scoreLabel;
  private static Label highScoreLabel;
  private static Label levelLabel;
  private static Label powerUpLabel;

  private static AnimationTimer animationTimer = new AnimationTimer() {
    public void handle(long currentNanoTime) {
      playerStatus();
      levelStatus();
      renderDynamic();
    }
  };

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
    prepareRenderer();
    currentLayout.setCenter(renderGroup);

    renderStatic();
    animationTimer.start();

    currentScene = new Scene(currentLayout);
    currentScene.getStylesheets().add(Constants.CSS_GAMEVIEW);
    passedStage.setScene(currentScene);

    EventListeners listeners = new EventListeners(currentGame, currentStage, currentScene);
    listeners.init();

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

    Button startStopButton = new Button();
    if (!currentTimer.isPaused()) {
      startStopButton.setText(Constants.GAME_STOPBUTTON);
    } else {
      startStopButton.setText(Constants.GAME_STARTBUTTON);
    }
    mapStartStopButton(startStopButton);
    Button settingsButton = new Button(Constants.GAME_SETTINGSBUTTON);
    mapSettingsButton(settingsButton);
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
          passedButton.setText(Constants.GAME_STOPBUTTON);
          currentTimer.pause();
        } else {
          passedButton.setText(Constants.GAME_STARTBUTTON);
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

  /**
   * Checks the status of the player(s) in terms of life.
   */
  private static void playerStatus() {
    ArrayList<Player> players = currentGame.getCurrentLevel().getPlayers();
    Boolean playersLeft = false;
    for (Player player : players) {
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

    for (Platform current : currentGame.getCurrentLevel().getPlatforms()) {
      staticContext.drawImage(new Image(sprites.get("wall-1").getPath()), current.getPosition()
          .getX(), current.getPosition().getY());
    }
  }

  /**
   * Renders the dynamic elements of the level, such as the player and enemies.
   */
  private static void renderDynamic() {
    dynamicContext.clearRect(0, 0, Constants.GUIX, Constants.GUIY);

    renderPlayer();
    renderBubbles();
    renderNPC();
    renderFruit();

    scoreLabel.setText(currentGame.getScore());
    highScoreLabel.setText(currentGame.getHighScore());
    levelLabel.setText(currentGame.getCurrentLevelNumber());
  }

  /**
   * Renders the player(s) on the map.
   */
  private static void renderPlayer() {
    ArrayList<Player> players = currentGame.getCurrentLevel().getPlayers();
    String color = "";
    String[] colors = { "green", "blue" };
    int index = 0;

    for (Player player : players) {
      if (index < colors.length) {
        color = colors[index++];
      }
      double steps = currentGame.getSteps();
      boolean toRight = player.getLastMove() == PlayerAction.MoveRight;
      boolean isFiring = player.isFiring();
      String spr = "move-left";
      if (isFiring && toRight) {
        spr = "shoot-right";
      } else if (isFiring) {
        spr = "shoot-left";
      } else if (toRight) {
        spr = "move-right";
      }
      if (player.getSpeed().getX() == 0 && !isFiring) {
        steps = 0;
      }

      String path = sprites.getAnimated("player-" + color + "-" + spr).getFrame(steps).getPath();
      dynamicContext.drawImage(new Image(path), player.getPosition().getX(), player.getPosition()
          .getY());
    }
  }

  /**
   * Renders the bubble projectile(s) on the map.
   */
  private static void renderBubbles() {
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    for (Bubble bubble : currentGame.getCurrentLevel().getBubbles()) {
      bubbles.add(bubble);
    }

    for (Bubble currentBubble : bubbles) {
      String path = sprites.getAnimated("bubble").getFrame(currentGame.getSteps()).getPath();

      if (currentBubble.hasNPC()) {
        path = sprites.getAnimated("bubble-zenchan").getFrame(currentGame.getSteps()).getPath();
      }
      dynamicContext.drawImage(new Image(path), currentBubble.getPosition().getX(), currentBubble
          .getPosition().getY());
    }
  }

  /**
   * Renders the non-player characters on the map.
   */
  private static void renderNPC() {
    ArrayList<NPC> npcs = new ArrayList<NPC>();
    double steps = currentGame.getSteps();

    for (NPC npc : currentGame.getCurrentLevel().getNPCs()) {
      npcs.add(npc);
    }

    for (NPC current : npcs) {
      String spr = "zenchan-move-right";
      if (current.getLastMove().equals(NPCAction.MoveLeft)) {
        spr = "zenchan-move-left";
      }
      String path = sprites.getAnimated(spr).getFrame(steps).getPath();
      dynamicContext.drawImage(new Image(path), current.getPosition().getX(), current.getPosition()
          .getY());
    }
  }

  private static void renderFruit() {
    ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    for (Fruit fruit : currentGame.getCurrentLevel().getFruits()) {
      fruits.add(fruit);
    }

    for (Fruit current : fruits) {
      String path = sprites.getAnimated("fruit").getFrame(current.posX()).getPath();
      dynamicContext.drawImage(new Image(path), current.getPosition().getX(), current.getPosition()
          .getY());
    }
  }

}
