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
@SuppressWarnings("PMD.TooManyMethods")
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
    generateInput();
    generateStartStop();
    generateShooting();
    generatePoints();
    generateEnemy();
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

    if (Constants.isLoggingWantMovement()) {
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
          Constants.setLoggingWantMovement(true);
        } else if (newButton == movementFalse) {
          Constants.setLoggingWantMovement(false);
        }
      }
    });

    movementBox.getChildren().addAll(queryLocation, movementTrue, movementFalse);
    currentBox.getChildren().addAll(movementBox);
  }

  /**
   * Generates the queries for the input logging within the settings menu.
   */
  private static void generateInput() {
    HBox inputBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryInput = new Label(Constants.LOGGING_PLAYER_INPUT);
    final ToggleGroup inputGroup = new ToggleGroup();
    final RadioButton inputTrue = new RadioButton(Constants.SETTINGS_YES);
    inputTrue.setToggleGroup(inputGroup);
    final RadioButton inputFalse = new RadioButton(Constants.SETTINGS_NO);
    inputFalse.setToggleGroup(inputGroup);

    if (Constants.isLoggingWantInput()) {
      inputTrue.setSelected(true);
      inputFalse.setSelected(false);
    } else {
      inputFalse.setSelected(true);
      inputTrue.setSelected(false);
    }
    inputGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == inputTrue) {
          Constants.setLoggingWantInput(true);
        } else if (newButton == inputFalse) {
          Constants.setLoggingWantInput(false);
        }
      }
    });

    inputBox.getChildren().addAll(queryInput, inputTrue, inputFalse);
    currentBox.getChildren().addAll(inputBox);
  }

  /**
   * Generate the start/stop options or logging for the settings menu.
   */
  private static void generateStartStop() {
    HBox startStopBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryStartStop = new Label(Constants.LOGGING_GAME_STARTSTOP);
    final ToggleGroup startStopGroup = new ToggleGroup();
    final RadioButton startStopTrue = new RadioButton(Constants.SETTINGS_YES);
    startStopTrue.setToggleGroup(startStopGroup);
    final RadioButton startStopFalse = new RadioButton(Constants.SETTINGS_NO);
    startStopFalse.setToggleGroup(startStopGroup);

    if (Constants.isLoggingWantStartStop()) {
      startStopTrue.setSelected(true);
      startStopFalse.setSelected(false);
    } else {
      startStopFalse.setSelected(true);
      startStopTrue.setSelected(false);
    }
    startStopGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == startStopTrue) {
          Constants.setLoggingWantStartStop(true);
        } else if (newButton == startStopFalse) {
          Constants.setLoggingWantStartStop(false);
        }
      }
    });

    startStopBox.getChildren().addAll(queryStartStop, startStopTrue, startStopFalse);
    currentBox.getChildren().addAll(startStopBox);
  }

  /**
   * Generate the shooting options or logging for the settings menu.
   */
  private static void generateShooting() {
    HBox shootBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryShoot = new Label(Constants.LOGGING_SHOOTING);
    final ToggleGroup shootGroup = new ToggleGroup();
    final RadioButton shootTrue = new RadioButton(Constants.SETTINGS_YES);
    shootTrue.setToggleGroup(shootGroup);
    final RadioButton shootFalse = new RadioButton(Constants.SETTINGS_NO);
    shootFalse.setToggleGroup(shootGroup);

    if (Constants.isLoggingWantShooting()) {
      shootTrue.setSelected(true);
      shootFalse.setSelected(false);
    } else {
      shootFalse.setSelected(true);
      shootTrue.setSelected(false);
    }
    shootGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == shootTrue) {
          Constants.setLoggingWantShooting(true);
        } else if (newButton == shootFalse) {
          Constants.setLoggingWantShooting(false);
        }
      }
    });

    shootBox.getChildren().addAll(queryShoot, shootTrue, shootFalse);
    currentBox.getChildren().addAll(shootBox);
  }

  /**
   * Generate the points options or logging for the settings menu.
   */
  private static void generatePoints() {
    HBox pointBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryPoint = new Label(Constants.LOGGING_POINTS);
    final ToggleGroup pointGroup = new ToggleGroup();
    final RadioButton pointTrue = new RadioButton(Constants.SETTINGS_YES);
    pointTrue.setToggleGroup(pointGroup);
    final RadioButton pointFalse = new RadioButton(Constants.SETTINGS_NO);
    pointFalse.setToggleGroup(pointGroup);

    if (Constants.isLoggingWantPoints()) {
      pointTrue.setSelected(true);
      pointFalse.setSelected(false);
    } else {
      pointFalse.setSelected(true);
      pointTrue.setSelected(false);
    }
    pointGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == pointTrue) {
          Constants.setLoggingWantPoints(true);
        } else if (newButton == pointFalse) {
          Constants.setLoggingWantPoints(false);
        }
      }
    });

    pointBox.getChildren().addAll(queryPoint, pointTrue, pointFalse);
    currentBox.getChildren().addAll(pointBox);
  }

  /**
   * Generate the enemy options or logging for the settings menu.
   */
  private static void generateEnemy() {
    HBox enemyBox = new HBox(Constants.SETTINGS_PADDING);
    Label queryEnemy = new Label(Constants.LOGGING_ENEMY);
    final ToggleGroup enemyGroup = new ToggleGroup();
    final RadioButton enemyTrue = new RadioButton(Constants.SETTINGS_YES);
    enemyTrue.setToggleGroup(enemyGroup);
    final RadioButton enemyFalse = new RadioButton(Constants.SETTINGS_NO);
    enemyFalse.setToggleGroup(enemyGroup);

    if (Constants.isLoggingWantEnemy()) {
      enemyTrue.setSelected(true);
      enemyFalse.setSelected(false);
    } else {
      enemyFalse.setSelected(true);
      enemyTrue.setSelected(false);
    }
    enemyGroup.selectedToggleProperty().addListener(new ChangeListener<Object>() {
      public void changed(ObservableValue<? extends Object> param, Object oldButton,
          Object newButton) {
        if (newButton == enemyTrue) {
          Constants.setLoggingWantEnemy(true);
        } else if (newButton == enemyFalse) {
          Constants.setLoggingWantEnemy(false);
        }
      }
    });

    enemyBox.getChildren().addAll(queryEnemy, enemyTrue, enemyFalse);
    currentBox.getChildren().addAll(enemyBox);
  }

}
