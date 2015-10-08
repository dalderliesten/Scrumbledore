////@SuppressWarnings("checkstyle:methodlength")
////public class GUI extends Application {
////  private Game game;
////  private StepTimer timer;
////  private SpriteStore sprites;
////  private Stage stage;
////  private Scene scene;
////  private VBox layout;
////  private Group gameView;
////  private Canvas staticDisplay;
////  private Canvas dynamicDisplay;
////  private GraphicsContext staticPainter;
////  private GraphicsContext dynamicPainter;
////  private AnimationTimer animationTimer = new AnimationTimer() {
////    public void handle(long currentNanoTime) {
////      checkPlayerAlive();
////      advanceLevel();
////      renderDynamic();
////    }
////  };
////
////  private Button startStopButton;
////  private Button settingsButton;
////  private Button exitButton;
////  private Label scoreLabel;
////  private Label levelLabel;
////  private Label highScoreLabel;
////  private Label powerUpLabel;
////
////  /**
////   * Advances the level to the next one, and displays a special dialog box upon completion of all
////   * the levels without dying.
////   */
////  private void advanceLevel() {
////
////    // When the enemies in the current level have been killed.
////    if (game.getCurrentLevel().getNPCs().isEmpty()
////        && game.getCurrentLevel().getEnemyBubbles().isEmpty()) {
////
////      // If there are no levels left in the game, show a message.
////      if (game.remainingLevels() == 0) {
////        // Log the completion of the game.
////        Logger.getInstance().log("Player completed the game successfully.");
////
////        // Creating of the dialog pop-up stage.
////        Stage gameWinStage = new Stage();
////
////        // Setting the parent of the dialog window.
////        gameWinStage.initModality(Modality.APPLICATION_MODAL);
////        gameWinStage.initOwner(stage);
////
////        // Creation of a vertical box to display the label, and creation of the label.
////        VBox gameWinVBox = new VBox(20);
////        Label gameWinLabel = new Label(Constants.GAMEWIN_DIALOG);
////        gameWinVBox.getChildren().add(gameWinLabel);
////
////        // Creation of a scene to display the virtual box and associated label.
////        Scene gameWinScene = new Scene(gameWinVBox, 300, 200);
////
////        // Showing the dialog box.
////        gameWinStage.setScene(gameWinScene);
////        gameWinStage.show();
////
////        // Halting the animation timer.
////        animationTimer.stop();
////      } else {
////        // Logging the advancement in level.
////        Logger.getInstance().log("Player advanced to the next level.");
////
////        // Go to the next level and then re-render it.
////        game.goToNextLevel();
////        renderStatic();
////      }
////    }
////  }