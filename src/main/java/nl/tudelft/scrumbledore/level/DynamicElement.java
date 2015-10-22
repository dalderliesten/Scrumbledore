package nl.tudelft.scrumbledore.level;


public interface DynamicElement extends LevelElement {

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction
   */
  void addAction(LevelElementAction action);

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
  boolean hasAction(LevelElementAction action);

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A PlayerAction.
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
}
