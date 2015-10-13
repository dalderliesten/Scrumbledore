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
  public static PlayerAction invertAction(Player player, PlayerAction action) {
    if (action != null) {
      switch (action) {
      case MoveRight:
        player.removeAction(PlayerAction.MoveRight);
        return MoveStop;
      case MoveLeft:
        player.removeAction(PlayerAction.MoveLeft);
        return MoveStop;
      case Shoot:
        player.removeAction(PlayerAction.Shoot);
        return ShootStop;
      case Jump:
        player.removeAction(PlayerAction.Jump);
      default:
        return null;
      }
    }
    return null;
  }
}
