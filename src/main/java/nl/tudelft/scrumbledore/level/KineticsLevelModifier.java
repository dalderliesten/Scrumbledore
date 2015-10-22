package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;

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
    for (LevelElement element : level.getDynamicElements()) {
      move(element, d);
      applyFriction(element, d);
      warp(element);
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
      el.stopHorizontally();
    }
    if (Math.abs(el.vSpeed()) > el.vFric() * d) {
      signY = (int) Math.signum(el.vSpeed());
    } else {
      el.stopVertically();
    }

    Vector fricDiff = new Vector(d * signX * el.hFric(), d * signY * el.vFric());
    el.getSpeed().difference(fricDiff);
  }

  /**
   * Move a LevelElement by updating its position vector.
   * 
   * @param el
   *          The element whose position has to be updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void move(LevelElement el, double d) {
    if (el != null) {
      el.getPosition().sum(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Reverse update the position of the LevelElement by removing the speed vector.
   * 
   * @param el
   *          The element whose position has to be reverse updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void revertMove(LevelElement el, double d) {
    if (el != null) {
      el.getPosition().difference(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Warp a LeveElement both in horizontal and vertical direction.
   * 
   * @param element
   *          The LevelElement to be warped.
   */
  public void warp(LevelElement element) {
    warpVertically(element);
    warpHorizontally(element);
  }

  /**
   * Warp a Level Element through the vertical boundaries of the level.
   * 
   * @param element
   *          The Level Element to be warped.
   */
  public void warpVertically(LevelElement element) {
    double offset = element.height() / 2;
    if (element.posY() < -offset) {
      element.getPosition().setY(Constants.LEVELY + offset);
    } else if (element.posY() > Constants.LEVELY + offset) {
      element.getPosition().setY(-offset);
    }
  }

  /**
   * Warp a Level Element through the horizontal boundaries of the level.
   * 
   * @param element
   *          The Level Element to be warped.
   */
  public void warpHorizontally(LevelElement element) {
    double offset = -element.width() / 2;
    if (element.posX() < -offset) {
      element.getPosition().setX(Constants.LEVELX + offset);
    } else if (element.posX() > Constants.LEVELX + offset) {
      element.getPosition().setX(-offset);
    }
  }

}
