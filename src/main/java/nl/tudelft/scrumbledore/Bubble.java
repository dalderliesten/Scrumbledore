package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * This class creates a Bubble object that the player can shoot.
 * 
 * @author Floris Doolaard
 *
 */
public class Bubble extends Projectile {
  private ArrayList<BubbleAction> actions;
  private Boolean hasNPC;

  private double lifetime;

  /**
   * The Bubble constructor creates a new Bubble instance.
   * 
   * @param position
   *          Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public Bubble(Vector position, Vector size) {
    super(position, size);
    getFriction().setX(Constants.BUBBLE_FRICTION);
    lifetime = Constants.BUBBLE_LIFETIME;
    actions = new ArrayList<BubbleAction>();
  }

  /**
   * Add an action the Bubble has to perform.
   * 
   * @param action
   *          The action (MoveLeft or MoveRight) the Bubble has to perform.
   */
  public void addAction(BubbleAction action) {
    actions.clear();
    actions.add(action);
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

  /**
   * Clear all queued actions for this Bubble.
   */
  public void clearActions() {
    actions.clear();
  }

  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  public void decreaseLifetime(double delta) {
    lifetime -= delta;
  }

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  public double getLifetime() {
    return lifetime;
  }
  
  /**
   * Return a boolean wether to see if a bubble has an NPC in it.
   * @return Boolean of hasNPC.
   */
  public Boolean hasNPC() {
    return hasNPC;
  }
  
  /**
   * Setting a boolean to hasNPC.
   * @param bool The boolean that hasNPC has to be.
   */
  public void setHasNPC(Boolean bool) {
    hasNPC = bool;
  }
}
