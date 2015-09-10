package nl.tudelft.scrumbledore;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions that are related to the
 * GUI.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 */
public class GUI extends Application {
  private Game game;
  private StepTimer timer;
  private Image playerSprite;
  private int playerDirection;

  private Stage stage;
  private Scene scene;
  private BorderPane layout;
  private Group gameView;
  private Canvas staticDisplay;
  private Canvas dynamicDisplay;
  private GraphicsContext staticPainter;
  private GraphicsContext dynamicPainter;

  private Button startStopButton;
  private Button settingsButton;
  private Button exitButton;

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

    // Setup the Game logic.
    setupGame();

    // Setup the GUI display.
    setupGUI();

    // Add event listeners.
    addKeyEventListeners(scene);
    addButtonEventListeners();

    // Render the static canvas
    renderPlatforms(staticPainter);

    // Start the animation timer to keep refreshing the dynamic canvas.
    startAnimationTimer(stage, dynamicPainter);

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
    // gameStage.setResizable(false);

    setupGUIGameView();
    setupGUILayout();
    setupGUIScene();
  }

  /**
   * Setup the Game View GUI group.
   */
  private void setupGUIGameView() {
    // Setup the static canvas and its painter
    staticDisplay = new Canvas(Constants.GUIX, Constants.GUIY);
    staticPainter = staticDisplay.getGraphicsContext2D();

    // Setup the dynamic canvas and its painter
    dynamicDisplay = new Canvas(Constants.GUIX, Constants.GUIY);
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
    layout = new BorderPane();

    // Creation of a horizontal box for storing top labels and items to display.
    HBox topItems = new HBox();

    // Linking the labels needed in the top HBox to their constant referneces.
    Label scoreLabel = new Label(Constants.SCORELABEL);
    Label highScoreLabel = new Label(Constants.HISCORELABEL);
    Label powerUpLabel = new Label(Constants.POWERUPLABEL);
    Label levelLabel = new Label(Constants.LEVELLABEL);

    // Adding the top labels to the top HBox and to the game display interface.
    topItems.getChildren().addAll(scoreLabel, powerUpLabel, levelLabel, highScoreLabel);
    layout.setTop(topItems);

    // Displaying the parsed level content in the center of the user interface.
    layout.setCenter(gameView);

    // Creation of a horiztonal box for storing bottom buttons and items to display.
    HBox bottomItems = new HBox();

    // Linking the buttons needed to their associated constants namesake.
    startStopButton = new Button(Constants.STOPBTNLABEL);
    settingsButton = new Button(Constants.SETTINGSBTNLABEL);
    exitButton = new Button(Constants.EXITBTNLABEL);

    // Adding the buttons to the bottom Hbox and to the game display interface.
    bottomItems.getChildren().addAll(startStopButton, settingsButton, exitButton);
    layout.setBottom(bottomItems);
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
   * Start the animation timer to refresh the GUI.
   * 
   * @param stage
   *          The Stage used by the rest of the GUI
   * @param painter
   *          The GraphicsContext used by the rest of the GUI
   * 
   */
  private void startAnimationTimer(final Stage stage, final GraphicsContext painter) {
    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        refresh(stage, painter);
      }
    }.start();
  }

  /**
   * Refresh the GUI by rendering all elements in the current level of the game.
   */
  private void refresh(Stage stage, final GraphicsContext painter) {
    // Clear canvas
    painter.clearRect(0, 0, Constants.GUIX, Constants.GUIY);
    // Render Player.
    renderPlayer(painter);
    // Render Bubbles.
    renderBubbles(painter);
    // Render Fruits.
    renderFruits(painter);
    // Render other moving elements.
    renderMovingElements(painter);
    
  }

  /**
   * Render the player of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderPlayer(GraphicsContext painter) {
    if (game.getCurrentLevel().getPlayer().getLastMove() == PlayerAction.MoveRight) {
      playerSprite = new Image(Constants.PLAYER_SPRITE_RIGHT);
      playerDirection = 1;
    } else {
      playerSprite = new Image(Constants.PLAYER_SPRITE_LEFT);
      playerDirection = -1;
    }
    painter.drawImage(playerSprite, game.getCurrentLevel().getPlayer().getPosition().getX(), game
        .getCurrentLevel().getPlayer().getPosition().getY());
  }

  /**
   * Render the bubbles of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderBubbles(GraphicsContext painter) {
    // Adding the Bubbles being shot.
    for (Bubble currentBubble : game.getCurrentLevel().getBubbles()) {
      painter.drawImage(new Image(Constants.BUBBLE_SPRITE), currentBubble.getPosition().getX(),
          currentBubble.getPosition().getY());
    }
  }

  /**
   * Render the moving elements of the game using the given GraphicsContext.
   * 
   * @param painter
   *          The GraphicsContext to be used.
   */
  private void renderMovingElements(GraphicsContext painter) {
    // Adding the initial enemy locations to the GUI.
    for (NPC current : game.getCurrentLevel().getNPCs()) {
      painter.drawImage(new Image(Constants.NPC_SPRITE_LEFT), current.getPosition().getX(), current
          .getPosition().getY());
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
      painter.drawImage(new Image(Constants.PLATFORM_SPRITE), current.getPosition().getX(), 
          current.getPosition().getY());
    }
  }
  
  private void renderFruits(GraphicsContext painter) {
    for (Fruit current : game.getCurrentLevel().getFruits()) {
      painter.drawImage(new Image(Constants.FRUIT_SPRITE),  current.getPosition().getX(),
          current.getPosition().getY());
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
          timer.resume();
          startStopButton.setText(Constants.STOPBTNLABEL);
        } else {
          timer.pause();
          startStopButton.setText(Constants.STARTBTNLABEL);
        }
      }

    });

    // Mapping the exit function to the exit button to quit when button is pressed.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        System.exit(0);
      }

    });
  }

  /**
   * Add key event listeners to a given scene to allow player controls.
   * 
   * @param scene
   *          The scene the listeners should be added to.
   */
  private void addKeyEventListeners(Scene scene) {
    // KeyPress Event handlers.
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

      // Handling the key press.
      public void handle(KeyEvent keyPressed) {
        String keyPress = keyPressed.getCode().toString();
        Player player = game.getCurrentLevel().getPlayer();
        Vector bubblePos = player.getPosition().clone();
        ArrayList<Bubble> bubbles = game.getCurrentLevel().getBubbles();

        // Mapping the desired keys to the desired actions.
        if (keyPress.equals("LEFT")) {
          player.addAction(PlayerAction.MoveLeft);
        } else if (keyPress.equals("RIGHT")) {
          player.addAction(PlayerAction.MoveRight);
        } else if (keyPress.equals("UP")) {
          player.addAction(PlayerAction.Jump);
        }

        // Mapping the shooting action keys.
        if (keyPress.equals("Z")) {
          Bubble newBubble = new Bubble(bubblePos, new Vector(Constants.BLOCKSIZE,
              Constants.BLOCKSIZE));
          bubbles.add(newBubble);
          if (playerDirection == -1) {
            newBubble.addAction(BubbleAction.MoveLeft);
          } else {
            newBubble.addAction(BubbleAction.MoveRight);
          }
        }
      }

    });

    // KeyRelease Event handlers.
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

      // Handling the key press.
      public void handle(KeyEvent keyReleased) {
        String keyRelease = keyReleased.getCode().toString();
        Player player = game.getCurrentLevel().getPlayer();

        // Mapping the desired keys to the desired actions.
        if (keyRelease.equals("LEFT")) {
          player.addAction(PlayerAction.MoveStop);
        } else if (keyRelease.equals("RIGHT")) {
          player.addAction(PlayerAction.MoveStop);
        }
      }

    });
  }

}
