package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class responsible for generating the logging options and the arming of all graphical elements for
 * these options.
 * 
 * @author David Alderliesten
 */
public final class LoggingSettings {
  private static VBox currentBox;

  /**
   * Constructor is set to private, as only one instance of the settings menu should exist at any
   * given time.
   */
  private LoggingSettings() {
  }

  /**
   * Performs all the actions required to get the settings for logging.
   * 
   * @return A JavaFX VBox containing all the options and associated actions.
   */
  public static VBox fetchLoggingOptions() {
    currentBox = new VBox(Constants.SETTINGS_PADDING);

    generatePlayerMovement();

    return currentBox;
  }

  /**
   * Generates the logging options and labels for the player movement query.
   */
  private static void generatePlayerMovement() {
    HBox useBox = new HBox(15);
    Label query = new Label(Constants.LOGGING_PLAYER_MOVEMENT);
    final ToggleGroup toggleGroup = new ToggleGroup();
    final RadioButton logTrue = new RadioButton(Constants.SETTINGS_YES);
    logTrue.setToggleGroup(toggleGroup);
    final RadioButton logFalse = new RadioButton(Constants.SETTINGS_NO);
    logFalse.setToggleGroup(toggleGroup);

    if (Constants.LOGGING_WANTMOVEMENT) {
      logTrue.setSelected(true);
    } else {
      logFalse.setSelected(true);
    }
    
    toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      public void changed(ObservableValue<? extends Toggle> original, Toggle oldToggle,
          Toggle newToggle) {
        if (newToggle == logTrue) {
          Constants.LOGGING_WANTMOVEMENT = true;
        } else if (newToggle == logFalse) {
          Constants.LOGGING_WANTMOVEMENT = false;
        }
      }
    });

    useBox.getChildren().addAll(query, logTrue, logFalse);
    currentBox.getChildren().add(useBox);
  }

}
