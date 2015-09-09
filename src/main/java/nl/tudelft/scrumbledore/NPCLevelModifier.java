package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on the Player.
 * 
 * @author Jesse Tilro
 *
 */
public class NPCLevelModifier implements LevelModifier {

  /**
   * Process the actions to be performed on the Player.
   * 
   * @param level
   *          The level in which the player actions need to be processed.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  public void modify(Level level, double delta) {

    ArrayList<LevelElement> elements = level.getMovingElements();
    NPC npc = new NPC(null, null);
    
    for (LevelElement le : elements) {
      if (le.getClass().equals(NPC.class)) {
        npc = (NPC) le;
        break;
      }
    }
    
    // Stop Horizontal Movement.
    if (npc.hasAction(PlayerAction.MoveStop)) {
      npc.getSpeed().setX(0);
    }
    
    // Horizontal Movement.
    if (npc.hasAction(PlayerAction.MoveLeft)) {
      npc.getSpeed().setX(-1 * Constants.PLAYER_SPEED);
    }
    if (npc.hasAction(PlayerAction.MoveRight)) {
      npc.getSpeed().setX(Constants.PLAYER_SPEED);
    }
    
    // Clear actions for next step.
    npc.clearActions();
  }

}
