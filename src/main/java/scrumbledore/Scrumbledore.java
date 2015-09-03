package scrumbledore;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scrumbledore extends Application {

	/**
	 * main
	 * The main method launches the program execution
	 * and launches the GUI and game classes.
	 * 
	 * @pre Program executed
	 * @post Program terminated
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * start
	 * The start method launches the JavaFX GUI window
	 * and handles associated start-up items.
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
