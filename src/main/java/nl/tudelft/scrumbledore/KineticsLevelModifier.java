package nl.tudelft.scrumbledore;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * 
 * @author Floris Doolaard
 *
 */
public class KineticsLevelModifier implements LevelModifier {

  /**
   * Update all elements in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void modify(Level level, double d) {
    // Add speed to generic moving elements
    for (LevelElement el : level.getMovingElements()) {
      addSpeed(el, d);
    }

    updatePlayer(level, d);
    updateBubble(level, d);
  }

  /**
   * Update the player in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  private void updatePlayer(Level level, double d) {
    Player player = level.getPlayer();

    addSpeed(player, d);

    if (player.posY() + player.height() >= Constants.LEVELY) {
      player.getPosition().setY(player.height() / -2);
    }
  }

  /**
   * Update the speed/position of the bubble in a given level.
   * 
   * @param level
   *          The level whose Bubble objects should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  private void updateBubble(Level level, double d) {
    for (Bubble bubble : level.getBubbles()) {
      addSpeed(bubble, d);
      applyFriction(bubble, d);
    }
  }

  /**
   * Update the position of the LevelElement by adding the speed.
   * 
   * @param el
   *          The element whose position has to be updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void addSpeed(LevelElement el, double d) {
    // Only add speed if an object has been initialized.
    if (el != null) {
      el.getPosition().sum(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Apply friction on a given LevelElement based on its Friction Vector. If an entry in the speed
   * vector is smaller than the corresponding entry in the friction vector, it is set to zero.
   * Otherwise, the friction entry is subtracted from the speed.
   * 
   * @param el
   *          A LevelElement
   * @param d
   *          The number of steps passed since this method was last called.
   */
  public void applyFriction(LevelElement el, double d) {
    int signX = 0;
    int signY = 0;
    if (Math.abs(el.hSpeed()) > el.hFric()) {
      signX = (int) Math.signum(el.hSpeed());
    } else {
      stopHorizontally(el);
    }
    if (Math.abs(el.vSpeed()) > el.vFric()) {
      signY = (int) Math.signum(el.vSpeed());
    } else {
      stopVertically(el);
    }

    Vector fricDiff = new Vector(d * signX * el.hFric(), d * signY * el.vFric());
    el.getSpeed().difference(fricDiff);
  }

  /**
   * Reverse update the position of the LevelElement by removing the speed.
   * 
   * @param el
   *          The element whose position has to be reverse updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void removeSpeed(LevelElement el, double d) {
    // Only add speed if an object has been initialized.
    if (el != null) {
      el.getPosition().difference(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Stop a LevelElement's vertical movement.
   * 
   * @param element
   *          The element.
   */
  public void stopVertically(LevelElement element) {
    element.getSpeed().setY(0);
  }

  /**
   * Stop a LevelElement's horizontal movement.
   * 
   * @param element
   *          The element.
   */
  public void stopHorizontally(LevelElement element) {
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
  public void snapLeft(LevelElement snapper, LevelElement snapTo) {
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
  public void snapRight(LevelElement snapper, LevelElement snapTo) {
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
  public void snapTop(LevelElement snapper, LevelElement snapTo) {
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
  public void snapBottom(LevelElement snapper, LevelElement snapTo) {
    double offset = snapper.getSize().getY() / 2;
    double newPos = snapTo.getBottom() + offset;
    snapper.getPosition().setY(newPos);
  }
}
