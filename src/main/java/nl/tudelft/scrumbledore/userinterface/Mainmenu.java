package nl.tudelft.scrumbledore.userinterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.tudelft.scrumbledore.Constants;

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
   * The handler handles the creation of the main menu scene, and ensures the scene handling is done
   * within the current stage.
   * 
   * @param passedStage
   *          The stage that is active that has been passed.
   */
  public static void mainMenuHandle(Stage passedStage) {
    gameStage = passedStage;
    contentBox = new VBox(Constants.MAINMENU_PADDING);

    generateLogo();

    generateButtons();

    currentScene = new Scene(contentBox);
    gameStage.setScene(currentScene);

    currentScene.getStylesheets().add(Constants.CSS_MAINMENU);

    gameStage.show();
  }

  /**
   * Generates the logo at the top of the main menu.
   */
  private static void generateLogo() {
    Label logo = new Label(Constants.SCRUMBLEDORE_TEXT);

    contentBox.getChildren().add(logo);
  }

  /**
   * Generates the main menu buttons.
   */
  private static void generateButtons() {
    Button singleplayerGameButton = new Button(Constants.SINGLEPLAYERGAME_BUTTON);
    singleplayerChoice(singleplayerGameButton);

    Button multiplayerGameButton = new Button(Constants.MULTIPLAYERGAME_BUTTON);
    multiplayerChoice(multiplayerGameButton);

    Button settingsButton = new Button(Constants.SETTINGS_BUTTON);
    settingsChoice(settingsButton);

    Button exitButton = new Button(Constants.EXIT_BUTTION);
    exitButtonChoice(exitButton);

    contentBox.getChildren().addAll(singleplayerGameButton, multiplayerGameButton, settingsButton,
        exitButton);
  }

  /**
   * Handling the actions needed for the singleplayer choice.
   * 
   * @param passedButton
   *          The button that has been passed that requires singleplayer game launch assigning.
   */
  private static void singleplayerChoice(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        GameDisplay.launchSinglePlayerGame(gameStage);
      }

    });
  }

  /**
   * Handling the actions needed for the multiplayer choice.
   * 
   * @param passedButton
   *          The button that has been passed that requires multiplayer game launch assigning.
   */
  private static void multiplayerChoice(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        GameDisplay.launchMultiPlayerGame(gameStage);
      }

    });
  }

  /**
   * Handling the actions needed for the settings choice.
   * 
   * @param passedButton
   *          The button that has been passed that requires settings assigning.
   */
  private static void settingsChoice(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        Settingsmenu.settingsHandle();
      }

    });
  }

  /**
   * Handling the actions needed for the exit game choice.
   * 
   * @param passedButton
   *          The button that has been passed that requires exit assigning.
   */
  private static void exitButtonChoice(Button passedButton) {
    passedButton.setOnAction(new EventHandler<ActionEvent>() {

      public void handle(ActionEvent arg0) {
        // Quitting the game with a value of zero, indicating everything worked as intended.
        System.exit(0);
      }

    });
  }

}
