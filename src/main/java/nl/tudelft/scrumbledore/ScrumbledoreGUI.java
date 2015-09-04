package nl.tudelft.scrumbledore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

    // Creation of the border pane for alignment of elements within the UI.
    BorderPane mainPane = new BorderPane();

    // Creating the top handler box as well as the content for it.
    HBox topLabels = new HBox();
    Label scoreLabel = new Label(Constants.scorelabelText);
    Label highLabel = new Label(Constants.highscorelabelText);
    Label powerupLabel = new Label(Constants.poweruplabelText);
    Label levelLabel = new Label(Constants.levellabelText);

    // Adding the content for the top bar to the container box.
    topLabels.getChildren().addAll(scoreLabel, powerupLabel, levelLabel, highLabel);
    mainPane.setTop(topLabels);

    // Creating the bottom handler box as well as the buttons for it.
    HBox bottomButtons = new HBox();
    final Button startstopButton = new Button(Constants.startButtonText);
    Button settingsButton = new Button(Constants.settingsbuttonText);
    Button exitButton = new Button(Constants.exitbuttonText);

    // Defining the desired action of the startstop button, which is to stop the game when active,
    // and restart the game when paused.
    startstopButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        startstopButton.setText(Constants.stopButtonText);
      }
    });

    // Defining the desired action of the settings button, which is to show a settings menu.
    settingsButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        System.out.println("SETTINGS MENU WOULD OPEN NOW");
      }
    });

    // Defining the desired action of the exit button, which is to quit when pressed.
    exitButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
      }
    });

    // Adding the buttons for the bottom bar to the container box.
    bottomButtons.getChildren().addAll(startstopButton, settingsButton, exitButton);
    mainPane.setBottom(bottomButtons);

    // Creation of a scene for display of content.
    Scene mainScene = new Scene(mainPane, 800, 600);
    gameStage.setScene(mainScene);

    // Rendering the stage in the current form.
    gameStage.show();
  }

}
