package nl.tudelft.scrumbledore;

/**
 * Level Modifier that processes the actions to be performed on an NPC.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class NPCLevelModifier implements LevelModifier {

  /**
   * Make NPC's move in the given level.
   * 
   * @param level
   *          The level.
   * @param delta
   *          The number of steps passed since this method was last called.
   */
  public void modify(Level level, double delta) {

    // For the moment, the NPC does not need to be controlled here.

  }

}
