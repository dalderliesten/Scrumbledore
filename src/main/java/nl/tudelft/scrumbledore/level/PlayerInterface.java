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
}
