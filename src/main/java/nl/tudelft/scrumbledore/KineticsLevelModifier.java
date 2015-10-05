package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * 
 * @author Floris Doolaard
 * @author David Alderliesten
 */
@SuppressWarnings("PMD.TooManyMethods")
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
    updateNPC(level, d);
    updateFruit(level, d);
    updatePlayer(level, d);
    updateBubble(level, d);
  }

  /**
   * Update the Fruits in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  private void updateFruit(Level level, double d) {
    for (Fruit fruit : level.getFruits()) {
      addSpeed(fruit, d);
      warpVertically(fruit);
    }
  }

  /**
   * Update the NPCs in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  private void updateNPC(Level level, double d) {
    for (NPC npc : level.getNPCs()) {
      addSpeed(npc, d);
      warpVertically(npc);
    }
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
    ArrayList<Player> players = level.getPlayers();
    for (Player player : players) {
      addSpeed(player, d);
      warpVertically(player);

      if (Constants.LOGGING_WANTMOVEMENT) {
        // Logging the movement of the player within the level to the session log.
        Logger.log(
            "Player moved to " + player.getPosition().getX() + ", " + player.getPosition().getY());
      }
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
    // Copy bubbles to prevent a race condition when many bubbles are shot rapidly
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    for (Bubble bubble : level.getBubbles()) {
      bubbles.add(bubble);
    }

    for (Bubble bubble : bubbles) {
      addSpeed(bubble, d);
      applyFriction(bubble, d);
      warpVertically(bubble);
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
    if (Math.abs(el.hSpeed()) > el.hFric() * d) {
      signX = (int) Math.signum(el.hSpeed());
    } else {
      stopHorizontally(el);
    }
    if (Math.abs(el.vSpeed()) > el.vFric() * d) {
      signY = (int) Math.signum(el.vSpeed());
    } else {
      stopVertically(el);
    }

    Vector fricDiff = new Vector(d * signX * el.hFric(), d * signY * el.vFric());
    el.getSpeed().difference(fricDiff);
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
   * Warp a Level Element through the vertical boundaries of the level.
   * 
   * @param element
   *          The Level Element to be warped.
   */
  public void warpVertically(LevelElement element) {
    double offset = element.width() / 2;
    if (element.posY() < -offset) {
      element.getPosition().setY(Constants.LEVELY + offset);
    } else if (element.posY() > Constants.LEVELY + offset) {
      element.getPosition().setY(-offset);
    }
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
