package nl.tudelft.scrumbledore.level;

/**
 * Enumeration containing types of actions that the player can perform.
 * 
 * @author Jesse Tilro
 *
 */
public enum PlayerAction {
  MoveLeft, MoveRight, MoveStop, Jump, Shoot, ShootStop;

  /**
   * Returns the inverted action. i.e. MoveRight returns MoveStop.
   * 
   * @param action
   *          Action to be inverted
   * @return inverted action
   */
  public static PlayerAction invertAction(PlayerAction action) {
    if (action != null) {
      switch (action) {
      case MoveRight:
        return MoveStop;
      case MoveLeft:
        return MoveStop;
      case Shoot:
        return ShootStop;
      default:
        return null;
      }
    }
    return null;
  }
}
