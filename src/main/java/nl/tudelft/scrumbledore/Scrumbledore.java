package nl.tudelft.scrumbledore;

/**
 * Launches the game and containes the main method, which will run the GUI.
 * 
 * @author David Alderliesten
 */
public class Scrumbledore {

  /**
   * The main method launches the program.
   * 
   * @pre Program executed
   * @post Program terminated
   * @param args
   *          Arguments given at program initialization
   */
  public static void main(String[] args) {
    ScrumbledoreGUI.launch(args);
  }

}
