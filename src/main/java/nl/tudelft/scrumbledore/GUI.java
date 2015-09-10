package nl.tudelft.scrumbledore;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @author Niels Warnars
 */
public class GUI extends Application {
  private Game game;
  private StepTimer timer;
  private Image playerSprite;

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
  public void start(Stage gameStage) {
    // Instantiate the essential game and step timer functions for the game handling.
    game = new Game();
    timer = new StepTimer(Constants.REFRESH_RATE, game);

    // Starting the step timer.
    timer.start();

    // Setting the title of the GUI window.
    gameStage.setTitle("Scrumbledore");

    // Setting window dimension and movement properties.
    gameStage.setHeight(Constants.GUIY);
    gameStage.setWidth(Constants.GUIX);
    // gameStage.setResizable(false);

    // Setting the content handler group object, to which objects within the game must be added.
    BorderPane contentHandler = new BorderPane();

    // Creating of the scene and assigning this scene to the game stage.
    Scene mainScene = new Scene(contentHandler);
    gameStage.setScene(mainScene);

    // Adding the desired stylesheet to the scene for visual modifications.
    mainScene.getStylesheets().add(Constants.CSS_LOCATION);

    // Creation of a horizontal box for storing top labels and items to display.
    HBox topItems = new HBox();

    // Linking the labels needed in the top HBox to their constant referneces.
    Label scoreLabel = new Label(Constants.SCORELABEL);
    Label highScoreLabel = new Label(Constants.HISCORELABEL);
    Label powerUpLabel = new Label(Constants.POWERUPLABEL);
    Label levelLabel = new Label(Constants.LEVELLABEL);

    // Adding the top labels to the top HBox and to the game display interface.
    topItems.getChildren().addAll(scoreLabel, powerUpLabel, levelLabel, highScoreLabel);
    contentHandler.setTop(topItems);

    // Creation of the game display canvas, and adding a graphics context object to allow for
    // simple, call based refreshing. Canvas is then added to the scene of the window.
    Canvas gameDisplay = new Canvas(Constants.GUIX, Constants.GUIY);
    GraphicsContext gamePainter = gameDisplay.getGraphicsContext2D();

    addKeyEventListeners(mainScene);
    
    // Displaying the parsed level content in the center of the user interface.
    contentHandler.setCenter(gameDisplay);

    // Creation of a horiztonal box for storing bottom buttons and items to display.
    HBox bottomItems = new HBox();

    // Linking the buttons needed to their associated constants namesake.
    final Button startStopButton = new Button();
    final Button settingsButton = new Button(Constants.SETTINGSBTNLABEL);
    final Button exitButton = new Button(Constants.EXITBTNLABEL);

    // Checking the state of the game/timer to determine the start/stop button status needed for the
    // text.
    if (timer.isPaused()) {
      startStopButton.setText(Constants.STOPBTNLABEL);
    } else {
      startStopButton.setText(Constants.STARTBTNLABEL);
    }

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

    // Mapping the settings button menu actionevent to trigger when the button is pressed.
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        System.out.println("SETTINGS HOOK ACTIVATED");
      }

    });

    // Mapping the exit function to the exit button to quit when button is pressed.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        System.exit(0);
      }

    });

    // Adding the buttons to the bottom Hbox and to the game display interface.
    bottomItems.getChildren().addAll(startStopButton, settingsButton, exitButton);
    contentHandler.setBottom(bottomItems);

    // Calling the dynamic handling for the GUI.
    spawnDynamic(gameStage, gamePainter);
  }

  /**
   * spawnDynamic takes care of elements which must be updated, such as players and enemies.
   * 
   * @pre method called within the class and valid parameters passed
   * @post spawns and handles dynamic elemens of the game
   * @param passedStage
   *          The stage used by the rest of the GUI
   * @param passedScene
   *          The scene used by the rest of the GUI
   * @param passedPane
   *          The layout pane used by the rest of the GUI
   * 
   */
  private void spawnDynamic(Stage passedStage, final GraphicsContext gamePainter) {
    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        // Make the enemies move simultaneously
        enemyMover();
        // Clear canvas
        gamePainter.clearRect(0, 0, Constants.GUIX, Constants.GUIY);
        // background image clears canvas
        // Adding the initial player location to the GUI.
        if (game.getCurrentLevel().getPlayer().getLastMove() == PlayerAction.MoveRight) {
          playerSprite = new Image(Constants.PLAYER_SPRITE_RIGHT);
        } else {
          playerSprite = new Image(Constants.PLAYER_SPRITE_LEFT);
        }
        gamePainter.drawImage(playerSprite,
            game.getCurrentLevel().getPlayer().getPosition().getX(), game.getCurrentLevel()
                .getPlayer().getPosition().getY());

        // Adding the Bubbles being shot.
        for (Bubble currentBubble : game.getCurrentLevel().getBubbles()) {
          gamePainter.drawImage(new Image(Constants.BUBBLE_SPRITE), currentBubble.getPosition().getX(), 
              currentBubble.getPosition().getY());
        }
        
        // Adding the initial enemy locations to the GUI.
        for (LevelElement current : game.getCurrentLevel().getMovingElements()) {
          gamePainter.drawImage(new Image(Constants.NPC_SPRITE), current.getPosition().getX(), 
              current.getPosition().getY());
        }
        
        // Placing the platform elements within the level.
        for (Platform current : game.getCurrentLevel().getPlatforms()) {
          // Painting the current platform image at the desired x and y location given by the vector.
          gamePainter.drawImage(new Image(Constants.PLATFORM_SPRITE), current.getPosition().getX(),
              current.getPosition().getY());
        }
      }
    }.start();

    passedStage.show();
  }

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
        if (keyPress.equals("A")) {
          Bubble newBubble = new Bubble(bubblePos, new Vector(Constants.BLOCKSIZE,Constants.BLOCKSIZE));
          bubbles.add(newBubble);
          newBubble.addAction(BubbleAction.MoveLeft);
        } else if (keyPress.equals("D")) {
          Bubble newBubble = new Bubble(bubblePos, new Vector(Constants.BLOCKSIZE,Constants.BLOCKSIZE));
          bubbles.add(newBubble);
          newBubble.addAction(BubbleAction.MoveRight);
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
  
  /**
   * Makes NPCs move to the right position.
   */
  private void enemyMover() {
    
    // Loop over all NPCs
    for (LevelElement element: game.getCurrentLevel().getMovingElements()) {
      if (element.getClass().equals(NPC.class)) {
        NPC npc = (NPC) element;
        
        // Assign platforms to NPC if this has not happened yet
        if (npc.getMovementBoundaries() == null) {
          npc.setPlatforms(game.getCurrentLevel().getPlatforms());
        }
        
        // Move into a certain direction if this is indicated by the NPC
        if (npc.getMovementDirection().equals("right")) {
          npc.addAction(NPCAction.MoveRight);
        } else if (npc.getMovementDirection().equals("left")) {
          npc.addAction(NPCAction.MoveLeft);
        }

        Vector currentPosition = npc.getPosition();
        
        // Enemy is at left boundary, make it move to the right
        if (currentPosition.neighbouring(8, npc.getMovementBoundaries()[0])) {
          npc.addAction(NPCAction.MoveStop);
          npc.setMovementDirection("right");
          return;
        }
        
        // Enemy is at right boundary, make it move to the left
        if (currentPosition.neighbouring(8, npc.getMovementBoundaries()[1])) {
          npc.addAction(NPCAction.MoveStop);
          npc.setMovementDirection("left");
          return;
        }
      }
    } 
    

  }
}
