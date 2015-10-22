package nl.tudelft.scrumbledore.powerup;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.LevelElementAction;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;
import nl.tudelft.scrumbledore.sprite.SpriteStore;

/**
 * This class represents a pickup item of the BlueberryBubble power-up.
 * 
 * @author Floris Doolaard
 *
 */
public class BlueberryBubblePickUp extends PowerupPickUp {

  /**
   * The consctrutor creates a pickup item in the level.
   * @param position , location of the object.
   * @param size , size of the object.
   */
  public BlueberryBubblePickUp(Vector position, Vector size) {
    super(position, size);
  }

  @Override
  public ArrayList<Sprite> getSprites(double steps) {
    SpriteStore store = SpriteStore.getInstance();
    ArrayList<Sprite> result = new ArrayList<Sprite>();

    result.add(store.get("powerup-blueberry-bubble"));
    return result;
  }

  public void addAction(LevelElementAction action) {
    // TODO Auto-generated method stub
    
  }

  public void clearActions() {
    // TODO Auto-generated method stub
    
  }

  public Boolean isAlive() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setAlive(Boolean bool) {
    // TODO Auto-generated method stub
    
  }

  public int getPlayerNumber() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void setPlayerNumber(int id) {
    // TODO Auto-generated method stub
    
  }

  public boolean hasAction(LevelElementAction action) {
    // TODO Auto-generated method stub
    return false;
  }

  public void removeAction(LevelElementAction action) {
    // TODO Auto-generated method stub
    
  }

  public LevelElementAction getLastMove() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setLastMove(LevelElementAction action) {
    // TODO Auto-generated method stub
    
  }

  public Boolean isFiring() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setFiring(Boolean isFiring) {
    // TODO Auto-generated method stub
    
  }

  public ArrayList<LevelElementAction> getActions() {
    // TODO Auto-generated method stub
    return null;
  }


}
