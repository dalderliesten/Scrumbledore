package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.PlayerAction;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * This interface tells the player and the powerup objects to have the same functionality.
 * 
 * @author Floris
 *
 */
public interface Powerup {

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  void addAction(PlayerAction action);

  /**
   * Remove all actions from the queue.
   */
  void clearActions();

  /**
   * Checking wether the player is alive.
   * 
   * @return The boolean if the player is alive.
   */
  Boolean isAlive();

  /**
   * Setting the life of the player.
   * 
   * @param bool
   *          Can be True or False, stated on situation of player.
   */
  void setAlive(Boolean bool);

  /**
   * Gets the id of the current player.
   * 
   * @return Integer that represents the players number in the game.
   */
  int getPlayerNumber();

  /**
   * Sets the id of the current player.
   * 
   * @param id
   *          Integer that represents the players number in the game.
   */
  void setPlayerNumber(int id);

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A PlayerAction.
   * @return Boolean.
   */
  boolean hasAction(PlayerAction action);

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A PlayerAction.
   */
  void removeAction(PlayerAction action);

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  PlayerAction getLastMove();

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  void setLastMove(PlayerAction action);

  @Override
  int hashCode();

  @Override
  boolean equals(Object other);

  /**
   * Return whether the Player is firing.
   * 
   * @return whether the Player is firing
   */
  Boolean isFiring();

  /**
   * Set whether the Player is firing.
   * 
   * @param isFiring
   *          whether the Player is firing
   */
  void setFiring(Boolean isFiring);

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   * @return Sprites to be drawn.
   */
  ArrayList<Sprite> getSprites(double steps);
}
