package nl.tudelft.scrumbledore;

/**
 * A LevelModifier can be used by the Game to update the state of the Level and its LevelElements.
 * 
 * @author Jeroen Meijer
 * @author Jesse Tilro
 *
 */
public interface LevelModifier {

  /**
   * Modify a given level.
   * 
   * @param level
   *          The level to modify.
   * @param delta
   *          The number of steps since the last modification was performed.
   */
  void modify(Level level, double delta);

}
