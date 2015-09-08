package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 *
 */
public class CollisionsLevelModifier implements LevelModifier {

  private KineticsLevelModifier kinetics;
  
  /**
   * Constructs a new Collisions Level Modifier using a given Kinetics Level Modifier.
   * @param kinetics The Kinetics Level Modifier to be used.
   */
  public CollisionsLevelModifier(KineticsLevelModifier kinetics) {
    this.kinetics = kinetics;
  }
  
  /**
   * Detect collisions in level.
   * 
   * @param level
   *          The level.
   * @param delta
   *          The steps passed since this method wat last executed.
   */
  public void modify(Level level, double delta) {
    detectPlayer(level, delta);
  }

  /**
   * Detect collisions between player and other LevelElements.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public void detectPlayer(Level level, double delta) {
    Player player = level.getPlayer();

    // Find platform collidee candidates.
    ArrayList<Platform> candidates = new ArrayList<Platform>();

    for (Platform platform : level.getPlatforms()) {
      double dist = player.distance(platform);
      if (dist <= Constants.COLLISION_RADIUS) {
        candidates.add(platform);
      }
    }

    // Detect collisions with candidates.
    for (Platform platform : candidates) {
      Collision collision = new Collision(player, platform, delta);
      if (collision.collidingFromTop()) {
        kinetics.stopVertically(player);
        kinetics.snapTop(player, platform);
      }
    }
  }

}
