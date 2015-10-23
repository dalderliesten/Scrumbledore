package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.DynamicElement;
import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * ChiliChicken is a power-up that gives a Player object more speed for 5 seconds.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class ChiliChicken implements Powerup {

  private DynamicElement wrapped;

  /**
   * Create a new LevelElement instance.
   * 
   * @param wrapped
   *          The DynamicElement to be wrapped in this Powerup Decorator.
   */
  public ChiliChicken(DynamicElement wrapped) {
    this.wrapped = wrapped;
  }

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   * @return Sprites to be drawn.
   */
  public ArrayList<Sprite> getSprites(double steps) {
    ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    SpriteStore store = SpriteStore.getInstance();
    sprites.add(store.getAnimated("fire-yellow").getFrame(steps));
    sprites.addAll(wrapped.getSprites(steps));
    return sprites;
  }

  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  public void decreaseLifetime(double delta) {
    wrapped.decreaseLifetime(delta * Constants.CHILI_LIFETIME_MULTIPLIER);
  }

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  public double getLifetime() {
    return wrapped.getLifetime();
  }

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  public void setLifetime(double newTime) {
    wrapped.setLifetime(newTime);
  }

  /**
   * Get the position vector of this element.
   * 
   * @return Position Vector.
   */
  public Vector getPosition() {
    return wrapped.getPosition();
  }

  /**
   * Get the X coordinate of the element.
   * 
   * @return double
   */
  public double posX() {
    return wrapped.posX();
  }

  /**
   * Get the Y coordinate of the element.
   * 
   * @return double
   */
  public double posY() {
    return wrapped.posY();
  }

  /**
   * Get the size vector of this element.
   * 
   * @return Size Vector.
   */
  public Vector getSize() {
    return wrapped.getSize();
  }

  /**
   * Get the width of the element.
   * 
   * @return double
   */
  public double width() {
    return wrapped.width();
  }

  /**
   * Get the height of the element.
   * 
   * @return double
   */
  public double height() {
    return wrapped.height();
  }

  /**
   * Get the speed vector of this element.
   * 
   * @return Speed Vector.
   */
  public Vector getSpeed() {
    return wrapped.getSpeed();
  }

  /**
   * Get the horizontal speed of the element.
   * 
   * @return double
   */
  public double hSpeed() {
    return wrapped.hSpeed();
  }

  /**
   * Get the vertical speed of the element.
   * 
   * @return double
   */
  public double vSpeed() {
    return wrapped.vSpeed();
  }

  /**
   * Get the friction vector of this element.
   * 
   * @return Friction Vector.
   */
  public Vector getFriction() {
    return wrapped.getFriction();
  }

  /**
   * Get the horizontal friction.
   * 
   * @return Horizontal friction.
   */
  public double hFric() {
    return wrapped.hFric();
  }

  /**
   * Get the vertical friction.
   * 
   * @return Vertical friction.
   */
  public double vFric() {
    return wrapped.vFric();
  }

  /**
   * Stop this LevelElement's vertical movement.
   */
  public void stopVertically() {
    wrapped.stopVertically();
  }

  /**
   * Stop this LevelElement's horizontal movement.
   */
  public void stopHorizontally() {
    wrapped.stopHorizontally();
  }

  /**
   * Check whether this LevelElement is affected by Gravity.
   * 
   * @return Boolean
   */
  public boolean hasGravity() {
    return wrapped.hasGravity();
  }

  /**
   * Set the property determining whether this LevelElement is affected by gravity.
   * 
   * @param gravity
   *          A boolean
   */
  public void setGravity(boolean gravity) {
    wrapped.setGravity(gravity);
  }

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  public double getTop() {
    return wrapped.getTop();
  }

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  public double getBottom() {
    return wrapped.getBottom();
  }

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  public double getLeft() {
    return wrapped.getLeft();
  }

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  public double getRight() {
    return wrapped.getRight();
  }

  /**
   * Get the distance to another LevelElement.
   * 
   * @param other
   *          The other element to measure the distance to.
   * @return The distance.
   */
  public double distance(LevelElement other) {
    return wrapped.distance(other);
  }

  /**
   * Check whether another element is within range of this element using a circular radius by
   * computing the distance.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (of the circle).
   * @return A boolean.
   */
  public boolean inRadiusRangeOf(LevelElement other, double range) {
    return wrapped.inBoxRangeOf(other, range);
  }

  /**
   * Check whether another element is within range of this element using a box. The box is a square
   * axis-aligned bounding box, with dimensions of twice the given range. It has the position of
   * this element as its center.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (a half of the dimensions of the square box).
   * @return A boolean.
   */
  public boolean inBoxRangeOf(LevelElement other, double range) {
    return wrapped.inBoxRangeOf(other, range);
  }

  /**
   * Snap a LevelElement to the left side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapLeft(LevelElement other) {
    wrapped.snapLeft(other);
  }

  /**
   * Snap a LevelElement to the right side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapRight(LevelElement other) {
    wrapped.snapRight(other);
  }

  /**
   * Snap a LevelElement to the top side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapTop(LevelElement other) {
    wrapped.snapTop(other);
  }

  /**
   * Snap a LevelElement to the bottom side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapBottom(LevelElement other) {
    wrapped.snapBottom(other);
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  public void addAction(LevelElementAction action) {
    wrapped.addAction(action);
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
    wrapped.clearActions();
  }

  /**
   * Checking whether the player is alive.
   * 
   * @return The boolean if the player is alive.
   */
  public Boolean isAlive() {
    return wrapped.isAlive();
  }

  /**
   * Setting the life of the player.
   * 
   * @param bool
   *          Can be True or False, stated on situation of player.
   */
  public void setAlive(Boolean bool) {
    wrapped.setAlive(bool);
  }

  /**
   * Gets the id of the current player.
   * 
   * @return Integer that represents the players number in the game.
   */
  public int getPlayerNumber() {
    return wrapped.getPlayerNumber();
  }

  /**
   * Sets the id of the current player.
   * 
   * @param id
   *          Integer that represents the players number in the game.
   */
  public void setPlayerNumber(int id) {
    wrapped.setPlayerNumber(id);
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return wrapped.hasAction(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
   */
  public void removeAction(LevelElementAction action) {
    wrapped.removeAction(action);
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return wrapped.getLastMove();
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
    wrapped.setLastMove(action);
  }

  @Override
  public int hashCode() {
    return wrapped.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return wrapped.equals(other);
  }

  /**
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  public Boolean isFiring() {
    return wrapped.isFiring();
  }

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  public void setFiring(Boolean isFiring) {
    wrapped.setFiring(isFiring);
  }

  /**
   * Gives a list of current actions of the player.
   * 
   * @return a list of actions
   */
  public ArrayList<LevelElementAction> getActions() {
    return wrapped.getActions();
  }

}
