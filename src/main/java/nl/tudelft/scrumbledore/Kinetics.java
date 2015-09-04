package nl.tudelft.scrumbledore;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * @author Floris Doolaard
 *
 */
public class Kinetics {
  
  /**
   * Empty Kinetics contructor.
   */
  public Kinetics() {
  }
  
  /**
   * Sets the speed of the LevelElement object to be continued on each tick.
   * @param el
   *      The element where the speed has to be set.
   */
  public static void addSpeed(LevelElement el) {
    el.getPosition().sum(el.getSpeed());
  }
  
}
