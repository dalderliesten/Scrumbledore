package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The settings menu class handles the functioning of the settings menu as well as its display.
 * 
 * @author David Alderliesten
 */
public final class SettingsMenu {
  private static Stage settingsStage;
  private static Scene currentScene;
  private static VBox currentBox;

  /**
   * Constructor is set to private, as only one instance of the settings menu should exist at any
   * given time.
   */
  private SettingsMenu() {
  }

  /**
   * The handler handles the creation of the settings scene/menu, and ensures the scene handling is
   * done within the current stage. The settings menu is created as a dialog box to ensure that the
   * player is returned correctly when finished.
   */
  public static void settingsHandle() {
    settingsStage = new Stage();
    currentBox = new VBox(Constants.SETTINGS_PADDING);

    setupSettings();

    generateTitle();

    generateTabs();

    VBox currentSettings = LoggingSettings.fetchLoggingOptions();
    currentBox.getChildren().add(currentSettings);

    exitButtonHandling();

    currentScene = new Scene(currentBox);
    settingsStage.setScene(currentScene);

    currentScene.getStylesheets().add(Constants.CSS_SETTINGS);
    preventMoving();
    settingsStage.show();
  }

  /**
   * Sets up parameters for settings window, including its modality and parameters.
   */
  private static void setupSettings() {
    settingsStage.initStyle(StageStyle.UTILITY);
    settingsStage.setTitle("Settings");
    settingsStage.setHeight(Constants.GUIY);
    settingsStage.setWidth(Constants.GUIX);
    settingsStage.setResizable(false);
  }

  /**
   * Generates the title for the settings menu.
   */
  private static void generateTitle() {
    Label titleLabel = new Label(Constants.SETTINGS_LABEL);
    titleLabel.setId("settingstitle");

    currentBox.getChildren().add(titleLabel);
  }

  /**
   * Generates the settings tabs which the player can choose from.
   */
  private static void generateTabs() {
    HBox hbox = new HBox();

    Button loggingButton = getLoggingButton();
    Button keybindingButton = getKeybindingButton();

    hbox.getChildren().addAll(loggingButton, keybindingButton);

    currentBox.getChildren().add(hbox);
  }

  /**
   * Gets the logging button for the current option.
   * 
   * @return The button for the logging function.
   */
  private static Button getLoggingButton() {
    final Button button = new Button(Constants.SETTINGSLOGGING_BUTTON);
    button.getStyleClass().add("tab");

    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        currentBox.getChildren().set(2, LoggingSettings.fetchLoggingOptions());
      }
    });

    return button;
  }

  /**
   * Gets the button needed for the curreny key function.
   * 
   * @return The button for the keybinding.
   */
  private static Button getKeybindingButton() {
    final Button button = new Button(Constants.SETTINGSKEYBINDING_BUTTON);
    button.getStyleClass().add("tab");

    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        currentBox.getChildren().set(2, KeybindingSettings.fetchKeybindingOptions());
      }
    });

    return button;
  }

  /**
   * Handles the creation and the return from the settings menu.
   */
  private static void exitButtonHandling() {
    Button exitButton = new Button(Constants.SETTINGSEXIT_BUTTON);
    mapExitFunction(exitButton);

    currentBox.getChildren().add(exitButton);
  }

  /**
   * Handling the actions needed for the exit game choice.
   * 
   * @param passedButton
   *          The button that has been passed that requires exit assigning.
   */
  private static void mapExitFunction(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        settingsStage.close();
      }

    });
  }

  /**
   * Ensures the settings window cannot be moved around.
   */
  private static void preventMoving() {
    currentScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        settingsStage.centerOnScreen();
      }
    });
  }

}
