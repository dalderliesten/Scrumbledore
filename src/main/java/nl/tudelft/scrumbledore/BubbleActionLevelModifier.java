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
   * @param level
   *          The current level.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  public void modify(Level level, double delta) {
    
    
    for (Bubble bub : level.getBubbles()) {
      bub.getSpeed().setY(-Constants.BUBBLE_FLOAT);
      if (bub.hasAction(BubbleAction.MoveLeft)) {
        bub.getSpeed().setX(-1 * Constants.BUBBLE_SPEED);
      }
      if (bub.hasAction(BubbleAction.MoveRight)) {
        bub.getSpeed().setX(Constants.BUBBLE_SPEED);
      }
      bub.clearActions();
    }
    
  }

}
