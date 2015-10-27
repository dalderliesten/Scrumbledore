package nl.tudelft.scrumbledore.game;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.LevelModifier;

/**
 * The class responsible for aggregating all model classes of the Game, and therefore modeling a
 * Game session.
 * 
 * @author Jesse Tilro
 * @author David Alderliesten
 */
public abstract class Game {
  private ArrayList<Level> levels;
  private ArrayList<LevelModifier> modifiers;
  private Level currentLevel;
  private ScoreCounter score;
  private double steps;

  /**
   * Constructs a new Game with levels parsed from file system.
   */
  public Game() {
    construct(makeLevels());
  }

  /**
   * Constructs a new Game with given levels, used for testing purposes.
   * 
   * @param levels
   *          The levels this Game needs to consist of.
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
  private void construct(ArrayList<Level> levels) {
    assert levels.size() > 0;

    this.levels = levels;
    this.currentLevel = this.levels.get(0);

    this.score = new ScoreCounter();
    this.steps = 0;

    this.modifiers = new ArrayList<LevelModifier>();
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
  public int getCurrentLevelNumber() {
    invariant();
    int index = 0;
    while (!currentLevel.equals(levels.get(index)) && index < levels.size()) {
      ++index;
    }
    return index + 1;
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

    int newIndex = getCurrentLevelNumber();

    setCurrentLevel(levels.get(newIndex));

    invariant();
  }

  /**
   * Register a new LevelModifier that should be triggered on this Game's steps.
   * 
   * @param levelModifier
   *          The LevelModifier to be registered.
   */
  public void registerLevelModifier(LevelModifier levelModifier) {
    modifiers.add(levelModifier);
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
   * Get the Game's ScoreCounter.
   * 
   * @return The Game's ScoreCounter.
   */
  public ScoreCounter getScoreCounter() {
    return score;
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

  /**
   * Restart the Game.
   */
  public void restart() {
    levels = makeLevels();
    score.resetScore();
    currentLevel = levels.get(0);
  }

  /**
   * Returns a list of LevelModifiers.
   * 
   * @return A list of levelModifiers
   */
  public ArrayList<LevelModifier> getModifiers() {
    return modifiers;
  }

  /**
   * Make the Levels for this Game.
   * 
   * @return A list of Levels.
   */
  protected abstract ArrayList<Level> makeLevels();
}
