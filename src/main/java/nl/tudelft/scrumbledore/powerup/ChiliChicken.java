package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.LevelElement;
import nl.tudelft.scrumbledore.level.Player;
import nl.tudelft.scrumbledore.level.PlayerAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * ChiliChicken is a power-up that gives a Player object more speed for 5 seconds.
 * 
 * @author Floris Doolaard
 *
 */
public class ChiliChicken extends LevelElement implements Powerup {

  private ArrayList<PlayerAction> actions;
  private PlayerAction lastMove;
  private Boolean firing;
  private Boolean alive;
  private int id;

  private Player player;

  /**
   * Creates a ChiliChicken instance.
   * 
   * @param position
   *          , location of the ChiliChicken.
   * @param size
   *          , size of the object.
   */
  public ChiliChicken(Vector position, Vector size) {
    super(position, size);

    setGravity(true);

    id = 0;
    actions = new ArrayList<PlayerAction>();
    lastMove = PlayerAction.MoveRight;
    firing = false;
    alive = true;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  public void addAction(PlayerAction action) {
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
   *          A PlayerAction.
   * @return Boolean.
   */
  public boolean hasAction(PlayerAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A PlayerAction.
   */
  public void removeAction(PlayerAction action) {
    actions.remove(action);
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public PlayerAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(PlayerAction action) {
    if (action == PlayerAction.MoveLeft || action == PlayerAction.MoveRight) {
      lastMove = action;
    }
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ChiliChicken) {
      ChiliChicken that = (ChiliChicken) other;
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
      boolean toRight = getLastMove() == PlayerAction.MoveRight;

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
      
      result.add(store.getAnimated("fire-yellow").getFrame(steps));
    }
    return result;
  }

}
