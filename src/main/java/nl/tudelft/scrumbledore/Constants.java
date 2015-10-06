package nl.tudelft.scrumbledore;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;

/**
 * Class for the maintainence of strings for display in visual and GUI. Meant to allow easy fixing
 * for later.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
public final class Constants {
  // Empty private constructor for the utility class.
  private Constants() {
  }

  // Setting the resolution of the entire GUI window.
  public static final int GUIX = 646;
  public static final int GUIY = 723;

  // Setting the resolution constants for level/game display.
  public static final int LEVELX = 640;
  public static final int LEVELY = 640;

  // Setting the refresh rates for the calculations.
  public static final int REFRESH_RATE = 30;

  // Defining labels needed in the top section of the user interface.
  public static final String SCORELABEL = "Score:";
  public static final String HISCORELABEL = "High-Score:";
  public static final String POWERUPLABEL = "Power-Ups:";
  public static final String LEVELLABEL = "Level:";

  // Defining text needed to display the buttons in the bottom of the user interface.
  public static final String STARTBTNLABEL = "Start";
  public static final String STOPBTNLABEL = "Stop";
  public static final String SETTINGSBTNLABEL = "Settings";
  public static final String EXITBTNLABEL = "Exit";

  // Defining the text needed for the display of options in the settings menu.
  public static final String LOGGING_PLAYER_MOVEMENT = "Do you wish to track player movement?";
  public static final String LOGGING_PLAYER_INPUT = "Do you wish to track player input?";
  public static final String LOGGING_GAME_STARTSTOP = "Do you wish to track game states, such as pausing and restarting?";
  public static final String LOGGING_SHOOTING = "Do you wish to track shooting?";
  public static final String LOGGING_POINTS = "Do you wish to track points and high-scores?";
  public static final String LOGGING_ENEMY = "Do you wish to track enemy actions and changes?";
  public static final String LOGGING_ACTIVE = "Yes";
  public static final String LOGGING_DISABLED = "No";
  public static final String SETTINGSCLOSE = "Close";

  // Defining the settings value tracking.
  public static boolean LOGGING_WANTMOVEMENT = false;
  public static boolean LOGGING_WANTINPUT = false;
  public static boolean LOGGING_WANTSTARTSTOP = false;
  public static boolean LOGGING_WANTSHOOTING = false;
  public static boolean LOGGING_WANTPOINTS = false;
  public static boolean LOGGING_WANTENEMY = false;

  // Defining text needed for the display of dialog and handling boxes within the user interface.
  public static final String GAMEWIN_DIALOG = "You beat the game, congratulations!";

  // The standard size of a block.
  public static final double BLOCKSIZE = 32;

  // The height and width of the board in number of blocks.
  public static final double NUM_BLOCKS = 20;

  // Double precision for use within computational parts of the program.
  public static final double DOUBLE_PRECISION = 0.001;

  // Collision precision delta.
  public static final double COLLISION_PRECISION = 1;
  // Collision radius
  public static final double COLLISION_RADIUS = 64;

  // Gravity strength.
  public static final double GRAVITY_STRENGTH = 2;
  // Gravity maximal vertical speed.
  public static final double GRAVITY_MAX = 8;

  // Player horizontal movement speed.
  public static final double PLAYER_SPEED = 8;
  // Player jump strength.
  public static final double PLAYER_JUMP = 22;

  // Bubble horizontal movement speed
  public static final double BUBBLE_SPEED = 20;
  // Bubble horizontal fiction
  public static final double BUBBLE_FRICTION = 1.5;
  // Bubble horizontal fiction
  public static final double BUBBLE_FLOAT = 2;
  // Bubble bounce speed
  public static final double BUBBLE_BOUNCE = 8;
  // Number of steps a Bubble needs to stay alive.
  public static final double BUBBLE_LIFETIME = REFRESH_RATE * 3;

  // NPC horizontal movement speed
  public static final double NPC_SPEED = 4;

  // Maximum number of Bubbles per second that can be fired
  public static final double RAPID_FIRE_LIMIT = 5;

  // Location of the directories for the program.
  public static final String LEVELS_DIR = "src/main/resources/";
  public static final String RESOURCES_DIR = "src/main/resources/";
  public static final String APPDATA_DIR = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "appdata" + System.getProperty("file.separator");

  // Location of the directories for resources, such as sprites and CSS styling.
  public static final String SPRITES_DIR = "images/sprites/";
  public static final String CSS_LOCATION = "css/style.css";
  public static final String LOGGER_DIR =  "logger" + System.getProperty("file.separator");

  // Setting the interval for the animation of the sprites.
  public static final double ANIMATED_SPRITES_INTERVAL = REFRESH_RATE / 10;

  // Keymapping reference location.
  public static final List<Map<KeyCode, PlayerAction>> KEY_MAPPING = createKeyMapping();

  // Generation of keymapping list to prevent action skipping.
  private static List<Map<KeyCode, PlayerAction>> createKeyMapping() {
    List<Map<KeyCode, PlayerAction>> keyMapping = new ArrayList<Map<KeyCode, PlayerAction>>();

    keyMapping.add(createKeyMappingP1());
    keyMapping.add(createKeyMappingP2());

    return keyMapping;
  }

  // Performing key mapping for player one.
  private static Map<KeyCode, PlayerAction> createKeyMappingP1() {
    Map<KeyCode, PlayerAction> keyMapping = new HashMap<KeyCode, PlayerAction>();
    keyMapping.put(KeyCode.LEFT, PlayerAction.MoveLeft);
    keyMapping.put(KeyCode.RIGHT, PlayerAction.MoveRight);
    keyMapping.put(KeyCode.UP, PlayerAction.Jump);
    keyMapping.put(KeyCode.NUMPAD0, PlayerAction.Shoot);

    return keyMapping;
  }

  // Performing key mapping for player two.
  private static Map<KeyCode, PlayerAction> createKeyMappingP2() {
    Map<KeyCode, PlayerAction> keyMapping = new HashMap<KeyCode, PlayerAction>();
    keyMapping.put(KeyCode.A, PlayerAction.MoveLeft);
    keyMapping.put(KeyCode.D, PlayerAction.MoveRight);
    keyMapping.put(KeyCode.W, PlayerAction.Jump);
    keyMapping.put(KeyCode.CONTROL, PlayerAction.Shoot);

    return keyMapping;
  }
}
