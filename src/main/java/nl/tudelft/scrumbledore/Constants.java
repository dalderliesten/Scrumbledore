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
  public static final int GUIX = 640;
  public static final int GUIY = 750;

  // Setting the resolution constants for level/game display.
  public static final int LEVELY = 640;
  public static final int LEVELX = 640;
  
  // Setting the refresh rates for the calculations.
  public static final int refreshRate = 30;

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

  // Collision precision delta.
  public static final double COLLISION_PRECISION = 1;

  // Collision radius
  public static final double COLLISION_RADIUS = 64;
  
  // Location of the resource string.
  public static final String LEVELS_DIR = "src/main/resources/";
  
  // Location of the CSS resource for the GUI styling.
  public static final String CSS_LOCATION = "css/style.css";

  // References to images utilized for the game display within the GUI.
  public static final String BUBBLE_SPRITE = "images/Bubble/Bubble_32x32.png";
  public static final String FRUIT_SPRITE = "images/Fruit/Fruit_32x32.png";
  public static final String NPC_SPRITE = "images/NPC/NPC_32x32.png";
  public static final String PLATFORM_SPRITE = "images/Platform/Platform_32x32.png";
  public static final String PLAYER_SPRITE = "images/Player/Player_32x32.png";
}
