package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    // Setting the title of the GUI to be Scrumbledore.
    gameStage.setTitle("Scrumbledore");

    // Setting the width and height of the GUI stage.
    gameStage.setHeight(700);
    gameStage.setWidth(800);

    // Ensuring that the window cannot be resized.
    gameStage.setResizable(false);

    // Centering the window.
    gameStage.centerOnScreen();

    // Creating the top handler box as well as the content for it.
    HBox topLabels = new HBox();
    Label scoreLabel = new Label("SCORE");
    Label highLabel = new Label("HIGH SCORE");
    Label powerupLabel = new Label("POWER UP");
    Label levelLabel = new Label("LEVEL");

    // Adding the content for the top bar to the container box.
    topLabels.getChildren().addAll(scoreLabel, powerupLabel, levelLabel, highLabel);

    // Creating the bottom handler box as well as the buttons for it.
    HBox bottomButtons = new HBox();
    Button startstopButton = new Button("START STOP");
    Button settingsButton = new Button("SETTINGS");
    Button exitButton = new Button("EXIT");

    // Adding the buttons for the bottom bar to the container box.
    bottomButtons.getChildren().addAll(startstopButton, settingsButton, exitButton);
    
    // Creation of a scene for display of content.
    Scene mainScene = new Scene();
    gameStage.setScene(mainScene);

    // Rendering the stage in the current form.
    gameStage.show();
  }

}
