package nl.tudelft.scrumbledore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;

/**
 * Class for the maintenance of strings used throughout the game. Meant to allow easy fixing
 * for later.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class Constants {

  /**
   * Private constructor as no instance of a utility class should ever be possible to instantiate.
   */
  private Constants() {
  }

  public static final int GUIX = 646;
  public static final int GUIY = 723;

  public static final int LEVELX = 640;
  public static final int LEVELY = 640;

  public static final int REFRESH_RATE = 30;

  public static final int NUMBER_OF_PLAYERS = 2;
  public static final List<String> PLAYER_COLORS = Collections.unmodifiableList(Arrays.asList(
      "green", "blue"));

  public static final String SCRUMBLEDORE_TEXT = "SCRUMBLEDORE";
  public static final String SINGLEPLAYERGAME_BUTTON = "Singleplayer Game";
  public static final String MULTIPLAYERGAME_BUTTON = "Multiplayer Game";
  public static final String SETTINGS_BUTTON = "Settings";
  public static final String EXIT_BUTTON = "Exit";

  public static final String SCORELABEL = "Score:";
  public static final String HISCORELABEL = "High-Score:";
  public static final String POWERUPLABEL = "Power-Ups:";
  public static final String NOPOWERUP_LABEL = "NONE";
  public static final String LEVELLABEL = "Level:";

  public static final String ADVANCINGLABEL = "Advancing to next level in a few moments...";
  public static final int ADVANCING_DELAY = 5000;

  public static final String STARTBTNLABEL = "Start";
  public static final String STOPBTNLABEL = "Stop";
  public static final String SETTINGSBTNLABEL = "Settings";
  public static final String EXITBTNLABEL = "Exit";

  public static final String LOGGING_PLAYER_MOVEMENT = "Do you wish to track player movement?";
  public static final String LOGGING_PLAYER_INPUT = "Do you wish to track player input?";
  public static final String LOGGING_GAME_STARTSTOP = "Do you wish to track game "
      + "states, such as pausing and restarting?";
  public static final String LOGGING_SHOOTING = "Do you wish to track shooting?";
  public static final String LOGGING_POINTS = "Do you wish to track points and high-scores?";
  public static final String LOGGING_ENEMY = "Do you wish to track enemy actions and changes?";
  public static final String LOGGING_ACTIVE = "Yes";
  public static final String LOGGING_DISABLED = "No";
  public static final String SETTINGSCLOSE = "Close";

  private static boolean loggingWantMovement = false;
  private static boolean loggingWantInput = false;
  private static boolean loggingWantStartStop = false;
  private static boolean loggingWantShooting = false;
  private static boolean loggingWantPoints = false;
  private static boolean loggingWantEnemy = false;

  public static final String SETTINGS_LABEL = "Settings";
  public static final String SETTINGS_YES = "Yes";
  public static final String SETTINGS_NO = "No";
  public static final String SETTINGSEXIT_BUTTON = "Back";
  public static final String SETTINGSLOGGING_BUTTON = "Logging";
  public static final String SETTINGSKEYBINDING_BUTTON = "Keybinding";
  public static final String SETTINGS_CHOOSEKEYQUERY = "Press the desired key...";
  public static final int SETTINGS_CHOOSEKEYPADDING = 50;

  public static final String SETTINGS_PLAYER1 = "Player 1";
  public static final String SETTINGS_PLAYER2 = "Player 2";

  public static final int MAINMENU_PADDING = 30;
  public static final int SETTINGS_PADDING = 20;
  public static final int GAME_PADDING = 10;

  public static final String GAME_SCORELABEL = "Score";
  public static final String GAME_HISCORELABEL = "High-Score";
  public static final String GAME_POWERUPLABEL = "Power-Ups";
  public static final String GAME_LEVELLABEL = "Level";

  public static final String GAME_STARTBUTTON = "Start";
  public static final String GAME_STOPBUTTON = "Stop";
  public static final String GAME_SETTINGSBUTTON = "Settings";
  public static final String GAME_EXITBUTTON = "Exit";

  public static final String GAMEWIN_HEADER = "SCRUMMASTER";
  public static final String GAMEWIN_DIALOG = "You beat the game, congratulations!";
  public static final String GAMEWIN_POINTS = "You scored ";
  public static final String GAMEWIN_HIGHSCORE = " points with a session high score of ";
  public static final String GAMEWIN_TOMAINMENU = "Return to the main menu";

  public static final double BLOCKSIZE = 32;

  public static final double NUM_BLOCKS = 20;

  public static final double DOUBLE_PRECISION = 0.001;

  public static final double COLLISION_PRECISION = 1;
  public static final double COLLISION_RADIUS = 64;

  public static final double GRAVITY_STRENGTH = 2;
  public static final double GRAVITY_MAX = 8;

  public static final double PLAYER_SPEED = 8;
  public static final double PLAYER_CHILI_MULTIPLIER = 1.8;
  public static final double PLAYER_JUMP = 22;

  public static final double BUBBLE_SPEED = 20;
  public static final double FIREBALL_SPEED = 9;
  public static final double BUBBLE_FRICTION = 1.5;
  public static final double BUBBLE_FLOAT = 2;
  public static final double BUBBLE_BOUNCE = 8;
  public static final double BUBBLE_LIFETIME = REFRESH_RATE * 3;

  public static final double PLAYER_POWERUP_LIFETIME = REFRESH_RATE * 3;
  public static final double CHILI_LIFETIME_MULTIPLIER = 0.4;
  public static final double TURTLE_LIFETIME_MULTIPLIER = 0.7;

  public static final double NPC_SPEED = 4;

  public static final double RAPID_FIRE_LIMIT = 5;

  public static final String LEVELS_DIR = "src" + System.getProperty("file.separator") + "main"
      + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator")
      + "levels" + System.getProperty("file.separator");
  public static final String RESOURCES_DIR = "src" + System.getProperty("file.separator") + "main"
      + System.getProperty("file.separator") + "resources" + System.getProperty("file.separator");
  public static final String APPDATA_DIR = System.getProperty("user.dir")
      + System.getProperty("file.separator") + "appdata" + System.getProperty("file.separator");

  public static final String SPRITES_DIR = "images" + System.getProperty("file.separator")
      + "sprites" + System.getProperty("file.separator");
  public static final String LOGGER_DIR = "logger" + System.getProperty("file.separator");

  public static final String CSS_MAINMENU = "css/mainmenustyle.css";
  public static final String CSS_SETTINGS = "css/settingsstyle.css";
  public static final String CSS_GAMEVIEW = "css/gameviewstyle.css";
  public static final String CSS_VICTORY = "css/victorystyle.css";

  public static final double ANIMATED_SPRITES_INTERVAL = REFRESH_RATE / 10;

  public static final List<Map<KeyCode, LevelElementAction>> KEY_MAPPING = createKeyMapping();

  /**
   * Returns a list of maps containing the keybinding/key mapping for players one and two.
   * 
   * @return A list containing a map with the keybindings/key mapping for both players.
   */
  private static List<Map<KeyCode, LevelElementAction>> createKeyMapping() {
    List<Map<KeyCode, LevelElementAction>> keyMapping 
        = new ArrayList<Map<KeyCode, LevelElementAction>>();

    keyMapping.add(createKeyMappingP1());
    keyMapping.add(createKeyMappingP2());

    return keyMapping;
  }

  /**
   * Returns a map containing the keybinding/key mapping for player one.
   * 
   * @return A map containing the keys and their associated actions.
   */
  private static Map<KeyCode, LevelElementAction> createKeyMappingP1() {
    Map<KeyCode, LevelElementAction> keyMapping = new HashMap<KeyCode, LevelElementAction>();
    keyMapping.put(KeyCode.LEFT, LevelElementAction.MoveLeft);
    keyMapping.put(KeyCode.RIGHT, LevelElementAction.MoveRight);
    keyMapping.put(KeyCode.UP, LevelElementAction.Jump);
    keyMapping.put(KeyCode.CONTROL, LevelElementAction.Shoot);

    return keyMapping;
  }

  /**
   * Returns a map containing the keybinding/key mapping for player two.
   * 
   * @return A map containing the keys and their associated actions.
   */
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
   * @return Whether movement should be logged.
   */
  public static boolean isLoggingWantMovement() {
    return loggingWantMovement;
  }

  /**
   * Returns whether input should be logged.
   * 
   * @return Whether input should be logged.
   */
  public static boolean isLoggingWantInput() {
    return loggingWantInput;
  }

  /**
   * Returns whether the usage of the start/stop button should be logged.
   * 
   * @return Whether the usage of the start/stop button should be logged.
   */
  public static boolean isLoggingWantStartStop() {
    return loggingWantStartStop;
  }

  /**
   * Returns whether shooting actions should be logged.
   * 
   * @return Whether shooting actions should be logged.
   */
  public static boolean isLoggingWantShooting() {
    return loggingWantShooting;
  }

  /**
   * Returns whether obtained points should be logged.
   * 
   * @return Whether obtained points should be logged.
   */
  public static boolean isLoggingWantPoints() {
    return loggingWantPoints;
  }

  /**
   * Returns whether the location of enemies should be logged.
   * 
   * @return Whether the location of enemies should be logged.
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
