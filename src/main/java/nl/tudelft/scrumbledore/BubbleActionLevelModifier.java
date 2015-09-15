package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on the Bubble.
 * 
 * @author Floris Doolaard
 *
 */
public class BubbleActionLevelModifier implements LevelModifier {

  /**
   * Processing the actions to be performed on the Bubble.
   * 
   * @param level
   *          The current level.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  public void modify(Level level, double delta) {

    ArrayList<Bubble> bubbles = new ArrayList<Bubble>(); 
    for (Bubble bubble : level.getBubbles()) {
      bubbles.add(bubble);
    }
    
    for (Bubble bub : bubbles) {     
      if (bub.getLifetime() <= 0) {
        level.getBubbles().remove(bub);
      } else {
        bub.decreaseLifetime(delta);

        if (bub.vSpeed() > -Constants.BUBBLE_FLOAT) {
          bub.getSpeed().difference(new Vector(0, Constants.BUBBLE_FRICTION * delta));
        }
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

}
