package nl.tudelft.scrumbledore.level.element;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Vector;

/**
 * The interface of powerup objects and dynamic elements.
 * This way dynamic elements an powerups are replacable.
 * @author Floris Doolaard
 *
 */
public interface DynamicElement extends LevelElement {
  
  /**
   * Stop this LevelElement's vertical movement.
   */
  void stopVertically();

  /**
   * Stop this LevelElement's horizontal movement.
   */
  void stopHorizontally();
  
  /**
   * Gives a list of current actions of the player.
   * @return a list of actions
   */
  ArrayList<LevelElementAction> getActions();

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  void addAction(LevelElementAction action);

  /**
   * Remove all actions from the queue.
   */
  void clearActions();

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  boolean hasAction(LevelElementAction action);

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
   */
  void removeAction(LevelElementAction action);

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  LevelElementAction getLastMove();

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  void setLastMove(LevelElementAction action);

  @Override
  int hashCode();

  @Override
  boolean equals(Object other);
  
  /**
   * Check whether this LevelElement is affected by Gravity.
   * 
   * @return Boolean
   */
  boolean hasGravity();

  /**
   * Set the property determining whether this LevelElement is affected by gravity.
   * 
   * @param gravity
   *          A boolean
   */
  void setGravity(boolean gravity);
  
  /**
   * Get the speed vector of this element.
   * 
   * @return Speed Vector.
   */
  Vector getSpeed();

  /**
   * Get the horizontal speed of the element.
   * 
   * @return double
   */
  double hSpeed();

  /**
   * Get the vertical speed of the element.
   * 
   * @return double
   */
  double vSpeed();

  /**
   * Get the friction vector of this element.
   * 
   * @return Friction Vector.
   */
  Vector getFriction();

  /**
   * Get the horizontal friction.
   * 
   * @return Horizontal friction.
   */
  double hFric();

  /**
   * Get the vertical friction.
   * 
   * @return Vertical friction.
   */
  double vFric();

  /**
   * Snap a LevelElement to the left side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  void snapLeft(LevelElement other);

  /**
   * Snap a LevelElement to the right side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  void snapRight(LevelElement other);

  /**
   * Snap a LevelElement to the top side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  void snapTop(LevelElement other);

  /**
   * Snap a LevelElement to the bottom side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  void snapBottom(LevelElement other);
}
