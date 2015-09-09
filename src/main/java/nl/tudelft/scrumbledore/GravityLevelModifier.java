package nl.tudelft.scrumbledore;

/**
 * Class simulating Gravity on the LevelElements in the game.
 * 
 * @author Jesse Tilro
 *
 */
public class GravityLevelModifier implements LevelModifier {

  private double strength;
  private double max;

  /**
   * Constructs a new Gravity Level Modifier using a given strength and max.
   * 
   * @param strength
   *          The strength.
   * @param max
   *          The maximal vertical speed it may accelerate elements to.
   */
  public GravityLevelModifier(double strength, double max) {
    this.strength = strength;
    this.max = max;
  }
  
  /**
   * Construct a new Gravity Level Modifier using the constants for strenght and max.
   */
  public GravityLevelModifier() {
    this.strength = Constants.GRAVITY_STRENGTH;
    this.max = Constants.GRAVITY_MAX;
  }

  /**
   * Pull down all elements in a given Level.
   * 
   * @param level
   *          A Level containing elements to be pulled down.
   * @param d
   *          The number of steps since last executing this function.
   */
  public void modify(Level level, double d) {
    // Pull down generic moving elements
    for (LevelElement element : level.getMovingElements()) {
      pull(element, d);
    }

    // Pull down the player
    pull(level.getPlayer(), d);
  }

  /**
   * Pull down a LevelElement that is affected by Gravity by incrementing its vertical speed if it
   * has not yet reached the maximal vertical speed.
   * 
   * @param element
   *          A LevelElement
   * @param d
   *          The number of steps since last executing this function.
   */
  public void pull(LevelElement element, double d) {
    // If the object has not yet been initialized, return.
    if (element == null) {
      return;
    }

    // If the element is not affected by Gravity, ignore it.
    if (!element.hasGravity()) {
      return;
    }

    double vspeed = element.getSpeed().getY() * d;
    // If the element has not yet reached the maximal falling speed and the difference is still
    // larger than or equal than the strength, increment it. Otherwise, set the vertical speed to
    // maximal.
    if ((max - vspeed) >= strength) {
      element.getSpeed().setY(vspeed + strength);
    } else {
      element.getSpeed().setY(max);
    }
  }

}
