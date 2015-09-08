package nl.tudelft.scrumbledore;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.*;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions that are related to the
 * GUI.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
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
    timer = new StepTimer(Constants.refreshRate, game);

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

    // Creation of a horizontal box for storing top labels and items to display.
    HBox topItems = new HBox();

    // Linking the labels needed in the top HBox to their constant referneces.
    Label scoreLabel = new Label(Constants.SCORELABEL);
    Label highScoreLabel = new Label(Constants.HISCORELABEL);
    Label powerUpLabel = new Label(Constants.POWERUPLABEL);
    Label levelLabel = new Label(Constants.LEVELLABEL);

    // Adding the top labels to the top HBox and to the game display interface.
    topItems.getChildren().addAll(scoreLabel, powerUpLabel, levelLabel, highScoreLabel);
    contentHandler.setTop(topItems);

    // Creation of the game display canvas, and adding a graphics context object to allow for
    // simple, call based refreshing. Canvas is then added to the scene of the window.
    Canvas gameDisplay = new Canvas(Constants.GUIX, Constants.GUIY);
    GraphicsContext gamePainter = gameDisplay.getGraphicsContext2D();

    // Getting and fetching essential elements of the level.
    Level currentLevel = game.getCurrentLevel();
    ArrayList<Platform> platforms = currentLevel.getPlatforms();

    // Placing the platform elements within the level.
    for (Platform current : platforms) {
      // Painting the current platform image at the desired x and y location given by the vector.
      gamePainter.drawImage(new Image(Constants.PLATFORM_SPRITE), current.getPosition().getX(),
          current.getPosition().getY());
    }

    // Displaying the parsed level content in the center of the user interface.
    contentHandler.setCenter(gameDisplay);

    // Creation of a horiztonal box for storing bottom buttons and items to display.
    HBox bottomItems = new HBox();

    // Linking the buttons needed to their associated constants namesake.
    final Button startStopButton = new Button();
    final Button settingsButton = new Button(Constants.SETTINGSBTNLABEL);
    final Button exitButton = new Button(Constants.EXITBTNLABEL);

    // Checking the state of the game/timer to determine the start/stop button status needed for the
    // text.
    if (timer.isPaused()) {
      timer.resume();
      startStopButton.setText(Constants.STOPBTNLABEL);
    } else {
      timer.pause();
      startStopButton.setText(Constants.STARTBTNLABEL);
    }

    // Mapping the function of the start/stop button to start/stop the game when the button is
    // pressed.
    startStopButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        // If the game is paused, it will resume it and change the button label to stop. Otherwise,
        // it resumes the game and changes the butotn label to start.
        if (timer.isPaused()) {
          timer.resume();
          startStopButton.setText(Constants.STOPBTNLABEL);
        } else {
          timer.pause();
          startStopButton.setText(Constants.STARTBTNLABEL);
        }
      }

    });

    // Mapping the settings button menu actionevent to trigger when the button is pressed.
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
          System.out.println("SETTINGS HOOK ACTIVATED");
      }

    });

    // Mapping the exit function to the exit button to quit when button is pressed.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        System.exit(0);
      }

    });

    // Adding the buttons to the bottom Hbox and to the game display interface.
    bottomItems.getChildren().addAll(startStopButton, settingsButton, exitButton);
    contentHandler.setBottom(bottomItems);

    // Displaying the user interface.
    gameStage.show();
  }
}
