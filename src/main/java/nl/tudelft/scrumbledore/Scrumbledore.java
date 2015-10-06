package nl.tudelft.scrumbledore;

import java.io.File;
import java.io.IOException;

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
    makeAppData();

    // To create the logging directory and session file already.
    Logger.getInstance();

    GUI.launch(GUI.class);
  }

  /**
   * Creates the appData folder, which is used by multiple classes for issues such as logging and
   * keybinding saving.
   */
  private static void makeAppData() {
    File appDataDir = new File(Constants.APPDATA_DIR);

    try {
      if (!appDataDir.exists()) {
        boolean result = appDataDir.mkdir();

        if (!result) {
          throw new IOException();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
