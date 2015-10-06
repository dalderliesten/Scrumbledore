package nl.tudelft.scrumbledore.userinterface;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Mainmenu class performs all actions related to the main menu, as well as ensuring successful
 * display.
 * 
 * @author David Alderliesten
 */
public final class Mainmenu {
  private static Stage gameStage;
  private Scene currentScene;

  /**
   * Constructor is set to private, as only one instance of the main menu should exist.
   */
  private Mainmenu() {
  }

  /**
   * The constructor handles the creation of the main menu scene, and ensures the scene handling is
   * done within the current stage.
   * 
   * @param passedStage
   *          The stage that is active that has been passed.
   */
  public static void mainMenuStart(Stage passedStage) {
    gameStage = passedStage;

    // currentScene = new Scene();
    // gameStage.setScene(currentScene);

    gameStage.show();
  }

}
