package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class representing a Player in a game.
 * 
 * @author Niels Warnars
 * @author Jesse Tilro
 * @author David Alderliesten
 * @author Floris Doolaard
 */
public class Player extends BasicDynamicElement {
  private ArrayList<LevelElementAction> actions;
  private LevelElementAction lastMove;
  private Boolean firing;
  private Boolean alive;
  private int id;

  /**
   * Create a new Player instance.
   * 
   * @param position
   *          Position of the player in the level.
   * @param size
   *          Size of the Player.
   */
  public Player(Vector position, Vector size) {
    super(position, size);

    setGravity(true);

    id = 0;
    actions = new ArrayList<LevelElementAction>();
    lastMove = LevelElementAction.MoveRight;
    firing = false;
    alive = true;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
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
   * Gives the list of actions of the player.
   * @return a list of actions.
   */
  public ArrayList<LevelElementAction> getActions() {
    return actions;
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A PlayerAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A PlayerAction.
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
  
  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  public void decreaseLifetime(double delta) {
  }

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  public double getLifetime() {
    return 0;
  }

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  public void setLifetime(double newTime) {
  }

}
