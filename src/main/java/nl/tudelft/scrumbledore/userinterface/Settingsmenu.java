package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The settings menu class handles the functioning of the settings menu as well as its display.
 * 
 * @author David Alderliesten
 */
public final class Settingsmenu {
  private static Stage gameStage;
  private static Scene currentScene;
  private static VBox contentBox;

  /**
   * Constructor is set to private, as only one instance of the settings menu should exist at any
   * given time.
   */
  private Settingsmenu() {
  }

  /**
   * The handler handles the creation of the settings scene/menu, and ensures the scene handling is
   * done within the current stage.
   * 
   * @param passedStage
   *          The stage that is active that has been passed.
   */
  public static void settingsHandle(Stage passedStage) {
    gameStage = passedStage;
    contentBox = new VBox(Constants.SETTINGS_PADDING);

    currentScene = new Scene(contentBox);
    gameStage.setScene(currentScene);

    gameStage.show();
  }

}
