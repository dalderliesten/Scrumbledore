package nl.tudelft.scrumbledore.userinterface;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.tudelft.scrumbledore.keybinding.KeybindingContainer;
import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.PlayerInterface;

/**
 * Handles all the key event listeners of the gui.
 * 
 * @author David Alderliesten
 * @author Jeroen Meijer
 */
public class KeyListeners {
  private Game game;
  private Scene scene;
  private KeybindingContainer keybindings;

  /**
   * Prepares the EventListeners.
   * 
   * @param game
   *          The game to which the KeyEventListeners are added.
   * @param scene
   *          The scene to which the KeyEventListeners are added.
   */
  public KeyListeners(Game game, Scene scene) {
    this.game = game;
    this.scene = scene;
    this.keybindings = KeybindingContainer.getInstance();
  }

  /**
   * Initializes the EventListeners.
   */
  public void init() {
    addKeyEventListeners();
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
        ArrayList<PlayerInterface> players = game.getCurrentLevel().getPlayers();
        for (PlayerInterface player : players) {
          player.addAction(keybindings.getKeybinding(player.getPlayerNumber()).getAction(keyCode));
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
        
        ArrayList<PlayerInterface> players = game.getCurrentLevel().getPlayers();
        for (PlayerInterface player : players) { 
          player.addAction(LevelElementAction.invertAction(
              keybindings.getKeybinding(player.getPlayerNumber()).getAction(keyCode)));
          player.removeAction((keybindings.getKeybinding(
              player.getPlayerNumber()).getAction(keyCode)));
        }
      }
    });
  }
}
