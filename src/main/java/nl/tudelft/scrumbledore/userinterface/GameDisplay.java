package nl.tudelft.scrumbledore.userinterface;

import java.util.ArrayList;

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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.StepTimer;
import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.game.GameFactory;
import nl.tudelft.scrumbledore.level.Bubble;
import nl.tudelft.scrumbledore.level.Fruit;
import nl.tudelft.scrumbledore.level.NPC;
import nl.tudelft.scrumbledore.level.NPCAction;
import nl.tudelft.scrumbledore.level.Platform;
import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.PlayerAction;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class responsible for displaying, running, updating, and interacting with the game for one or two
 * players.
 * 
 * @author David Alderliesten
 */
@SuppressWarnings({ "PMD.TooManyMethods", "PMD.TooManyFields", "PMD.CyclomaticComplexity" })
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
   * 
   * @param passedStage
   *          The stage used by the game client.
   */
  private GameDisplay() {
  }

  /**
   * Launch a new MultiPlayerGame.
   * 
   * @param passedStage
   *          The stage to draw to.
   */
  public static void launchMultiPlayerGame(Stage passedStage) {
    currentStage = passedStage;
    GameFactory factory = new GameFactory();
    currentGame = factory.makeMultiPlayerGame();

    launchGame();
  }

  /**
   * Launch a new SinglePlayerGame.
   * 
   * @param passedStage
   *          The stage to draw to.
   */
  public static void launchSinglePlayerGame(Stage passedStage) {
    currentStage = passedStage;
    GameFactory factory = new GameFactory();
    currentGame = factory.makeSinglePlayerGame();

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

    EventListeners listeners = new EventListeners(currentGame, currentStage, currentScene);
    listeners.init();

    currentStage.show();
  }

  /**
   * Prepares the game by launching the sprite storage, game instance, and timer instance.
   */
  private static void prepareGame() {
    sprites = new SpriteStore();

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
    levelLabel = new Label(new Integer(currentGame.getCurrentLevelNumber()).toString());
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
   * Checks the status of the level, and determines if the player should advance to the next level.
   */
  private static void levelStatus() {
    if (currentGame.getCurrentLevel().getNPCs().isEmpty()
        && currentGame.getCurrentLevel().getEnemyBubbles().isEmpty()) {
      if (currentGame.remainingLevels() == 0) {
        Logger.getInstance().log("Player completed the game successfully.");

        Stage gameWinStage = new Stage();
        gameWinStage.initModality(Modality.APPLICATION_MODAL);
        gameWinStage.initOwner(currentStage);

        VBox gameWinVBox = new VBox(20);
        Label gameWinLabel = new Label(Constants.GAMEWIN_DIALOG);
        gameWinVBox.getChildren().add(gameWinLabel);

        Scene gameWinScene = new Scene(gameWinVBox, 300, 200);
        gameWinStage.setScene(gameWinScene);
        gameWinStage.show();

        animationTimer.stop();
      } else {
        Logger.getInstance().log("Player advanced to the next level.");

        currentGame.goToNextLevel();
        renderStatic();
      }
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
      staticContext.drawImage(new Image(sprites.get("wall-1").getPath()),
          current.getPosition().getX(), current.getPosition().getY());
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
    levelLabel.setText(new Integer(currentGame.getCurrentLevelNumber()).toString());
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
      if (player.isAlive()) {
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
        dynamicContext.drawImage(new Image(path), player.getPosition().getX(),
            player.getPosition().getY());
      }
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
      dynamicContext.drawImage(new Image(path), currentBubble.getPosition().getX(),
          currentBubble.getPosition().getY());
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
      dynamicContext.drawImage(new Image(path), current.getPosition().getX(),
          current.getPosition().getY());
    }
  }

  private static void renderFruit() {
    ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    for (Fruit fruit : currentGame.getCurrentLevel().getFruits()) {
      fruits.add(fruit);
    }

    for (Fruit current : fruits) {
      String path = sprites.getAnimated("fruit").getFrame(current.posX()).getPath();
      dynamicContext.drawImage(new Image(path), current.getPosition().getX(),
          current.getPosition().getY());
    }
  }

}
