package nl.tudelft.scrumbledore;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * 
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
   * Update the position of the LevelElement by adding the speed.
   * 
   * @param el
   *          The element whose position has to be updated with its speed.
   */
  public void addSpeed(LevelElement el) {
    el.getPosition().sum(el.getSpeed());
  }
  
  pu

}
