package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * The main controller of the game. Connects model classes and the GUI.
 * 
 * @author Jesse Tilro
 * @author David Alderliesten
 */
public class Game {

  private ArrayList<Level> levels;
  private ArrayList<LevelModifier> modifiers;
  private Level currentLevel;
  private ScoreCounter score;
  private double steps;
  private int currentLevelNumber;

  /**
   * Constructs a new Game from disk.
   */
  public Game() {
    LevelParser lp = new LevelParser();
    construct(lp.getLevels());

    currentLevelNumber = 1;
  }

  /**
   * Constructs a new Game based on a given ArrayList<Level>.
   * 
   * @param levels
   *          An ArrayList of levels
   */
  public Game(ArrayList<Level> levels) {
    construct(levels);
  }

  /**
   * Shared part of the constructors.
   * 
   * @param levels
   *          The levels for the game.
   */
  public void construct(ArrayList<Level> levels) {
    this.score = new ScoreCounter();
    this.steps = 0;

    assert levels.size() > 0;

    this.levels = levels;
    this.currentLevel = levels.get(0);

    KineticsLevelModifier kinetics = new KineticsLevelModifier();

    this.modifiers = new ArrayList<LevelModifier>();
    this.modifiers.add(new PlayerActionsLevelModifier());
    this.modifiers.add(new GravityLevelModifier());
    this.modifiers.add(new NPCLevelModifier());
    this.modifiers.add(new CollisionsLevelModifier(kinetics, score));
    this.modifiers.add(new BubbleActionsLevelModifier());
    this.modifiers.add(kinetics);

  }

  /**
   * Runs invariant assertions required for the functioning of the game.
   */
  public void invariant() {
    assert levels.contains(currentLevel);
  }

  /**
   * Get the current level.
   * 
   * @return The current Level.
   */
  public Level getCurrentLevel() {
    return currentLevel;
  }

  /**
   * Returns the current level number.
   * 
   * @return The current level number as a string.
   */
  public String getCurrentLevelNumber() {
    return Integer.toString(currentLevelNumber);
  }

  /**
   * Returns the list of all levels in the game.
   * 
   * @return All levels in the game.
   */
  public ArrayList<Level> getLevels() {
    return levels;
  }

  /**
   * Set the current level to the given level.
   * 
   * @param level
   *          The level.
   */
  public void setCurrentLevel(Level level) {
    assert levels.contains(level);
    this.currentLevel = level;
  }

  /**
   * Return the number of remaining levels to be played after the current level is won.
   * 
   * @return An integer number of remaining levels.
   */
  public int remainingLevels() {
    invariant();
    int remaining = 0;
    while (!currentLevel.equals(levels.get(levels.size() - 1 - remaining))) {
      ++remaining;
    }
    return remaining;
  }

  /**
   * Advances the game to the next level.
   */
  public void goToNextLevel() {
    invariant();
    assert remainingLevels() > 0;

    int index = 0;
    while (!currentLevel.equals(levels.get(index)) && index < levels.size()) {
      ++index;
    }

    setCurrentLevel(levels.get(index + 1));

    currentLevelNumber = currentLevelNumber + 1;

    invariant();
  }

  /**
   * Restarting the game.
   */
  public void restart() {
    LevelParser lp = new LevelParser();
    construct(lp.getLevels());

    Logger.log("--------------------PLAYED DIED");
  }

  /**
   * Performs a step, the next cycle in the game.
   * 
   * @param delta
   *          The relative period of time passed with respect to the expected period of time since
   *          the last step.
   */
  public void step(double delta) {
    addSteps(delta);
    for (LevelModifier modifier : modifiers) {
      modifier.modify(currentLevel, delta);
    }
  }

  /**
   * Returns the current value of the score.
   * 
   * @return Value of the current score.
   */
  public String getScore() {
    return score.getScoreString();
  }

  /**
   * Returns the current value of the high score.
   * 
   * @return Value of the high score.
   */
  public String getHighScore() {
    return Integer.toString(score.getHighScore());
  }

  /**
   * Increment this Game's step counter with a given number of (partial) steps.
   * 
   * @param steps
   *          The number of (partial) steps to be added.
   */
  public void addSteps(double steps) {
    this.steps += steps;
  }

  /**
   * Get the exact number of steps that have been performed in this game.
   * 
   * @return The number of steps performed.
   */
  public double getSteps() {
    return steps;
  }

  /**
   * Get the number of entire steps that have been performed in this game.
   * 
   * @return The number of entire steps performed.
   */
  public int getFullSteps() {
    return (int) Math.floor(steps);
  }

}
