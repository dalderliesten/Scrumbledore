package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;
import java.util.Iterator;

import nl.tudelft.scrumbledore.Constants;

/**
 * Level Modifier that processes the actions to be performed on the Bubble.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity",
    "PMD.StdCyclomaticComplexity" })
public class BubbleActionsLevelModifier implements LevelModifier {

  /**
   * Processing the actions to be performed on the Bubble.
   * 
   * @param level
   *          The current level.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public void modify(Level level, double delta) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Bubble> enemyBubbles = level.getEnemyBubbles();

    Iterator<Bubble> iter = level.getBubbles().iterator();

    while (iter.hasNext()) {
      Bubble bub = iter.next();
      if (bub.getLifetime() <= 0) {
        if (bub.hasNPC()) {
          try {
            enemies.add(new NPC(bub.getPosition().clone(),
                new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE)));
          } catch (CloneNotSupportedException e) {
            e.printStackTrace();
          }
          enemyBubbles.remove(bub);
        }
        iter.remove();
      } else {
        bub.decreaseLifetime(delta);

        if (bub.vSpeed() > -Constants.BUBBLE_FLOAT) {
          bub.getSpeed().difference(new Vector(0, Constants.BUBBLE_FRICTION * delta));
        }
        if (bub.hasAction(LevelElementAction.MoveLeft)) {
          bub.getSpeed().setX(-1 * Constants.BUBBLE_SPEED);
        }
        if (bub.hasAction(LevelElementAction.MoveRight)) {
          bub.getSpeed().setX(Constants.BUBBLE_SPEED);
        }

        bub.clearActions();
      }
    }

  }

}
