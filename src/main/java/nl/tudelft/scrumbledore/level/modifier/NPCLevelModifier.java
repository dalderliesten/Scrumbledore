package nl.tudelft.scrumbledore.level.modifier;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.NPC;

/**
 * Level Modifier that processes the actions to be performed on an NPC.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Niels Warnars
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class NPCLevelModifier implements LevelModifier {

  /**
   * Make NPC's move in the given level.
   * 
   * @param level
   *          The level.
   * 
   * @param delta
   *          The number of steps passed since this method was last called.
   */
  public void modify(Level level, double delta) {

    for (NPC npc : level.getNPCs()) {

      if (npc.hasAction(LevelElementAction.Jump)) {
        npc.getSpeed().setY(-Constants.PLAYER_JUMP / 1.1);
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
