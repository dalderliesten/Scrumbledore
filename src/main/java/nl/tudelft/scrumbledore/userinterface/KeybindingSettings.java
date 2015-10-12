package nl.tudelft.scrumbledore.userinterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * Class responsible for generating the keybinding options and the arming of all graphical elements
 * for these options.
 * 
 * @author Jeroen Meijer
 */
public final class KeybindingSettings {

  private static VBox currentBox;
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

    generateOptions();

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
        if (newButton == player1) {
          selectedPlayer = 1;
        } else if (newButton == player2) {
          selectedPlayer =2;
        }
      }
    });

    playerBox.getChildren().addAll(player1, player2);
    currentBox.getChildren().addAll(playerBox);
  }

  private static void generateKeybindings() {
    HBox finalBox = new HBox();
    
    VBox actionLabels = new VBox();
    VBox actionBinders = new VBox();
    for (PlayerAction action : PlayerAction.values()) {
      actionBinders.getChildren().add(generateKeybinder(action));
    }
    finalBox.getChildren().addAll(actionLabels,actionBinders);
    currentBox.getChildren().add(finalBox);
  }
  
  private static Button generateKeybinder(PlayerAction action) {
    Button actionBinder = new Button("test");
    
    
    
    return actionBinder;
  }
}
