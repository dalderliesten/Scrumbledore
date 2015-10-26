package nl.tudelft.scrumbledore.level;

public interface PlayerInterface extends DynamicElement {

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
   * Checking wether the element is alive.
   * 
   * @return The boolean if the element is alive.
   */
  Boolean isAlive();

  /**
   * Setting the life of the element.
   * 
   * @param bool
   *          Can be True or False, stated on situation of element.
   */
  void setAlive(Boolean bool);
  
  /**
   * Decrease the lifetime by a given number of steps.
   * 
   * @param delta
   *          The number of steps.
   */
  void decreaseLifetime(double delta);

  /**
   * Get the remaining lifetime.
   * 
   * @return Remaining lifetime.
   */
  double getLifetime();

  /**
   * Setting the life time of a bubble.
   * 
   * @param newTime
   *          The new life time.
   */
  void setLifetime(double newTime);
}
