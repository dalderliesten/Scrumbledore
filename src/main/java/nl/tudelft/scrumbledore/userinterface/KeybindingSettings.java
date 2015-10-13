package nl.tudelft.scrumbledore.userinterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.keybinding.Keybinding;
import nl.tudelft.scrumbledore.keybinding.KeybindingContainer;
import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * Class responsible for generating the keybinding options and the arming of all graphical elements
 * for these options.
 * 
 * @author Jeroen Meijer
 */
public final class KeybindingSettings {

  private static VBox currentBox;
  private static VBox actionBinders;
  private static int selectedPlayer = 1;

  /**
   * Constructor is set to private, as only one instance of the settings menu should exist at any
   * given time.
   */
  private KeybindingSettings() {

  }

  /**
   * Performs all the actions required to get the settings for logging.
   * 
   * @return A JavaFX VBox containing all the options and associated actions.
   */
  public static VBox fetchKeybindingOptions() {
    currentBox = new VBox(Constants.SETTINGS_PADDING);
    actionBinders = new VBox();
    
    generateOptions();
    
    currentBox.getChildren().add(actionBinders);
    
    return currentBox;
  }

  /**
   * Generates the options needed for the keybindings in the settings menu.
   */
  private static void generateOptions() {
    generatePlayerRadio();
    generateKeybindings();
  }

  /**
   * Generates the player selection radio button.
   */
  private static void generatePlayerRadio() {
    HBox playerBox = new HBox(Constants.SETTINGS_PADDING);
    final ToggleGroup playerGroup = new ToggleGroup();

    final RadioButton player1 = new RadioButton(Constants.SETTINGS_PLAYER1);
    player1.setToggleGroup(playerGroup);
    player1.setSelected(true);

    final RadioButton player2 = new RadioButton(Constants.SETTINGS_PLAYER2);
    player2.setToggleGroup(playerGroup);

    playerGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        int index = 0;
        for (Toggle toggle : playerGroup.getToggles()) {
          index++;
          if (toggle == newButton) {
            selectedPlayer = index;
          }
        }
        generateKeybindings();
      }
    });

    playerBox.getChildren().addAll(player1, player2);
    currentBox.getChildren().addAll(playerBox);
  }

  private static void generateKeybindings() {
    actionBinders.getChildren().clear();
    
    Keybinding binding = KeybindingContainer.getInstance().getKeybinding(selectedPlayer - 1);

    Button moveLeft = generateKeybinder(PlayerAction.MoveLeft,
        binding.getKey(PlayerAction.MoveLeft), "Move left: ");
    Button moveRight = generateKeybinder(PlayerAction.MoveRight,
        binding.getKey(PlayerAction.MoveRight), "Move right: ");
    Button jump = generateKeybinder(PlayerAction.Jump, binding.getKey(PlayerAction.Jump), "Jump: ");
    Button shoot = generateKeybinder(PlayerAction.Shoot, binding.getKey(PlayerAction.Shoot),
        "Shoot: ");
    actionBinders.getChildren().addAll(moveLeft, moveRight, jump, shoot);
  }

  /**
   * 
   * @param action
   *          The PlayerAction to be remapped.
   * @param key
   *          The KeyCode to which the action is bound.
   * @param description
   *          Description of the button.
   * @return actionBinder The Button to be rendered
   */
  private static Button generateKeybinder(PlayerAction action, KeyCode key, String description) {
    Button actionBinder = new Button(description + key.toString());

    actionBinder.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        
      }
    });
    return actionBinder;
  }
}
