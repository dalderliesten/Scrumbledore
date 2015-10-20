package nl.tudelft.scrumbledore.userinterface;

import nl.tudelft.scrumbledore.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The User Interface class handles the creation and launching of the user interface window.
 * 
 * @author David Alderliesten
 */
public class UserInterface extends Application {

  /**
   * The Userinterface constuctor is overridden by javaFX.
   */
  public UserInterface() {
  }

  @Override
  public void start(Stage gameStage) {
    gameStage.setTitle("Scrumbledore");
    gameStage.setHeight(Constants.GUIY);
    gameStage.setWidth(Constants.GUIX);
    gameStage.setResizable(false);
    
    MainMenu.mainMenuHandle(gameStage);
  }

}
