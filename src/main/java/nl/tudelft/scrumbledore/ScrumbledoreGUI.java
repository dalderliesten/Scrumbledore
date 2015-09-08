package nl.tudelft.scrumbledore;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions that are related to the
 * GUI.
 * 
 * @author David Alderliesten
 */
public class ScrumbledoreGUI extends Application {
  private Game game;
  private StepTimer timer;

  /**
   * The start method launches the JavaFX GUI window and handles associated start-up items and the
   * creation of essential features, such as buttons and display information.
   * 
   * @pre Method called and gameStage passed
   * @post GUI created and handlers prepared
   * @param gameStage
   *          The stage required to display the GUI.
   */
  @Override
  public void start(Stage gameStage) {
    // Instantiate the essential game and step timer functions for the game handling.
    game = new Game();
    timer = new StepTimer(30, game);

    // Setting the title of the GUI window.
    gameStage.setTitle("Scrumbledore");

    // Setting window dimension and movement properties.
    gameStage.setHeight(Constants.GUIY);
    gameStage.setWidth(Constants.GUIX);
    gameStage.setResizable(false);

    // Setting the content handler group object, to which objects within the game must be added.
    BorderPane contentHandler = new BorderPane();

    // Creating of the scene and assigning this scene to the game stage.
    Scene mainScene = new Scene(contentHandler);
    gameStage.setScene(mainScene);

    // Creation of the game display canvas, and adding a graphics context object to allow for
    // simple, call based refreshing. Canvas is then added to the scene of the window.
    Canvas gameDisplay = new Canvas(Constants.GUIX, Constants.GUIY);
    GraphicsContext gamePainter = gameDisplay.getGraphicsContext2D();
    
    // Getting and fetching essential elements of the level.
    Level currentLevel = game.getCurrentLevel();
    ArrayList<Platform> platforms = currentLevel.getPlatforms();
    
    

    // Displaying the parsed level content in the center of the user interface.
    contentHandler.setCenter(gameDisplay);

    // Displaying the user interface.
    gameStage.show();
  }
}
