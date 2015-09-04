package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.stage.Stage;

public class ScrumbledoreGUI extends Application {
  
  /**
   * start The start method launches the JavaFX GUI window and handles associated start-up items.
   * 
   * @pre Method called and gameStage passed
   * @post GUI created and handlers prepared
   * @param gameStage
   */
  @Override
  public void start(Stage gameStage) throws Exception {
    gameStage.setTitle("Scrumbledore");
    gameStage.show();
  }
  
}
