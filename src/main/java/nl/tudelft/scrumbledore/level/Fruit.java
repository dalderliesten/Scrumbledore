package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * Class representing a Fruit in a game.
 * 
 * @author Niels Warnars
 * @author Floris Doolaard
 */
public class Fruit extends BasicDynamicElement {
  private int value;
  private Boolean pickable;

  /**
   * Create a new Fruit instance.
   * 
   * @param position
   *          Position of the fruit in the level.
   *          
   * @param size
   *          Size of the fruit.
   */
  public Fruit(Vector position, Vector size) {
    super(position, size);

    setGravity(true);
    pickable = false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Fruit) {
      Fruit that = (Fruit) other;
      return this.getPosition().equals(that.getPosition()) && this.getSize().equals(that.getSize());
    }

    return false;
  }

  /**
   * Get the value of a fruit.
   * 
   * @return The value of a fruit
   */
  public int getValue() {
    return value;
  }

  /**
   * Set the value of a fruit.
   * 
   * @param value
   *          The value of a fruit
   */
  public void setValue(int value) {
    this.value = value;
  }

  /**
   * Return whether the fruit instance is pickable or not.
   * 
   * @return Boolean pickable.
   */
  public Boolean isPickable() {
    return pickable;
  }

  /**
   * Setting whether the fruit instance is pickable.
   * 
   * @param bool
   *          true or false.
   */
  public void setPickable(Boolean bool) {
    pickable = bool;
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
    ArrayList<Sprite> result = new ArrayList<Sprite>();
    result.add(store.getAnimated("fruit").getFrame(posX()));
    return result;
  }

  /**
   * Add an action to be performed in the next step.
   * 
   * @param action
   *          A LevelElementAction
   */
  public void addAction(LevelElementAction action) {
  }

  /**
   * Remove all actions from the queue.
   */
  public void clearActions() {
  }

  /**
   * Check whether the given action is queued for the next step.
   * 
   * @param action
   *          A LevelElementAction.
   * @return Boolean.
   */
  public boolean hasAction(LevelElementAction action) {
    return false;
  }

  /**
   * Remove the given action from the actions queue.
   * 
   * @param action
   *          A LevelElementAction.
   */
  public void removeAction(LevelElementAction action) {
  }

  /**
   * Get the last horizontal move performed.
   * 
   * @return The last move performed.
   */
  public LevelElementAction getLastMove() {
    return null;
  }

  /**
   * Set the last performed horizontal move.
   * 
   * @param action
   *          The last move action performed.
   */
  public void setLastMove(LevelElementAction action) {
  }

  /**
   * Gives a list of current actions of the player.
   * 
   * @return a list of actions
   */
  public ArrayList<LevelElementAction> getActions() {
    return null;
  }

}
