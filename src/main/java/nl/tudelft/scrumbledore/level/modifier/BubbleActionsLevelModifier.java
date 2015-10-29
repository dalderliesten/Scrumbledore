package nl.tudelft.scrumbledore.level.modifier;

import java.util.ArrayList;
import java.util.Iterator;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.Bubble;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.NPC;

/**
 * Level Modifier that processes the actions to be performed on the projectile.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity",
    "PMD.StdCyclomaticComplexity" })
public class BubbleActionsLevelModifier implements LevelModifier {

  /**
   * Processing the actions to be performed on projectiles.
   * 
   * @param level
   *          The current level.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  public void modify(Level level, double delta) {
    Iterator<Bubble> iter = level.getBubbles().iterator();

    while (iter.hasNext()) {
      Bubble bub = iter.next();
      if (bub.getLifetime() <= 0) {
        checkEnemies(level, bub);
        iter.remove();
      } else {
        checkMovement(bub, delta);
      }
    }
  }

  /**
   * Checks if the bubble has enemies trapped inside and acts upon that fact.
   * 
   * @param level
   *          The level to check.
   * @param bubble
   *          The bubble to check.
   */
  public static void checkEnemies(Level level, Bubble bubble) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Bubble> enemyBubbles = level.getEnemyBubbles();
    if (bubble.hasNPC()) {
      try {
        enemies.add(new NPC(bubble.getPosition().clone(),
            new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE)));
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
      enemyBubbles.remove(bubble);
    }
  }

  /**
   * Checks the movement of the bubble and acts upon that fact.
   * 
   * @param bubble
   *          The bubble to check.
   * @param delta
   *          The amount of steps since previous step.
   */
  public static void checkMovement(Bubble bubble, Double delta) {
    bubble.decreaseLifetime(delta);

    if (bubble.vSpeed() > -Constants.BUBBLE_FLOAT) {
      bubble.getSpeed().difference(new Vector(0, Constants.BUBBLE_FRICTION * delta));
    }
    if (bubble.hasAction(LevelElementAction.MoveLeft)) {
      bubble.getSpeed().setX(-1 * Constants.BUBBLE_SPEED);
    }
    if (bubble.hasAction(LevelElementAction.MoveRight)) {
      bubble.getSpeed().setX(Constants.BUBBLE_SPEED);
    }

    bubble.clearActions();
  }

}
