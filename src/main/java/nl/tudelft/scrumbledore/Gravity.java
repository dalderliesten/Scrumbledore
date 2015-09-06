package nl.tudelft.scrumbledore;

/**
 * Class simulating Gravity on the LevelElements in the game.
 * 
 * @author Jesse Tilro
 *
 */
public class Gravity {

  private int strength;
  private int max;

  /**
   * Construct a new Gravity simulator.
   * 
   * @param strength
   *          The strength with which the instance will pull down LevelElements, describing how much
   *          the vertical speed should be incremented each step.
   * @param max
   *          The maximal vertical speed a LevelElement can be pulled to.
   */
  public Gravity(int strength, int max) {
    this.strength = strength;
    this.max = max;
  }

  /**
   * Pull down a LevelElement that is affected by Gravity by incrementing its vertical speed if it
   * has not yet reached the maximal vertical speed.
   * 
   * @param element
   *          A LevelElement
   */
  public void pull(LevelElement element) {
    // If the object has not yet been initialized, return.
    if (element == null) {
      return;
    }
    
    // If the element is not affected by Gravity, ignore it.
    if (!element.hasGravity()) {
      return;
    }

    int vspeed = element.getSpeed().getY();
    // If the element has not yet reached the maximal falling speed and the difference is still
    // larger than or equal than the strength, increment it. Otherwise, set the vertical speed to
    // maximal.
    if ((max - vspeed) >= strength) {
      element.getSpeed().setY(vspeed + strength);
    } else {
      element.getSpeed().setY(max);
    }
  }

  /**
   * Pull down all elements in a given Level.
   * 
   * @param level
   *          A Level containing elements to be pulled down.
   */
  public void pull(Level level) {
    // Pull down generic moving elements

    for (LevelElement element : level.getMovingElements()) {
      pull(element);
    }
    
    // Pull down the player
    pull(level.getPlayer());
  }

}
