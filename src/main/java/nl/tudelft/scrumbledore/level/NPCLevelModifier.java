package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;

/**
 * Level Modifier that processes the actions to be performed on an NPC.
 * 
 * @author David Alderliesten
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

      if (npc.hasAction(LevelElementAction.Jump)) {
        npc.getSpeed().setY(Constants.NPC_SPEED);
      }

      if (npc.hasAction(LevelElementAction.MoveLeft)) {
        npc.getSpeed().setX(-Constants.NPC_SPEED);
      }

      if (npc.hasAction(LevelElementAction.MoveRight)) {
        npc.getSpeed().setX(Constants.NPC_SPEED);
      }

      npc.clearActions();

    }

  }

}
