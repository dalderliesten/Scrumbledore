package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * PyroPepper is a power-up that gives the Player object 1 fireball projectile to shoot.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class PyroPepper implements Powerup {
  private Vector position;
  private Vector size;
  private Vector speed;
  private Vector friction;
  private boolean gravity;
  private ArrayList<LevelElementAction> actions;
  private LevelElementAction lastMove;
  private Boolean firing;
  private Boolean alive;
  private int id;

  /**
   * Create a new LevelElement instance.
   * 
   * @param position
   *          Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public PyroPepper(Vector position, Vector size) {
    this.position = position;
    this.size = size;
    this.speed = new Vector(0, 0);
    this.friction = new Vector(0, 0);
    setGravity(true);

    id = 0;
    actions = new ArrayList<LevelElementAction>();
    lastMove = LevelElementAction.MoveRight;
    firing = false;
    alive = true;
  }

  /**
   * Get the position vector of this element.
   * 
   * @return Position Vector.
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * Get the X coordinate of the element.
   * 
   * @return double
   */
  public double posX() {
    return position.getX();
  }

  /**
   * Get the Y coordinate of the element.
   * 
   * @return double
   */
  public double posY() {
    return position.getY();
  }

  /**
   * Get the size vector of this element.
   * 
   * @return Size Vector.
   */
  public Vector getSize() {
    return size;
  }

  /**
   * Get the width of the element.
   * 
   * @return double
   */
  public double width() {
    return size.getX();
  }

  /**
   * Get the height of the element.
   * 
   * @return double
   */
  public double height() {
    return size.getY();
  }

  /**
   * Get the speed vector of this element.
   * 
   * @return Speed Vector.
   */
  public Vector getSpeed() {
    return speed;
  }

  /**
   * Get the horizontal speed of the element.
   * 
   * @return double
   */
  public double hSpeed() {
    return speed.getX();
  }

  /**
   * Get the vertical speed of the element.
   * 
   * @return double
   */
  public double vSpeed() {
    return speed.getY();
  }

  /**
   * Get the friction vector of this element.
   * 
   * @return Friction Vector.
   */
  public Vector getFriction() {
    return friction;
  }

  /**
   * Get the horizontal friction.
   * 
   * @return Horizontal friction.
   */
  public double hFric() {
    return friction.getX();
  }

  /**
   * Get the vertical friction.
   * 
   * @return Vertical friction.
   */
  public double vFric() {
    return friction.getY();
  }

  /**
   * Stop this LevelElement's vertical movement.
   */
  public void stopVertically() {
    getSpeed().setY(0);
  }

  /**
   * Stop this LevelElement's horizontal movement.
   */
  public void stopHorizontally() {
    getSpeed().setX(0);
  }

  /**
   * Check whether this LevelElement is affected by Gravity.
   * 
   * @return Boolean
   */
  public boolean hasGravity() {
    return gravity;
  }

  /**
   * Set the property determining whether this LevelElement is affected by gravity.
   * 
   * @param gravity
   *          A boolean
   */
  public void setGravity(boolean gravity) {
    this.gravity = gravity;
  }

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  public double getTop() {
    return position.getY() - size.getY() / 2;
  }

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  public double getBottom() {
    return position.getY() + size.getY() / 2;
  }

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  public double getLeft() {
    return position.getX() - size.getX() / 2;
  }

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  public double getRight() {
    return position.getX() + size.getX() / 2;
  }

  /**
   * Get the distance to another LevelElement.
   * 
   * @param other
   *          The other element to measure the distance to.
   * @return The distance.
   */
  public double distance(LevelElement other) {
    return getPosition().distance(other.getPosition());
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
    return distance(other) <= range;
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
    boolean inX = (other.posX() >= posX() - range && other.posX() <= posX() + range);
    boolean inY = (other.posY() >= posY() - range && other.posY() <= posY() + range);
    return inX && inY;
  }

  /**
   * Snap a LevelElement to the left side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapLeft(LevelElement other) {
    double offset = getSize().getX() / 2;
    double newPos = other.getLeft() - offset;
    getPosition().setX(newPos);
  }

  /**
   * Snap a LevelElement to the right side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapRight(LevelElement other) {
    double offset = getSize().getX() / 2;
    double newPos = other.getRight() + offset;
    getPosition().setX(newPos);
  }

  /**
   * Snap a LevelElement to the top side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapTop(LevelElement other) {
    double offset = getSize().getY() / 2;
    double newPos = other.getTop() - offset;
    getPosition().setY(newPos);
  }

  /**
   * Snap a LevelElement to the bottom side of another LevelElement.
   * 
   * @param other
   *          The LevelElement to be snapped to.
   */
  public void snapBottom(LevelElement other) {
    double offset = getSize().getY() / 2;
    double newPos = other.getBottom() + offset;
    getPosition().setY(newPos);
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  public void addAction(LevelElementAction action) {
    if (!hasAction(action)) {
      actions.add(action);
      setLastMove(action);
    }
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
    actions.clear();
  }

  /**
   * Checking wether the player is alive.
   * 
   * @return The boolean if the player is alive.
   */
  public Boolean isAlive() {
    return alive;
  }

  /**
   * Setting the life of the player.
   * 
   * @param bool
   *          Can be True or False, stated on situation of player.
   */
  public void setAlive(Boolean bool) {
    alive = bool;
  }

  /**
   * Gets the id of the current player.
   * 
   * @return Integer that represents the players number in the game.
   */
  public int getPlayerNumber() {
    return id;
  }

  /**
   * Sets the id of the current player.
   * 
   * @param id
   *          Integer that represents the players number in the game.
   */
  public void setPlayerNumber(int id) {
    this.id = id;
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
   */
  public void removeAction(LevelElementAction action) {
    actions.remove(action);
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
    if (action == LevelElementAction.MoveLeft || action == LevelElementAction.MoveRight) {
      lastMove = action;
    }
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Player) {
      Player that = (Player) other;
      return this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize());
    }

    return false;
  }

  /**
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  public Boolean isFiring() {
    return firing;
  }

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  public void setFiring(Boolean isFiring) {
    this.firing = isFiring;
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
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    SpriteStore store = SpriteStore.getInstance();
    if (alive) {
      boolean toRight = getLastMove() == LevelElementAction.MoveRight;

      String id = "move-left";
      if (firing && toRight) {
        id = "shoot-right";
      } else if (firing) {
        id = "shoot-left";
      } else if (toRight) {
        id = "move-right";
      }
      if (getSpeed().getX() == 0 && !firing) {
        steps = 0;
      }

      id = "player-" + Constants.PLAYER_COLORS.get(getPlayerNumber()) + "-" + id;

      result.add(store.getAnimated(id).getFrame(steps));
    }
    return result;
  }

}