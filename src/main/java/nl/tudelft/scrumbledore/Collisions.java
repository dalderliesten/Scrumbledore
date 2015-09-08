package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 *
 */
public class Collisions {

  /**
   * Detect collisions between player and other LevelElements.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public static void detectPlayer(Level level, double delta) {
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
        Kinetics.stopVertically(player);
        Kinetics.snapTop(player, platform);
      }
    }
  }

}
