package nl.tudelft.scrumbledore;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * 
 * @author Floris Doolaard
 *
 */
public final class Kinetics {
  private Kinetics() {

  }

  /**
   * Update the position of the LevelElement by adding the speed.
   * 
   * @param el
   *          The element whose position has to be updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void addSpeed(LevelElement el, double d) {
    // Only add speed if an object has been initialized.
    if (el != null) {
      el.getPosition().sum(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Reverse update the position of the LevelElement by removing the speed.
   * 
   * @param el
   *          The element whose position has to be reverse updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void removeSpeed(LevelElement el, double d) {
    // Only add speed if an object has been initialized.
    if (el != null) {
      el.getPosition().difference(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Update all elements in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void update(Level level, double d) {
    // Add speed to generic moving elements
    for (LevelElement el : level.getMovingElements()) {
      addSpeed(el, d);
    }

    Player player = level.getPlayer();
    
    // Add speed to player
    addSpeed(player, d);
    
    if (player.posY() + player.height() >= Constants.LEVELY) {
      player.getPosition().setY(player.height() / 2);
    }
  }
}
