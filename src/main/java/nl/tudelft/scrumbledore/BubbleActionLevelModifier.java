package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on the Bubble.
 * 
 * @author Floris Doolaard
 *
 */
public class BubbleActionLevelModifier implements LevelModifier{

  /**
   * Processing the actions to be performed on the Bubble.
   */
  public void modify(Level level, double delta) {
    
    for(Bubble bub : level.getBubbles()) {
      if(bub.hasAction(BubbleAction.MoveLeft)){
        bub.getSpeed().setX(-1 * Constants.BUBBLE_SPEED);
      }
      if(bub.hasAction(BubbleAction.MoveRight)) {
        bub.getSpeed().setX(Constants.BUBBLE_SPEED);
      }
    }  
  }

}
