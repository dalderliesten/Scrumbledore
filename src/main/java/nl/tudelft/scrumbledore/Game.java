package nl.tudelft.scrumbledore;

import java.util.List;

/**
 * The main controller of the game. Connects model classes and the GUI.
 * 
 * @author Jesse Tilro
 *
 */
public class Game {

  private List<Level> levels;
  private Level currentLevel;
  private Collisions collisions;

  /**
   * Constructs a new Game.
   * 
   * @param levels
   *          The levels the game needs to consist of.
   */
  public Game(List<Level> levels) {
    // The game needs at least one level.
    assert levels.size() > 0;

    this.levels = levels;
    this.currentLevel = levels.get(0);

    Gravity.setStrength(1);
    Gravity.setMax(8);
    
    this.collisions = new Collisions();
  }

  /**
   * Runs invariant assertions.
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
   * Returns the list of all levels in the game.
   * 
   * @return All levels in the game.
   */
  public List<Level> getLevels() {
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

    // Find index of current level.
    int index = 0;
    while (!currentLevel.equals(levels.get(index)) && index < levels.size()) {
      ++index;
    }
    // Set current level to the successor of this level.
    setCurrentLevel(levels.get(index + 1));

    invariant();
  }

  /**
   * Performs a step, the next cycle in the game.
   * 
   * @param delta
   *          The relative period of time passed with respect to the expected period of time since
   *          the last step.
   */
  public void step(double delta) {
    // Pull down all gravity affected elements in the current level.
    Gravity.pull(currentLevel, delta);
    // Detect collisions.
    collisions.detectPlayer(currentLevel, delta);
    // Apply kinetics update.
    Kinetics.update(currentLevel, delta);
  }

}
