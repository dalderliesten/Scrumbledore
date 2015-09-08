package nl.tudelft.scrumbledore;

/**
 * Class responsible for setting up and launching a game, and connecting it to the StepTimer and the
 * GUI.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
 *
 */
public class Launcher {

  private Game game;
  private StepTimer timer;

  /**
   * Constructs a new Launcher instance.
   */
  public Launcher() {
    // Creation of a game object to display a game.
    game = new Game();
    
    // Creation of a steptimes to provide a restriction in the calculations per second.
    timer = new StepTimer(30, game);
  }
  
  /**
   * launch
   * Launches the GUI associated to this launcher.
   * 
   * @pre method called
   * @post runs the GUI
   */
  public void launch() {
    // Launching the GUI to display all the content.
    ScrumbledoreGUI.launch(ScrumbledoreGUI.class);
  }

}
