package nl.tudelft.scrumbledore.level;

import nl.tudelft.scrumbledore.Constants;

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
   * Construct a new Gravity Level Modifier using the constants for strength and max.
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
    // Pull down NPCs
    for (NPC element : level.getNPCs()) {
      pull(element, d);
    }

    // Pull down Fruits
    for (Fruit element : level.getFruits()) {
      pull(element, d);
    }

    // Pull down the player
    for (Player player : level.getPlayers()) {
      pull(player, d);
    }
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
    if (element == null) {
      return;
    }

    if (!element.hasGravity()) {
      return;
    }

    double vspeed = element.vSpeed();
    double update = strength * d;

    if ((max - vspeed) >= update) {
      element.getSpeed().setY(vspeed + update);
    } else {
      element.getSpeed().setY(max);
    }
  }

  /**
   * Return the strength of the gravity.
   * 
   * @return strength
   *           The strength of the gravity.
   */
  public double getStrength() {
    return strength;
  }

  /**
   * Return the maximum strength of the gravity.
   * 
   * @return max
   *           The maximum strength of the gravity.
   */
  public double getMax() {
    return max;
  }

}
