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

    // Add speed to player
    addSpeed(level.getPlayer(), d);
  }
  
  /**
   * Stop a LevelElement's vertical movement.
   * @param element The element.
   */
  public static void stopVertically(LevelElement element) {
    element.getSpeed().setY(0);
  }
  
  /**
   * Stop a LevelElement's horizontal movement.
   * @param element The element.
   */
  public static void stopHorizontally(LevelElement element) {
    element.getSpeed().setX(0);
  }

  /**
   * Snap a LevelElement to the left side of another LevelElement.
   * 
   * @param snapper
   *          The LevelElement to be snapped.
   * @param snapTo
   *          The LevelElement to be snapped to.
   */
  public static void snapLeft(LevelElement snapper, LevelElement snapTo) {
    double offset = snapper.getSize().getX() / 2;
    double newPos = snapTo.getLeft() - offset;
    snapper.getPosition().setX(newPos);
  }
  
  /**
   * Snap a LevelElement to the right side of another LevelElement.
   * 
   * @param snapper
   *          The LevelElement to be snapped.
   * @param snapTo
   *          The LevelElement to be snapped to.
   */
  public static void snapRight(LevelElement snapper, LevelElement snapTo) {
    double offset = snapper.getSize().getX() / 2;
    double newPos = snapTo.getRight() + offset;
    snapper.getPosition().setX(newPos);
  }
  
  /**
   * Snap a LevelElement to the top side of another LevelElement.
   * 
   * @param snapper
   *          The LevelElement to be snapped.
   * @param snapTo
   *          The LevelElement to be snapped to.
   */
  public static void snapTop(LevelElement snapper, LevelElement snapTo) {
    double offset = snapper.getSize().getY() / 2;
    double newPos = snapTo.getTop() - offset;
    snapper.getPosition().setY(newPos);
  }
  
  /**
   * Snap a LevelElement to the bottom side of another LevelElement.
   * 
   * @param snapper
   *          The LevelElement to be snapped.
   * @param snapTo
   *          The LevelElement to be snapped to.
   */
  public static void snapBottom(LevelElement snapper, LevelElement snapTo) {
    double offset = snapper.getSize().getY() / 2;
    double newPos = snapTo.getBottom() + offset;
    snapper.getPosition().setY(newPos);
  }
}
