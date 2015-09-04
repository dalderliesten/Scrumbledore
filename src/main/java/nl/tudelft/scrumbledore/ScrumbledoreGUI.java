package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions.
 * 
 * @author David Alderliesten
 */
public class ScrumbledoreGUI extends Application {

  /**
   * The start method launches the JavaFX GUI window and handles associated start-up items.
   * 
   * @pre Method called and gameStage passed
   * @post GUI created and handlers prepared
   * @param gameStage
   *          The stage required to display the GUI.
   */
  @Override
  public void start(Stage gameStage) {
    // Setting the title of the GUI to be Scrumbledore.
    gameStage.setTitle("Scrumbledore");

    // Setting the width and height of the GUI stage.
    gameStage.setHeight(700);
    gameStage.setWidth(800);
    
    // Ensuring that the window cannot be resized.
    gameStage.setResizable(false);

    // Rendering the stage in the current form.
    gameStage.show();
  }

}
