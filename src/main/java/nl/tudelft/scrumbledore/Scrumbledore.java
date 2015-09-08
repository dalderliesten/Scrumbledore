package nl.tudelft.scrumbledore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
    try {
      File testCSS = new File(Constants.GUI_THEME);
      
      if(testCSS.exists()) {
        System.out.println("All good.");
      }
    } catch(Exception e) {
      System.out.println(e);
    }
    
    ScrumbledoreGUI.launch(ScrumbledoreGUI.class);
  }

}
