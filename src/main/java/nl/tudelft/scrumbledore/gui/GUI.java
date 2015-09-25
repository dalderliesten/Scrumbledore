package nl.tudelft.scrumbledore.gui;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nl.tudelft.scrumbledore.Bubble;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Fruit;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.NPC;
import nl.tudelft.scrumbledore.NPCAction;
import nl.tudelft.scrumbledore.Platform;
import nl.tudelft.scrumbledore.Player;
import nl.tudelft.scrumbledore.PlayerAction;
import nl.tudelft.scrumbledore.SpriteStore;
import nl.tudelft.scrumbledore.StepTimer;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions that are related to the
 * GUI.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 */
@SuppressWarnings({ "checkstyle:methodlength", "PMD.TooManyMethods", "PMD.NPathComplexity",
    "PMD.CyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity", "PMD.StdCyclomaticComplexity",
    "PMD.TooManyFields" })
public class GUI extends Application {
  private Game game;
  private StepTimer timer;
  private SpriteStore sprites;
  private Stage stage;
  private Scene scene;
  private VBox layout;
  private Group gameView;
  private Canvas staticDisplay;
  private Canvas dynamicDisplay;
  private GraphicsContext staticPainter;
  private GraphicsContext dynamicPainter;
  private AnimationTimer animationTimer = new AnimationTimer() {
    public void handle(long currentNanoTime) {
      checkPlayerAlive();
      advanceLevel();
      renderDynamic();
    }
  };

  private Button startStopButton;
  private Button settingsButton;
  private Button exitButton;
  private Label scoreLabel;
  private Label levelLabel;
  private Label highScoreLabel;
  private Label powerUpLabel;

  /**
   * The start method launches the JavaFX GUI window and handles associated start-up items and the
   * creation of essential features, such as buttons and display information.
   * 
   * @pre Method called and gameStage passed
   * @post GUI created and handlers prepared
   * @param gameStage
   *          The stage required to display the GUI.
   */
  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.sprites = new SpriteStore();

    // Setup the Game logic.
    setupGame();

    // Setup the GUI display.
    setupGUI();

    // Add event listeners.
    EventListeners el = new EventListeners(game, stage, scene);
    el.init();

    addButtonEventListeners();

    renderStatic();

    // Start the animation timer to keep refreshing the dynamic canvas.
    animationTimer.start();

    // Showing the game stage.
    stage.show();
  }

  /**
   * Setup the Game logic.
   */
  private void setupGame() {
    // Instantiate the essential game and step timer functions for the game handling.
    game = new Game();
    timer = new StepTimer(Constants.REFRESH_RATE, game);

    // Starting the step timer.
    timer.start();
  }

  /**
   * Setup the GUI display.
   */
  private void setupGUI() {
    // Setting the title of the GUI window.
    stage.setTitle("Scrumbledore");

    // Setting window dimension and movement properties.
    stage.setHeight(Constants.GUIY);
    stage.setWidth(Constants.GUIX);
    stage.setResizable(false);

    setupGUIGameView();
    setupGUILayout();
    setupGUIScene();
  }

  /**
   * Setup the Game View GUI group.
   */
  private void setupGUIGameView() {
    // Setup the static canvas and its painter
    staticDisplay = new Canvas(Constants.LEVELX, Constants.LEVELY);
    staticPainter = staticDisplay.getGraphicsContext2D();

    // Setup the dynamic canvas and its painter
    dynamicDisplay = new Canvas(Constants.LEVELX, Constants.LEVELY);
    dynamicPainter = dynamicDisplay.getGraphicsContext2D();

    gameView = new Group();

    gameView.getChildren().add(staticDisplay);
    gameView.getChildren().add(dynamicDisplay);
  }

  /**
   * Setup the GUI layout. Uses the Game View GUI Group.
   */
  private void setupGUILayout() {
    // Setting the content handler group object, to which objects within the game must be added.
    layout = new VBox();

    // Creation of a horizontal box for storing top labels and items to display, and making it the
    // full width of the GUI.
    HBox topItems = new HBox(15);
    topItems.maxWidth(Constants.GUIX);

    // Linking the labels needed in the top HBox to their constant referneces.
    Label scoreTitleLabel = new Label(Constants.SCORELABEL);
    scoreLabel = new Label(game.getScore());
    Label highScoreTitleLabel = new Label(Constants.HISCORELABEL);
    highScoreLabel = new Label(game.getHighScore());
    Label powerUpTitleLabel = new Label(Constants.POWERUPLABEL);
    powerUpLabel = new Label("NONE ACTIVE");
    Label levelTitleLabel = new Label(Constants.LEVELLABEL);
    levelLabel = new Label(game.getCurrentLevelNumber());

    // Adding the top labels to the top HBox and to the game display interface.
    topItems.getChildren().addAll(scoreTitleLabel, scoreLabel, powerUpTitleLabel, powerUpLabel,
        levelTitleLabel, levelLabel, highScoreTitleLabel, highScoreLabel);
    layout.getChildren().add(topItems);

    // Displaying the parsed level content in the center of the user interface.
    layout.getChildren().add(gameView);

    // Creation of a horiztonal box for storing bottom buttons and items to display.
    HBox bottomItems = new HBox();

    // Linking the buttons needed to their associated constants namesake.
    startStopButton = new Button(Constants.STOPBTNLABEL);
    settingsButton = new Button(Constants.SETTINGSBTNLABEL);
    exitButton = new Button(Constants.EXITBTNLABEL);

    // Adding the buttons to the bottom Hbox and to the game display interface.
    bottomItems.getChildren().addAll(startStopButton, settingsButton, exitButton);
    layout.getChildren().add(bottomItems);
  }

  /**
   * Setup the main GUI scene. Uses the GUI layout.
   */
  private void setupGUIScene() {
    // Creating of the scene and assigning this scene to the game stage.
    scene = new Scene(layout);
    stage.setScene(scene);

    // Adding the desired stylesheet to the scene for visual modifications.
    scene.getStylesheets().add(Constants.CSS_LOCATION);
  }

  /**
   * Render the static elements of the current level.
   */
  private void renderStatic() {
    // Clear canvas
    staticPainter.clearRect(0, 0, Constants.GUIX, Constants.GUIY);

    // Render the static canvas
    renderPlatforms(staticPainter);
  }

  /**
   * Refresh the GUI by rendering all dynamic elements in the current level of the game Also updates
   * all the GUI elements that must be refreshed per cycle.
   */
  private void renderDynamic() {
    // Clear canvas
    dynamicPainter.clearRect(0, 0, Constants.GUIX, Constants.GUIY);

    // Render Bubbles.
    renderBubbles(dynamicPainter);

    // Render Fruits.
    renderFruits(dynamicPainter);

    // Render other moving elements.
    renderNPCs(dynamicPainter);

    // Render Player.
    renderPlayer(dynamicPainter);

    // Updating the score display for the GUI.
    scoreLabel.setText(game.getScore());

    // Updating the current level number display for the GUI.
    levelLabel.setText(game.getCurrentLevelNumber());

    // Updating the current high score display for the GUI.
    highScoreLabel.setText(game.getHighScore());
  }

  /**
   * Render the player of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderPlayer(GraphicsContext painter) {
    Player player = game.getCurrentLevel().getPlayer();

    boolean toRight = player.getLastMove() == PlayerAction.MoveRight;
    boolean isFiring = player.isFiring();

    String spr = "player-left";
    if (isFiring && toRight) {
      spr = "player-shoot-right";
    } else if (isFiring) {
      spr = "player-shoot-left";
    } else if (toRight) {
      spr = "player-right";
    }

    String path = sprites.get(spr).getPath();
    painter.drawImage(new Image(path), game.getCurrentLevel().getPlayer().getPosition().getX(),
        game.getCurrentLevel().getPlayer().getPosition().getY());
  }

  /**
   * Render the bubbles of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderBubbles(GraphicsContext painter) {
    // Copy bubbles to prevent a race condition when many bubbles are shot rapidly
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    for (Bubble bubble : game.getCurrentLevel().getBubbles()) {
      bubbles.add(bubble);
    }

    for (Bubble currentBubble : bubbles) {
      painter.drawImage(new Image(sprites.get("bubble").getPath()), currentBubble.getPosition()
          .getX(), currentBubble.getPosition().getY());
    }
  }

  /**
   * Render the NPCs of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderNPCs(GraphicsContext painter) {
    ArrayList<NPC> npcs = new ArrayList<NPC>();

    // Copy bubbles to prevent a race condition when many bubbles are shot rapidly
    for (NPC npc : game.getCurrentLevel().getNPCs()) {
      npcs.add(npc);
    }

    // Adding the initial enemy locations to the GUI.
    for (NPC current : npcs) {
      String spr = "enemy-mighta-right";
      if (current.getLastMove().equals(NPCAction.MoveLeft)) {
        spr = "enemy-mighta-left";
      }
      painter.drawImage(new Image(sprites.get(spr).getPath()), current.getPosition().getX(),
          current.getPosition().getY());
    }
  }

  /**
   * Render the platforms of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderPlatforms(GraphicsContext painter) {
    // Placing the platform elements within the level.
    for (Platform current : game.getCurrentLevel().getPlatforms()) {
      // Painting the current platform image at the desired x and y location given by the vector.
      painter.drawImage(new Image(sprites.get("wall-1").getPath()), current.getPosition().getX(),
          current.getPosition().getY());
    }
  }

  /**
   * Render the fruit items of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The painter used to display the graphics content.
   */
  private void renderFruits(GraphicsContext painter) {
    ArrayList<Fruit> fruits = new ArrayList<Fruit>();

    for (Fruit fruit : game.getCurrentLevel().getFruits()) {
      fruits.add(fruit);
    }

    for (Fruit current : fruits) {
      painter.drawImage(new Image(sprites.get("fruit-banana").getPath()), current.getPosition()
          .getX(), current.getPosition().getY());
    }
  }

  /**
   * Advances the level to the next one, and displays a special dialog box upon completion of all
   * the levels without dying.
   */
  private void advanceLevel() {

    // When the enemies in the current level have been killed.
    if (game.getCurrentLevel().getNPCs().isEmpty()) {

      // If there are no levels left in the game, show a message.
      if (game.remainingLevels() == 0) {
        // Log the completion of the game.
        Logger.log("Player completed the game successfully.");

        // Creating of the dialog pop-up stage.
        Stage gameWinStage = new Stage();

        // Setting the parent of the dialog window.
        gameWinStage.initModality(Modality.APPLICATION_MODAL);
        gameWinStage.initOwner(stage);

        // Creation of a vertical box to display the label, and creation of the label.
        VBox gameWinVBox = new VBox(20);
        Label gameWinLabel = new Label(Constants.GAMEWIN_DIALOG);
        gameWinVBox.getChildren().add(gameWinLabel);

        // Creation of a scene to display the virtual box and associated label.
        Scene gameWinScene = new Scene(gameWinVBox, 300, 200);

        // Showing the dialog box.
        gameWinStage.setScene(gameWinScene);
        gameWinStage.show();

        // Halting the animation timer.
        animationTimer.stop();
      } else {
        // Logging the advancement in level.
        Logger.log("Player advanced to the next level.");

        // Go to the next level and then re-render it.
        game.goToNextLevel();
        renderStatic();
      }
    }
  }

  /**
   * If player died, restart the game.
   */
  private void checkPlayerAlive() {
    if (!game.getCurrentLevel().getPlayer().isAlive()) {
      game.restart();
      renderStatic();
    }
  }

  /**
   * Add event listeners to buttons.
   */
  private void addButtonEventListeners() {
    // Mapping the function of the start/stop button to start/stop the game when the button is
    // pressed.
    startStopButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        // If the game is paused, it will resume it and change the button label to stop. Otherwise,
        // it resumes the game and changes the butotn label to start.
        if (timer.isPaused()) {
          if (Constants.LOGGING_WANTSTARTSTOP) {
            // Logging that the game has been restarted.
            Logger.log("--------------------GAME HAS BEEN RESTARTED AFTER A PAUSE");
          }

          timer.resume();
          startStopButton.setText(Constants.STOPBTNLABEL);
        } else {
          if (Constants.LOGGING_WANTSTARTSTOP) {
            // Writing to the game log that the game has been paused.
            Logger.log("--------------------GAME HAS BEEN PAUSED");
          }

          timer.pause();
          startStopButton.setText(Constants.STARTBTNLABEL);
        }
      }

    });

    // Mapping the function of the settings button to the settings handling of the GUI.
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent event) {
        // Checking if the game is paused, and if not, it gets paused.
        if (!timer.isPaused()) {
          timer.pause();
        }

        // Changing the start-stop button text to correctly display the start text after entering
        // the settings menu.
        startStopButton.setText(Constants.STARTBTNLABEL);

        // Handling the creation and running of the settings menu.
        settingsMenu();

        if (Constants.LOGGING_WANTSTARTSTOP) {
          // Logging the entering of the settings menu and subsequent pausing of the game.
          Logger.log("--------------------SETTINGS MENU OPENED");

          // Writing to the game log that the game has been paused.
          Logger.log("--------------------GAME HAS BEEN PAUSED");
        }

      }

    });

    // Mapping the exit function to the exit button to quit when button is pressed.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        // Logging the termination of the game.
        Logger.log("--------------------GAME TERMINATED");

        System.exit(0);
      }

    });
  }

  /**
   * Handles the creation and feature functioning of the settings menu.
   */
  @SuppressWarnings("PMD.ExcessiveMethodLength")
  private void settingsMenu() {
    // Creation and formatting of the settings stage.
    final Stage settingsStage = new Stage();
    settingsStage.initStyle(StageStyle.UTILITY);

    // Styling the settings window height and width.
    settingsStage.setWidth(Constants.GUIX);
    settingsStage.setHeight(Constants.GUIY);
    settingsStage.setResizable(false);

    // Creation of the vertical box for settings display in a vertical manner.
    VBox settingsBox = new VBox(15);

    // Adding content of the settings menu to the VBox.
    Label settingsHeader = new Label(Constants.SETTINGSBTNLABEL);
    Label playerMoveLog = new Label(Constants.LOGGING_PLAYER_MOVEMENT);
    Label playerJumpLog = new Label(Constants.LOGGING_PLAYER_INPUT);
    Label playerShootingLog = new Label(Constants.LOGGING_SHOOTING);
    Label gameStateLog = new Label(Constants.LOGGING_GAME_STARTSTOP);

    // Adding the exit button to go back to the game menu.
    Button exitButton = new Button(Constants.SETTINGSCLOSE);

    // Performing the handling of the settings exit button.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        if (Constants.LOGGING_WANTSTARTSTOP) {
          // Logging the closing of the settings menu.
          Logger.log("--------------------SETTINGS MENU CLOSED");
        }

        // Closing the settings GUI.
        settingsStage.close();
      }

    });

    // Creation of the buttons and handler for the player movement groups. Includes the creation of
    // all grouping and positioning elements.
    final ToggleGroup playerMovementGroup = new ToggleGroup();
    HBox movementToggleBox = new HBox(15);
    final RadioButton movementLogTrue = new RadioButton(Constants.LOGGING_ACTIVE);
    movementLogTrue.setToggleGroup(playerMovementGroup);
    final RadioButton movementLogFalse = new RadioButton(Constants.LOGGING_DISABLED);
    movementLogFalse.setToggleGroup(playerMovementGroup);
    movementToggleBox.getChildren().addAll(movementLogTrue, movementLogFalse);

    // Arming initial buttons.
    if (Constants.LOGGING_WANTMOVEMENT) {
      movementLogTrue.setSelected(true);
    } else {
      movementLogFalse.setSelected(true);
    }

    // Implementing the listener for the radio buttons above.
    playerMovementGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

      public void changed(ObservableValue<? extends Toggle> original, Toggle oldToggle,
          Toggle newToggle) {
        if (newToggle == movementLogTrue) {
          Constants.LOGGING_WANTMOVEMENT = true;
        } else if (newToggle == movementLogFalse) {
          Constants.LOGGING_WANTMOVEMENT = false;
        }
      }

    });

    // Creation of the buttons and handler for the player input groups. Includes the creation of
    // all grouping and positioning elements.
    final ToggleGroup playerInputGroup = new ToggleGroup();
    HBox inputToggleBox = new HBox(15);
    final RadioButton inputLogTrue = new RadioButton(Constants.LOGGING_ACTIVE);
    inputLogTrue.setToggleGroup(playerInputGroup);
    final RadioButton inputLogFalse = new RadioButton(Constants.LOGGING_DISABLED);
    inputLogFalse.setToggleGroup(playerInputGroup);
    inputToggleBox.getChildren().addAll(inputLogTrue, inputLogFalse);

    // Arming initial buttons.
    if (Constants.LOGGING_WANTINPUT) {
      inputLogTrue.setSelected(true);
    } else {
      inputLogFalse.setSelected(true);
    }

    // Implementing the listener for the radio buttons above.
    playerInputGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

      public void changed(ObservableValue<? extends Toggle> original, Toggle oldToggle,
          Toggle newToggle) {
        if (newToggle == inputLogTrue) {
          Constants.LOGGING_WANTINPUT = true;
        } else if (newToggle == inputLogFalse) {
          Constants.LOGGING_WANTINPUT = false;
        }
      }

    });

    // Creation of the buttons and handler for the player shooting groups. Includes the creation of
    // all grouping and positioning elements.
    final ToggleGroup playerShootGroup = new ToggleGroup();
    HBox shootToggleBox = new HBox(15);
    final RadioButton shootLogTrue = new RadioButton(Constants.LOGGING_ACTIVE);
    shootLogTrue.setToggleGroup(playerShootGroup);
    final RadioButton shootLogFalse = new RadioButton(Constants.LOGGING_DISABLED);
    shootLogFalse.setToggleGroup(playerShootGroup);
    shootToggleBox.getChildren().addAll(shootLogTrue, shootLogFalse);

    if (Constants.LOGGING_WANTSHOOTING) {
      shootLogTrue.setSelected(true);
    } else {
      shootLogFalse.setSelected(true);
    }

    // Implementing the listener for the radio buttons above.
    playerShootGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

      public void changed(ObservableValue<? extends Toggle> original, Toggle oldToggle,
          Toggle newToggle) {
        if (newToggle == shootLogTrue) {
          Constants.LOGGING_WANTSHOOTING = true;
        } else if (newToggle == shootLogFalse) {
          Constants.LOGGING_WANTSHOOTING = false;
        }
      }

    });

    // Creation of the buttons and handler for the game tracking groups. Includes the creation of
    // all grouping and positioning elements.
    final ToggleGroup gameLogGroup = new ToggleGroup();
    HBox gameLogBox = new HBox(15);
    final RadioButton gameLogTrue = new RadioButton(Constants.LOGGING_ACTIVE);
    gameLogTrue.setToggleGroup(gameLogGroup);
    final RadioButton gameLogFalse = new RadioButton(Constants.LOGGING_DISABLED);
    gameLogFalse.setToggleGroup(gameLogGroup);
    gameLogBox.getChildren().addAll(gameLogTrue, gameLogFalse);

    if (Constants.LOGGING_WANTSTARTSTOP) {
      gameLogTrue.setSelected(true);
    } else {
      gameLogFalse.setSelected(true);
    }

    // Implementing the listener for the radio buttons above.
    gameLogGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

      public void changed(ObservableValue<? extends Toggle> original, Toggle oldToggle,
          Toggle newToggle) {
        if (newToggle == gameLogTrue) {
          Constants.LOGGING_WANTSTARTSTOP = true;
        } else if (newToggle == gameLogFalse) {
          Constants.LOGGING_WANTSTARTSTOP = false;
        }
      }

    });

    // Adding all the content for the settings menu to the settings scene.
    settingsBox.getChildren().addAll(settingsHeader, playerMoveLog, movementToggleBox,
        playerJumpLog, inputToggleBox, playerShootingLog, shootToggleBox, gameStateLog, gameLogBox,
        exitButton);

    // Creation of the scene and adding it to the settings stage.
    Scene settingsScene = new Scene(settingsBox);
    settingsStage.setScene(settingsScene);

    // Actual display of the settings stage and associated styling.
    settingsScene.getStylesheets().add(Constants.CSS_LOCATION);
    settingsStage.show();
  }
}
