package nl.tudelft.scrumbledore.level.element;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class representing an NPC in a game.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
 */
public class NPC extends BasicDynamicElement {
  private LevelElementAction lastMove;
  private ArrayList<LevelElementAction> actions;

  /**
   * Create a new NPC instance.
   * 
   * @param position
   *          Position of the NPC in the level.
   *          
   * @param size
   *          Size of the NPC.
   */
  public NPC(Vector position, Vector size) {
    super(position, size);

    setGravity(true);

    actions = new ArrayList<LevelElementAction>();
    addAction(LevelElementAction.MoveLeft);
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A PlayerAction.
   */
  public void addAction(LevelElementAction action) {
    if (!hasAction(action)) {
      actions.add(action);
      setLastMove(action);
    }
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
    actions.clear();
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          An NPCAction.
   *          
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return actions.contains(action);
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          An NPCAction.
   */
  public void removeAction(LevelElementAction action) {
    actions.remove(action);
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof NPC) {
      NPC that = (NPC) other;
      return this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize());
    }

    return false;
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return lastMove;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
    if (action == LevelElementAction.MoveLeft || action == LevelElementAction.MoveRight) {
      lastMove = action;
    }
  }

  /**
   * Retrieve a set of Sprites to be drawn in the current cycle at the position of this Level
   * Element.
   * 
   * @param steps
   *          The absolute exact number of steps since the game was started.
   *          
   * @return Sprites to be drawn.
   */
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    String id = "zenchan-move-right";
    if (getLastMove().equals(LevelElementAction.MoveLeft)) {
      id = "zenchan-move-left";
    }
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    result.add(store.getAnimated(id).getFrame(steps));
    return result;
  }

  /**
   * Gives a list of current actions of the player.
   * 
   * @return a list of actions
   */
  public ArrayList<LevelElementAction> getActions() {
    return actions;
  }

}
