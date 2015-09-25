package nl.tudelft.scrumbledore;

import java.util.ArrayList;

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
    ArrayList<Player> players = level.getPlayers();

    for (Player player : players) {
      checkHorizontalMovement(player);
      checkStopMovement(player);
      checkShooting(player, level);
      
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

  /**
   * Check for horizontal movement of the given player.
   * 
   * @param player
   *          Player to be checked
   */
  public void checkHorizontalMovement(Player player) {
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
  }
  
  /**
   * Checks if the player needs to stop moving.
   * 
   * @param player The player to be checked
   */
  public void checkStopMovement(Player player) {
    if (player.hasAction(PlayerAction.MoveStop)) {
      player.getSpeed().setX(0);

      if (Constants.LOGGING_WANTINPUT) {
        // Logging the stopping of player-caused movement.
        Logger.log("Player stopped moving.");
      }
    }
  }

  /**
   * Checks for shooting actions.
   * 
   * @param player
   *          The player to be checked
   * @param level
   *          Level to be get the bubbles from
   */
  public void checkShooting(Player player, Level level) {
    Vector bubblePos = player.getPosition().clone();
    ArrayList<Bubble> bubbles = level.getBubbles();

    if (player.hasAction(PlayerAction.Shoot)) {
      if (!player.isFiring()) {
        Bubble newBubble = new Bubble(bubblePos,
            new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));

        bubbles.add(newBubble);
        if (player.getLastMove() == PlayerAction.MoveLeft) {
          if (Constants.LOGGING_WANTSHOOTING) {
            // Sending the shooting information to the logger.
            Logger.log("Player shot in the western direction.");
          }
          newBubble.addAction(BubbleAction.MoveLeft);
        } else {
          if (Constants.LOGGING_WANTSHOOTING) {
            // Sending the shooting information to the logger.
            Logger.log("Player shot in the eastern direction.");
          }
          newBubble.addAction(BubbleAction.MoveRight);
        }
      }
      player.setFiring(true);
    }
    if (player.hasAction(PlayerAction.ShootStop)) {
      player.setFiring(false);
    }
  }
}
