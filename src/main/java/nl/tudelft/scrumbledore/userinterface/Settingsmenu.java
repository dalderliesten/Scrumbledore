package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.scene.Scene;
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
    
    currentScene = new Scene(currentBox);
    settingsStage.setScene(currentScene);

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
    
    currentBox.getChildren().add(titleLabel);
  }

}
