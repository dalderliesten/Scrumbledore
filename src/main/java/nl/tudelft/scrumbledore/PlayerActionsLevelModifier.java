package nl.tudelft.scrumbledore;

/**
 * Level Modifier that processes the actions to be performed on the Player.
 * 
 * @author Jesse Tilro
 * @author David Alderliesten
 */
public class PlayerActionsLevelModifier implements LevelModifier {

  /**
   * Process the actions to be performed on the Player.
   * 
   * @param level
   *          The level in which the player actions need to be processed.
   * @param delta
   *          The number of steps passed since the last execution of this method.
   */
  public void modify(Level level, double delta) {

    Player player = level.getPlayer();

    // Stop Horizontal Movement.
    if (player.hasAction(PlayerAction.MoveStop)) {
      player.getSpeed().setX(0);

      if (Constants.LOGGING_WANTINPUT) {
        // Logging the stopping of player-caused movement.
        Logger.log("Player stopped moving.");
      }

    }

    // Horizontal Movement.
    if (player.hasAction(PlayerAction.MoveLeft)) {
      player.getSpeed().setX(-1 * Constants.PLAYER_SPEED);
      if (Constants.LOGGING_WANTINPUT) {
        // Logging the moving to the left action.
        Logger.log("Player performed the move left action.");
      }

    }

    if (player.hasAction(PlayerAction.MoveRight)) {
      player.getSpeed().setX(Constants.PLAYER_SPEED);

      if (Constants.LOGGING_WANTINPUT) {
        // Logging the moving to the right action.
        Logger.log("Player performed the move right action.");
      }

    }

    // Jumping
    if (player.hasAction(PlayerAction.Jump) && player.vSpeed() == 0) {
      player.getSpeed().setY(-1 * Constants.PLAYER_JUMP);

      if (Constants.LOGGING_WANTINPUT) {
        // Logging the jumping action.
        Logger.log("Player performed the jump action.");
      }

    }

    // Clear actions for next step.
    player.clearActions();

  }

}
