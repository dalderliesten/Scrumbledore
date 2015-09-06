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
    // Only add speed if an object has been initialized.
    if (el != null) {
      el.getPosition().sum(el.getSpeed());   
    }
  }

  /**
   * Update all elements in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   */
  public void update(Level level) {
    // Add speed to generic moving elements
    for (LevelElement el : level.getMovingElements()) {
      addSpeed(el);
    }
    
    // Add speed to player
    addSpeed(level.getPlayer());
  }
}
