package nl.tudelft.scrumbledore.level.modifier;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.DynamicElement;
import nl.tudelft.scrumbledore.level.element.LevelElement;

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
   * 
   * @param d
   *          The number of steps since last executing this function.
   */
  public void modify(Level level, double d) {
    ArrayList<LevelElement> elements = level.getDynamicElements();
    for (int i = 0; i < elements.size(); i++) {
      DynamicElement element = (DynamicElement) elements.get(i);
      move(element, d);
      applyFriction(element, d);
    }
  }

  /**
   * Apply friction on a given LevelElement based on its Friction Vector. If an entry in the speed
   * vector is smaller than the corresponding entry in the friction vector, it is set to zero.
   * Otherwise, the friction entry is subtracted from the speed.
   * 
   * @param el
   *          A LevelElement.
   * 
   * @param d
   *          The number of steps passed since this method was last called.
   */
  public void applyFriction(DynamicElement el, double d) {
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
   * 
   * @param d
   *          The number of steps since last executing this function.
   */
  public void move(DynamicElement el, double d) {
    if (el != null) {
      el.getPosition().sum(Vector.scale(el.getSpeed(), d));
    }
  }

  /**
   * Reverse update the position of the LevelElement by removing the speed vector.
   * 
   * @param el
   *          The element whose position has to be reverse updated with its speed.
   * 
   * @param d
   *          The number of steps since last executing this function.
   */
  public void revertMove(DynamicElement el, double d) {
    if (el != null) {
      el.getPosition().difference(Vector.scale(el.getSpeed(), d));
    }
  }

}
