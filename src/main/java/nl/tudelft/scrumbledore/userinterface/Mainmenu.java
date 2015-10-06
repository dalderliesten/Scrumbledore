package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Mainmenu class performs all actions related to the main menu, as well as ensuring successful
 * display.
 * 
 * @author David Alderliesten
 */
public final class Mainmenu {
  private static Stage gameStage;
  private static Scene currentScene;
  private static VBox contentBox;

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
  public static void mainMenuHandle(Stage passedStage) {
    gameStage = passedStage;
    contentBox = new VBox();
    
    generateButtons();
    
    currentScene = new Scene(contentBox);
    gameStage.setScene(currentScene);

    gameStage.show();
  }
  
  /**
   * Generates the main menu buttons.
   */
  private static void generateButtons() {
    Button singleplayerGameButton = new Button(Constants.SINGLEPLAYERGAME_BUTTON);
    
    Button multiplayerGamebutton = new Button(Constants.MULTIPLAYERGAME_BUTTON);
    
    Button settingsButton = new Button(Constants.SETTINGS_BUTTON);
    
    Button exitButton = new Button(Constants.EXIT_BUTTION);
  }

}
