package nl.tudelft.scrumbledore.level.element;

/**
 * Enumeration containing types of actions that a LevelElement can perform.
 * 
 * @author Jesse Tilro, Floris Doolaard
 *
 */
public enum LevelElementAction {
  MoveLeft, MoveRight, MoveStop, Jump, Shoot, ShootStop;

  /**
   * Returns the inverted action. i.e. MoveRight returns MoveStop.
   * 
   * @param action
   *          Action to be inverted
   * @return inverted action
   */
  public static LevelElementAction invertAction(LevelElementAction action) {
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
