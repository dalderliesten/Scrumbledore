package scrumbledore;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scrumbledore extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage gameStage) throws Exception {
		gameStage.setTitle("Scrumbledore");
		gameStage.show();
	}

}