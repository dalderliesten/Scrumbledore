package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on an NPC.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 *
 */
public class NPCLevelModifier implements LevelModifier {

  
  public void modify(Level level, double delta) {
    
    // Loop over all NPCs
    for (LevelElement element: level.getMovingElements()) {
      if (element.getClass().equals(NPC.class)) {
        NPC npc = (NPC) element;
        
        // Stop Horizontal Movement.
        if (npc.hasAction(NPCAction.MoveStop)) {
          npc.getSpeed().setX(0);
        }
        
        // Horizontal Movement.
        if (npc.hasAction(NPCAction.MoveLeft)) {
          npc.getSpeed().setX(-1 * Constants.NPC_SPEED);
        }
        
        if (npc.hasAction(NPCAction.MoveRight)) {
          npc.getSpeed().setX(Constants.NPC_SPEED);
        }
        
        // Clear actions for next step.
        npc.clearActions();
      }
    } 

  }

}
