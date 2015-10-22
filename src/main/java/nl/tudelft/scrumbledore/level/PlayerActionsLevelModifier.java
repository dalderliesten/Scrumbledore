package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;

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
    ArrayList<DynamicElement> players = level.getPlayers();

    for (DynamicElement player : players) {
      if (player.isAlive()) {
        checkStopMovement(player);
        checkHorizontalMovement(player);
        checkShooting(player, level);

        if (player.hasAction(LevelElementAction.Jump) && player.vSpeed() == 0) {
          player.getSpeed().setY(-1 * Constants.PLAYER_JUMP);

          if (Constants.isLoggingWantInput()) {
            Logger.getInstance().log("Player performed the jump action.");
          }
        }

        player.removeAction(LevelElementAction.MoveStop);
        player.removeAction(LevelElementAction.Shoot);
      }
    }
  }

  /**
   * Check for horizontal movement of the given player.
   * 
   * @param player
   *          Player to be checked
   */
  public void checkHorizontalMovement(DynamicElement player) {
    if (player.hasAction(LevelElementAction.MoveLeft)) {
      player.getSpeed().setX(-1 * Constants.PLAYER_SPEED);
      if (Constants.isLoggingWantInput()) {
        Logger.getInstance().log("Player performed the move left action.");
      }
    }

    if (player.hasAction(LevelElementAction.MoveRight)) {
      player.getSpeed().setX(Constants.PLAYER_SPEED);

      if (Constants.isLoggingWantInput()) {
        Logger.getInstance().log("Player performed the move right action.");
      }
    }
  }

  /**
   * Checks if the player needs to stop moving.
   * 
   * @param player
   *          The player to be checked
   */
  public void checkStopMovement(DynamicElement player) {
    if (player.hasAction(LevelElementAction.MoveStop)) {
      player.getSpeed().setX(0);

      if (Constants.isLoggingWantInput()) {
        Logger.getInstance().log("Player stopped moving.");
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
  @SuppressWarnings("methodlength")
  public void checkShooting(DynamicElement player, Level level) {
    Vector bubblePos = null;
    
    try {
      bubblePos = player.getPosition().clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    ArrayList<Bubble> bubbles = level.getBubbles();

    if (player.hasAction(LevelElementAction.Shoot) && player.isAlive()) {
      if (!player.isFiring()) {
        Bubble newBubble = new Bubble(bubblePos,
            new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));

        bubbles.add(newBubble);
        if (player.getLastMove() == LevelElementAction.MoveLeft) {
          if (Constants.isLoggingWantShooting()) {
            Logger.getInstance().log("Player shot in the left direction.");
          }
          newBubble.addAction(BubbleAction.MoveLeft);
        } else {
          if (Constants.isLoggingWantShooting()) {
            Logger.getInstance().log("Player shot in the right direction.");
          }
          newBubble.addAction(BubbleAction.MoveRight);
        }
      }
      player.setFiring(true);
    }
    if (player.hasAction(LevelElementAction.ShootStop)) {
      player.setFiring(false);
      player.removeAction(LevelElementAction.ShootStop);
    }
  }
}
