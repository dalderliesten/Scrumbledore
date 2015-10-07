package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The settings menu class handles the functioning of the settings menu as well as its display.
 * 
 * @author David Alderliesten
 */
public final class Settingsmenu {
  private static Stage settingsStage;
  private static Scene currentScene;
  private static VBox currentBox;

  /**
   * Constructor is set to private, as only one instance of the settings menu should exist at any
   * given time.
   */
  private Settingsmenu() {
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
    
    VBox loggingOptions = LoggingSettings.fetchLoggingOptions();
    currentBox.getChildren().addAll(loggingOptions);
    
    exitButtonHandling();

    currentScene = new Scene(currentBox);
    settingsStage.setScene(currentScene);

    currentScene.getStylesheets().add(Constants.CSS_SETTINGS);

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

    // Setting a unique CSS ID for correct themeing.
    titleLabel.setId("settingstitle");

    currentBox.getChildren().add(titleLabel);
  }

  /**
   * Handles the creation and the return from the settings menu.
   */
  private static void exitButtonHandling() {
    Button exitButton = new Button(Constants.SETTINGSEXIT_BUTTION);
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

}
