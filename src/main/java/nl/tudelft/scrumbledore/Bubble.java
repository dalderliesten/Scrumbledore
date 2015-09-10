package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * This class creates a Bubble object 
 * that the player can shoot.
 * @author Floris Doolaard
 *
 */
public class Bubble extends Projectile {
  private ArrayList<BubbleAction> actions;

  /**
   * The Bubble constructor creates a new Bubble instance.
   * @param position
   *           Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public Bubble(Vector position, Vector size) {
    super(position, size);
    
    actions = new ArrayList<BubbleAction>();
  }
  
  public void addAction(BubbleAction action){
    if (action == BubbleAction.MoveLeft) {
      actions.clear();
      actions.add(action);
    } else {
      actions.clear();
      actions.add(action);
    }
  }
  
  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A BubbleAction.
   * @return Boolean.
   */
  public boolean hasAction(BubbleAction action) {
    return actions.contains(action);
  }
}
