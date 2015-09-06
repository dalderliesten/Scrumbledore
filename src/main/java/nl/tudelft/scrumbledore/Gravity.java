package nl.tudelft.scrumbledore;

/**
 * Class simulating Gravity on the LevelElements in the game.
 * 
 * @author Jesse Tilro
 *
 */
public final class Gravity {

  private static int strength;
  private static int max;

  private Gravity() {

  }

  /**
   * @return Strength of the gravity.
   */
  public static int getStrength() {
    return strength;
  }

  /**
   * Sets the strength of the gravity.
   * 
   * @param strength
   *          Strength of the gravity.
   */
  public static void setStrength(int strength) {
    Gravity.strength = strength;
  }

  /**
   * @return The maximum gravity.
   */
  public static int getMax() {
    return max;
  }

  /**
   * Sets the maximum gravity.
   * 
   * @param max
   *          Maximum gravity.
   */
  public static void setMax(int max) {
    Gravity.max = max;
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
  public static void pull(LevelElement element, double d) {
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

  /**
   * Pull down all elements in a given Level.
   * 
   * @param level
   *          A Level containing elements to be pulled down.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void pull(Level level, double d) {
    for (LevelElement element : level.getElements()) {
      pull(element, d);
    }
  }

}
