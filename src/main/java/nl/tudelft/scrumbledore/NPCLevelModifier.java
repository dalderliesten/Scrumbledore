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

    // Loop over all NPCs
    for (NPC npc : level.getNPCs()) {
      // Assign platforms to NPC if this has not happened yet
      if (npc.getPlatforms() == null) {
        npc.setPlatforms(level.getPlatforms());
      }

      // Move into a certain direction if this is indicated by the NPC
      if (npc.getMovementDirection().equals(NPCAction.MoveRight)) {
        npc.getSpeed().setX(Constants.NPC_SPEED);
      } else if (npc.getMovementDirection().equals(NPCAction.MoveLeft)) {
        npc.getSpeed().setX(-1 * Constants.NPC_SPEED);
      }

      Vector currentPosition = npc.getPosition();

      // Enemy is at left boundary, make it move to the right
      if (currentPosition.distance(npc.getMovementBoundaries()[0]) < 8
          && npc.getMovementDirection().equals(NPCAction.MoveLeft)) {
        npc.getSpeed().setX(0);
        npc.setMovementDirection(NPCAction.MoveRight);
      } else if (currentPosition.distance(npc.getMovementBoundaries()[1]) < 8
          && npc.getMovementDirection().equals(NPCAction.MoveRight)) {
        npc.getSpeed().setX(0);
        npc.setMovementDirection(NPCAction.MoveLeft);
      }
    }
  }

}
