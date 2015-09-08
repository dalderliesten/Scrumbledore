package nl.tudelft.scrumbledore;

/**
 * Level Modifier that processes the actions to be performed on the Player.
 * 
 * @author Jesse Tilro
 *
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
    }
    
    // Horizontal Movement.
    if (player.hasAction(PlayerAction.MoveLeft)) {
      player.getSpeed().setX(-1 * Constants.PLAYER_SPEED);
    }
    if (player.hasAction(PlayerAction.MoveRight)) {
      player.getSpeed().setX(Constants.PLAYER_SPEED);
    }

    // Jumping
    if (player.hasAction(PlayerAction.Jump) && player.vSpeed() == 0) {
      player.getSpeed().setY(-1 * Constants.PLAYER_JUMP);
    }
    
    // Clear actions for next step.
    player.clearActions();

  }

}
