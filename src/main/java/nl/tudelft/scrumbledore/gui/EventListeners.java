package nl.tudelft.scrumbledore.gui;

import nl.tudelft.scrumbledore.Bubble;
import nl.tudelft.scrumbledore.BubbleAction;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Game;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.Player;
import nl.tudelft.scrumbledore.PlayerAction;
import nl.tudelft.scrumbledore.Vector;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;

import java.util.ArrayList;

import javafx.stage.WindowEvent;
import javafx.scene.input.KeyEvent;

/**
 * Handles all the evenlisteners of the gui.
 * 
 * @author Jeroen Meijer
 *
 */
@SuppressWarnings({ "checkstyle:methodlength", "PMD.TooManyMethods", "PMD.NPathComplexity",
    "PMD.CyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity", "PMD.StdCyclomaticComplexity" })
public class EventListeners {
  private Game game;
  private Stage stage;
  private Scene scene;

  /**
   * Prepares the EventListeners.
   * 
   * @param game
   *          The game to which the EventListeners are added.
   * @param stage
   *          The stage to which the EventListeners are added.
   * @param scene
   *          The scene to which the EventListeners are added.
   */
  public EventListeners(Game game, Stage stage, Scene scene) {
    this.game = game;
    this.stage = stage;
    this.scene = scene;
  }
  
  /**
   * Initializes the EventListeners.
   */
  public void init() {
    addKeyEventListeners();
    addWindowEventListeners();
  }

  /**
   * Add key event listeners to a given scene to allow player controls.
   * 
   * @param scene
   *          The scene the listeners should be added to.
   */
  private void addKeyEventListeners() {
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
          if (!player.isFiring()) {
            Bubble newBubble = new Bubble(bubblePos,
                new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));

            bubbles.add(newBubble);
            if (player.getLastMove() == PlayerAction.MoveLeft) {
              if (Constants.LOGGING_WANTSHOOTING) {
                // Sending the shooting information to the logger.
                Logger.log("Player shot in the western direction.");
              }

              newBubble.addAction(BubbleAction.MoveLeft);
            } else {
              if (Constants.LOGGING_WANTSHOOTING) {
                // Sending the shooting information to the logger.
                Logger.log("Player shot in the eastern direction.");
              }

              newBubble.addAction(BubbleAction.MoveRight);
            }
          }

          player.setFiring(true);
        }

        // Restarting the game if "R" is pressed or when the player is dead.
        if (keyPress.equals("R") || !game.getCurrentLevel().getPlayer().isAlive()) {
          game.restart();
          // renderStatic();
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

        if (keyRelease.equals("Z")) {
          player.setFiring(false);
        }
      }

    });
  }

  /**
   * Add WindowEvent listener to exit the application when the window is closed.
   * 
   * @param stage
   *          The Stage used by the rest of the GUI.
   */
  private void addWindowEventListeners() {
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent event) {

        // Logging the termination of the game.
        Logger.log("--------------------GAME TERMINATED");

        // Quitting the game.
        System.exit(0);
      }
    });

  }
}
