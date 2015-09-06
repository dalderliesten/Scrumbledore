package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions.
 * 
 * @author David Alderliesten
 */
public class ScrumbledoreGUI extends Application {

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
    // Setting the title of the user interface window.
    gameStage.setTitle("Scrumbledore");

    // Setting the resolution needed for the entire GUI.
    gameStage.setHeight(Constants.RESOLUTIONY);
    gameStage.setWidth(Constants.RESOLUTIONX);

    // Removing the ability to resize the game window. Must be disabled due to fixed dimensions.
    gameStage.setResizable(false);

    // Setting the content handler group object, to which objects within the game must be added.
    Group contentHandler = new Group();

    // Creating of the scene and assigning this scene to the game stage.
    Scene mainScene = new Scene(contentHandler);
    gameStage.setScene(mainScene);

    // Creation of the game display canvas, and adding a graphics context object to allow for
    // simple, call based refreshing. Canvas is then added to the scene of the window.
    Canvas gameDisplay = new Canvas(Constants.RESOLUTIONX, Constants.CANVASRESOLUTIONY);
    GraphicsContext gamePainter = gameDisplay.getGraphicsContext2D();
    contentHandler.getChildren().add(gameDisplay);

    // Displaying the user interface to the user.
    gameStage.show();
  }

}
