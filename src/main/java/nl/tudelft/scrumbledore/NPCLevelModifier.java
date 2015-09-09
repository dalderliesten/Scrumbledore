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
    ArrayList<LevelElement> elements = level.getMovingElements();
    NPC npc = new NPC(null, null);
    
    // Temporarily only use 1 NPC
    for (LevelElement le: elements) {
      if (le.getClass().equals(NPC.class)) {
        npc = (NPC) le;
      }
    } 
    
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
