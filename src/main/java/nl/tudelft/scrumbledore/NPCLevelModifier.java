package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on an NPC.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
public class NPCLevelModifier {


  public void modify(Level level, double delta, NPC npc) {
    
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
