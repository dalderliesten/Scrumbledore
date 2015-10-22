package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;

/**
 * LevelModifier responsible for warping LevelElements around the edges of the Level. (This used to
 * be a responsibility of the KineticsLevelModifier.)
 * 
 * @author Jesse Tilro
 *
 */
public class WarpLevelModifier implements LevelModifier {

  /**
   * Warp all dynamic element in a given Level.
   * 
   * @param level
   *          The level to be modified.
   * @param delta
   *          The number of steps passed since the last cycle.
   */
  public void modify(Level level, double delta) {
    for (LevelElement element : level.getDynamicElements()) {
      warp(element);
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
