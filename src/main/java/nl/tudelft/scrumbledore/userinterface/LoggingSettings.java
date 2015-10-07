package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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

    generateOptions();

    return currentBox;
  }

  /**
   * Generates the options needed for the logger in the settings menu.
   */
  private static void generateOptions() {
    generateMovement();
  }

  /**
   * Generates button functionality for the movement query.
   */
  private static void generateMovement() {
    HBox movementBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryLocation = new Label(Constants.LOGGING_PLAYER_MOVEMENT);
    final ToggleGroup movementGroup = new ToggleGroup();
    final RadioButton movementTrue = new RadioButton(Constants.SETTINGS_YES);
    movementTrue.setToggleGroup(movementGroup);
    final RadioButton movementFalse = new RadioButton(Constants.SETTINGS_NO);
    movementFalse.setToggleGroup(movementGroup);

    if (Constants.LOGGING_WANTMOVEMENT) {
      movementTrue.setSelected(true);
      movementFalse.setSelected(false);
    } else {
      movementFalse.setSelected(true);
      movementTrue.setSelected(false);
    }
    movementGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == movementTrue) {
          Constants.LOGGING_WANTMOVEMENT = true;
        } else if (newButton == movementFalse) {
          Constants.LOGGING_WANTMOVEMENT = false;
        }
      }
    });

    movementBox.getChildren().addAll(queryLocation, movementTrue, movementFalse);
    currentBox.getChildren().addAll(movementBox);
  }

}
