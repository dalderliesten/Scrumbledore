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

    for (NPC npc : level.getNPCs()) {

      if (npc.hasAction(NPCAction.MoveLeft)) {
        npc.getSpeed().setX(-Constants.NPC_SPEED);
      }

      if (npc.hasAction(NPCAction.MoveRight)) {
        npc.getSpeed().setX(Constants.NPC_SPEED);
      }

      npc.clearActions();

    }

  }

}
