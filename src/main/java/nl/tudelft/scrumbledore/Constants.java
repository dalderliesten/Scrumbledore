package nl.tudelft.scrumbledore;

/**
 * Class for the maintainence of strings for display in visual and GUI. Meant to allow easy fixing
 * for later.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
public class Constants {

  // Setting the resolution of the entire GUI window.
  public static final int RESOLUTIONX = 640;
  public static final int RESOLUTIONY = 700;

  // Setting the resolution constants for GUI game display.
  public static final int CANVASRESOLUTIONY = 640;

  // Defenining labels needed in the top section of the user interface.
  public static final String SCORELABEL = "Score: ";
  public static final String HISCORELABEL = "High-Score: ";
  public static final String POWERUPLABEL = "Power-Ups: ";
  public static final String LEVELLABEL = "Level: ";

  // Defining text needed to display the buttons in the bottom of the user interface.
  public static final String STARTBTNLABEL = "Start";
  public static final String STOPBTNLABEL = "Stop";
  public static final String SETTINGSBTNLABEL = "Settings";
  public static final String EXITBTNLABEL = "Exit";

  // The standard size of a block.
  public static final double BLOCKSIZE = 32;

  // Double precision for use within computational parts of the program.
  public static final double DOUBLE_PRECISION = 0.001;

  // Collision precision delta
  public static final double COLLISION_PRECISION = 1;

  // Reference within the file structure to the CSS for the GUI.
  public static final String GUI_THEME = "";
}
