package nl.tudelft.scrumbledore.userinterface;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.tudelft.scrumbledore.Constants;

/**
 * Class responsible for generating the keybinding options and the arming of all graphical elements for
 * these options.
 * 
 * @author Jeroen Meijer
 */
public final class KeybindingSettings {

  private static VBox currentBox;

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
    
  }
}
