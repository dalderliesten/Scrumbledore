package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Launches the Scrumbledore GUI and performs all required handling actions that are related to the
 * GUI.
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
    gameStage.setHeight(Constants.GUIY);
    gameStage.setWidth(Constants.GUIX);

    // Removing the ability to resize the game window. Must be disabled due to fixed dimensions.
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
    topItems.setStyle("-fx-background-color:#000000");
    contentHandler.setTop(topItems);

    // Creation of the game display canvas, and adding a graphics context object to allow for
    // simple, call based refreshing. Canvas is then added to the scene of the window.
    Canvas gameDisplay = new Canvas(Constants.LEVELX, Constants.LEVELY);
    GraphicsContext gamePainter = gameDisplay.getGraphicsContext2D();
    contentHandler.setCenter(gameDisplay);

    // Creation of a horiztonal box for storing bottom buttons and items to display.
    HBox bottomItems = new HBox();

    // Linking the buttons needed to their associated constants namesake.
    final Button startStopButton = new Button(Constants.STARTBTNLABEL);
    final Button settingsButton = new Button(Constants.SETTINGSBTNLABEL);
    final Button exitButton = new Button(Constants.EXITBTNLABEL);

    // Mapping the function of the start/stop button to start/stop the game when the button is
    // pressed.
    startStopButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        startStopButton.setText(Constants.STOPBTNLABEL);
      }

    });

    // Mapping the settings button menu actionevent to trigger when the button is pressed.
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        System.out.println("SETTINGS WOULD OPEN NOW");
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

    // Adding the CSS stylesheet to the scene to style the GUI appearance.
    mainScene.getStylesheets().add(Constants.GUI_THEME);

    // Displaying the user interface to the user.
    gameStage.show();
  }

}
