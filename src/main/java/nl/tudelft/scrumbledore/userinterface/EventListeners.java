package nl.tudelft.scrumbledore.userinterface;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * Handles all the evenlisteners of the gui.
 * 
 * @author David Alderliesten
 * @author Jeroen Meijer
 */
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
    keyPressListeners();
    keyReleaseListeners();
  }

  /**
   * Adds key press listeners.
   */
  private void keyPressListeners() {
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

      public void handle(KeyEvent keyPressed) {
        KeyCode keyCode = keyPressed.getCode();
        ArrayList<Player> players = game.getCurrentLevel().getPlayers();
        Boolean playersLeft = false;
        for (int i = 0; i < players.size(); i++) {
          players.get(i).addAction(Constants.KEY_MAPPING.get(i).get(keyCode));

          if (players.get(i).isAlive()) {
            playersLeft = true;
          }
        }

        // Restarting the game if "R" is pressed or when the player is dead.
        if (keyCode == KeyCode.R || !playersLeft) {
          game.restart();
          // renderStatic();
        }
      }
    });
  }

  /**
   * Adds key release listeners.
   */
  private void keyReleaseListeners() {
    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

      public void handle(KeyEvent keyReleased) {
        KeyCode keyCode = keyReleased.getCode();
        ArrayList<Player> players = game.getCurrentLevel().getPlayers();
        for (int i = 0; i < players.size(); i++) {
          players.get(i)
              .addAction(PlayerAction.invertAction(Constants.KEY_MAPPING.get(i).get(keyCode)));
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
        Logger.getInstance().log("--------------------GAME TERMINATED");

        // Quitting the game.
        System.exit(0);
      }
    });
  }
  
}
