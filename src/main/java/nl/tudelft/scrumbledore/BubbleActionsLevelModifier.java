package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Level Modifier that processes the actions to be performed on the Bubble.
 * 
 * @author Floris Doolaard
 *
 */
public class BubbleActionsLevelModifier implements LevelModifier {

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
    ArrayList<NPC> enemies = level.getNPCs();
    for (Bubble bubble : level.getBubbles()) {
      bubbles.add(bubble);
    }

    for (Bubble bub : bubbles) {
      if (bub.getLifetime() <= 0) {
        if (bub.hasNPC()) {
          enemies.add(new NPC(bub.getPosition().clone(), new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE)));
        }
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

        warp(bub);
        
        bub.clearActions();
      }
    }

  }

  /**
   * When a Bubble leaves the level from the top, it should warp to the bottom of the level.
   * 
   * @param bubble
   *          The Bubble to be warped.
   */
  private void warp(Bubble bubble) {
    if (bubble.posY() <= -bubble.height() / 2) {
      bubble.getPosition().setY(Constants.LEVELY + bubble.height() / 2);
    }
  }

}
