package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;
import java.util.Iterator;

import nl.tudelft.scrumbledore.Constants;

/**
 * Level Modifier that processes the actions to be performed on the projectile.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.ModifiedCyclomaticComplexity",
    "PMD.StdCyclomaticComplexity" })
public class ProjectileActionsLevelModifier implements LevelModifier {

  /**
   * Processing the actions to be performed on projectiles.
   * 
   * @param level
   *          The current level.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public void modify(Level level, double delta) {
    modifyBubble(level, delta);
    modifyFireball(level, delta);

  }

  /**
   * Modifies the stat of a bubble.
   * 
   * @param level
   *          , the level of the bubble.
   * @param delta
   *          , the step in which this bubble is moving.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public static void modifyBubble(Level level, double delta) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Projectile> enemyBubbles = level.getEnemyBubbles();

    Iterator<Projectile> iter = level.getProjectiles().iterator();

    while (iter.hasNext()) {
      Projectile bub = iter.next();
      if (bub instanceof Bubble) {
        if (bub.getLifetime() <= 0) {
          if (bub.hasNPC()) {
            try {
              enemies.add(new NPC(bub.getPosition().clone(), new Vector(Constants.BLOCKSIZE,
                  Constants.BLOCKSIZE)));
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

  /**
   * Modifies the stat of a bubble.
   * 
   * @param level
   *          , the level of the bubble.
   * @param delta
   *          , the step in which this bubble is moving.
   */
  public static void modifyFireball(Level level, double delta) {
    ArrayList<Projectile> projectiles = level.getProjectiles();

    for (int i = 0; i < projectiles.size(); i++) {
      Projectile current = projectiles.get(i);
      if (current instanceof Fireball) {

        if (current.hasAction(LevelElementAction.MoveLeft)) {
          current.getSpeed().setX(-1 * Constants.BUBBLE_SPEED);
        } else if (current.hasAction(LevelElementAction.MoveRight)) {
          current.getSpeed().setX(Constants.BUBBLE_SPEED);
        }
      }
    }

  }
}
