package nl.tudelft.scrumbledore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.tudelft.scrumbledore.level.LevelElementAction;
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
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
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

  // Defining the maximum number of players.
  public static final int NUMBER_OF_PLAYERS = 2;
  public static final List<String> PLAYER_COLORS = Collections.unmodifiableList(Arrays.asList(
      "green", "blue"));

  // Defining text needed for the labels and buttons at the main menu of the GUI.
  public static final String SCRUMBLEDORE_TEXT = "SCRUMBLEDORE";
  public static final String SINGLEPLAYERGAME_BUTTON = "Singleplayer Game";
  public static final String MULTIPLAYERGAME_BUTTON = "Multiplayer Game";
  public static final String SETTINGS_BUTTON = "Settings";
  public static final String EXIT_BUTTON = "Exit";

  // Defining labels needed in the top section of the user interface.
  public static final String SCORELABEL = "Score:";
  public static final String HISCORELABEL = "High-Score:";
  public static final String POWERUPLABEL = "Power-Ups:";
  public static final String LEVELLABEL = "Level:";

  // Defining labels and values needed for the advancement GUI pop-ups.
  public static final String ADVANCINGLABEL = "Advancing to next level in a few moments...";
  public static final int ADVANCING_DELAY = 5000;

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
  private static boolean loggingWantMovement = false;
  private static boolean loggingWantInput = false;
  private static boolean loggingWantStartStop = false;
  private static boolean loggingWantShooting = false;
  private static boolean loggingWantPoints = false;
  private static boolean loggingWantEnemy = false;

  // Defining text needed for the labels and buttons at the settings menu of the GUI.
  public static final String SETTINGS_LABEL = "Settings";
  public static final String SETTINGS_YES = "Yes";
  public static final String SETTINGS_NO = "No";
  public static final String SETTINGSEXIT_BUTTON = "Back";
  public static final String SETTINGSLOGGING_BUTTON = "Logging";
  public static final String SETTINGSKEYBINDING_BUTTON = "Keybinding";
  public static final String SETTINGS_CHOOSEKEYQUERY = "Press the desired key...";
  public static final int SETTINGS_CHOOSEKEYPADDING = 50;

  // Defining the text needed for the display of the keybinding options.
  public static final String SETTINGS_PLAYER1 = "Player 1";
  public static final String SETTINGS_PLAYER2 = "Player 2";

  // Defining constants needed for the user interface items.
  public static final int MAINMENU_PADDING = 30;
  public static final int SETTINGS_PADDING = 20;
  public static final int GAME_PADDING = 10;

  // Defining labels needed in the top section of the user interface.
  public static final String GAME_SCORELABEL = "Score";
  public static final String GAME_HISCORELABEL = "High-Score";
  public static final String GAME_POWERUPLABEL = "Power-Ups";
  public static final String GAME_LEVELLABEL = "Level";

  // Defining labels needed in the bottom section of the user interface.
  public static final String GAME_STARTBUTTON = "Start";
  public static final String GAME_STOPBUTTON = "Stop";
  public static final String GAME_SETTINGSBUTTON = "Settings";
  public static final String GAME_EXITBUTTON = "Exit";

  // Defining text needed for the display of dialog and handling boxes within the user interface.
  public static final String GAMEWIN_HEADER = "SCRUMMASTER";
  public static final String GAMEWIN_DIALOG = "You beat the game, congratulations!";
  public static final String GAMEWIN_POINTS = "You scored ";
  public static final String GAMEWIN_HIGHSCORE = " points with a session high score of ";
  public static final String GAMEWIN_TOMAINMENU = "Return to the main menu";

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
  public static final double PLAYER_CHILI_MULTIPLIER = 1.8;
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
  
  // Number of steps a ChiliChicken needs to stay alive.
  public static final double CHILI_LIFETIME = REFRESH_RATE * 3;

  // NPC horizontal movement speed
  public static final double NPC_SPEED = 4;

  // Maximum number of Bubbles per second that can be fired
  public static final double RAPID_FIRE_LIMIT = 5;

  // Location of the directories for the program.
  public static final String LEVELS_DIR = "src" + System.getProperty("file.separator") + "main"
      + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
      + "levels" + System.getProperty("file.separator");
  public static final String RESOURCES_DIR = "src" + System.getProperty("file.separator") + "main"
      + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator");
  public static final String APPDATA_DIR = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "appdata" + System.getProperty("file.separator");

  // Location of the directories for resources, such as sprites and logging locations.
  public static final String SPRITES_DIR = "images" + System.getProperty("file.separator")
      + "sprites" + System.getProperty("file.separator");
  public static final String LOGGER_DIR = "logger" + System.getProperty("file.separator");

  // Location of the css file. Kept as such due to bugs with FX.
  public static final String CSS_MAINMENU = "css/mainmenustyle.css";
  public static final String CSS_SETTINGS = "css/settingsstyle.css";
  public static final String CSS_GAMEVIEW = "css/gameviewstyle.css";
  public static final String CSS_VICTORY = "css/victorystyle.css";

  // Setting the interval for the animation of the sprites.
  public static final double ANIMATED_SPRITES_INTERVAL = REFRESH_RATE / 10;

  // Keymapping reference location.
  public static final List<Map<KeyCode, LevelElementAction>> KEY_MAPPING = createKeyMapping();

  // Generation of keymapping list to prevent action skipping.
  private static List<Map<KeyCode, LevelElementAction>> createKeyMapping() {
    List<Map<KeyCode, LevelElementAction>> keyMapping = new ArrayList<Map<KeyCode, 
        LevelElementAction>>();

    keyMapping.add(createKeyMappingP1());
    keyMapping.add(createKeyMappingP2());

    return keyMapping;
  }

  // Performing key mapping for player one.
  private static Map<KeyCode, LevelElementAction> createKeyMappingP1() {
    Map<KeyCode, LevelElementAction> keyMapping = new HashMap<KeyCode, LevelElementAction>();
    keyMapping.put(KeyCode.LEFT, LevelElementAction.MoveLeft);
    keyMapping.put(KeyCode.RIGHT, LevelElementAction.MoveRight);
    keyMapping.put(KeyCode.UP, LevelElementAction.Jump);
    keyMapping.put(KeyCode.CONTROL, LevelElementAction.Shoot);

    return keyMapping;
  }

  // Performing key mapping for player two.
  private static Map<KeyCode, LevelElementAction> createKeyMappingP2() {
    Map<KeyCode, LevelElementAction> keyMapping = new HashMap<KeyCode, LevelElementAction>();
    keyMapping.put(KeyCode.A, LevelElementAction.MoveLeft);
    keyMapping.put(KeyCode.D, LevelElementAction.MoveRight);
    keyMapping.put(KeyCode.W, LevelElementAction.Jump);
    keyMapping.put(KeyCode.Q, LevelElementAction.Shoot);

    return keyMapping;
  }

  /**
   * Returns whether movement should be logged.
   * 
   * @return whether movement should be logged
   */
  public static boolean isLoggingWantMovement() {
    return loggingWantMovement;
  }

  /**
   * Returns whether input should be logged.
   * 
   * @return whether input should be logged
   */
  public static boolean isLoggingWantInput() {
    return loggingWantInput;
  }

  /**
   * Returns whether the usage of the start/stop button should be logged.
   * 
   * @return whether the usage of the start/stop button should be logged
   */
  public static boolean isLoggingWantStartStop() {
    return loggingWantStartStop;
  }

  /**
   * Returns whether shooting actions should be logged.
   * 
   * @return whether shooting actions should be logged
   */
  public static boolean isLoggingWantShooting() {
    return loggingWantShooting;
  }

  /**
   * Returns whether obtained points should be logged.
   * 
   * @return whether obtained points should be logged.
   */
  public static boolean isLoggingWantPoints() {
    return loggingWantPoints;
  }

  /**
   * Returns whether the location of enemies should be logged.
   * 
   * @return whether the location of enemies should be logged.
   */
  public static boolean isLoggingWantEnemy() {
    return loggingWantEnemy;
  }

  /**
   * Set whether movement should be logged.
   * 
   * @param loggingWantMovement
   *          whether movement should be logged
   */
  public static void setLoggingWantMovement(boolean loggingWantMovement) {
    Constants.loggingWantMovement = loggingWantMovement;
  }

  /**
   * Set whether input should be logged.
   * 
   * @param loggingWantInput
   *          whether input should be logged
   */
  public static void setLoggingWantInput(boolean loggingWantInput) {
    Constants.loggingWantInput = loggingWantInput;
  }

  /**
   * Set whether the usage of the start/stop button should be logged.
   * 
   * @param loggingWantStartStop
   *          whether the usage of the start/stop button should be logged
   */
  public static void setLoggingWantStartStop(boolean loggingWantStartStop) {
    Constants.loggingWantStartStop = loggingWantStartStop;
  }

  /**
   * Set whether shooting actions should be logged.
   * 
   * @param loggingWantShooting
   *          whether shooting actions should be logged
   */
  public static void setLoggingWantShooting(boolean loggingWantShooting) {
    Constants.loggingWantShooting = loggingWantShooting;
  }

  /**
   * Set whether obtained points should be logged.
   * 
   * @param loggingWantPoints
   *          whether obtained points should be logged
   */
  public static void setLoggingWantPoints(boolean loggingWantPoints) {
    Constants.loggingWantPoints = loggingWantPoints;
  }

  /**
   * Set whether the location of enemies should be logged.
   * 
   * @param loggingWantEnemy
   *          whether the location of enemies should be logged
   */
  public static void setLoggingWantEnemy(boolean loggingWantEnemy) {
    Constants.loggingWantEnemy = loggingWantEnemy;
  }

}
