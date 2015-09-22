package nl.tudelft.scrumbledore;

import nl.tudelft.scrumbledore.gui.GUI;

/**
 * Launches the game and containes the main method, which will run the GUI, which in turn will run
 * the game.
 * 
 * @author David Alderliesten
 */
abstract class Scrumbledore {

  /**
   * The main method launches the program.
   * 
   * @pre Program executed
   * @post Program terminated
   * @param args
   *          Arguments given at program initialization
   */
  public static void main(String[] args) {
    // Create a logger to log all actions in this session.
    Logger.start();

    // Creating a launcher to launch the game and GUI.
    GUI.launch(GUI.class);
  }

}
