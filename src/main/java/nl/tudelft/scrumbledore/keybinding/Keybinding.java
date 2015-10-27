package nl.tudelft.scrumbledore.keybinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.LevelElementAction;

/**
 * Represents a Keybinding in the form of a key value pair.
 * 
 * @author Jeroen Meijer
 */
public class Keybinding {
  private Map<KeyCode, LevelElementAction> keyMap;
  private Map<LevelElementAction, KeyCode> actionMap;

  /**
   * Creates a new KeyBinding with the default binding for the given player number.
   * 
   * @param playerNumber
   *          The number of a given player.
   */
  public Keybinding(int playerNumber) {
    keyMap = new HashMap<KeyCode, LevelElementAction>();
    actionMap = new HashMap<LevelElementAction, KeyCode>();

    Map<KeyCode, LevelElementAction> tempMap = Constants.KEY_MAPPING.get(playerNumber);

    for (Entry<KeyCode, LevelElementAction> entry : tempMap.entrySet()) {
      keyMap.put(entry.getKey(), tempMap.get(entry.getKey()));
      actionMap.put(tempMap.get(entry.getKey()), entry.getKey());
    }
  }

  /**
   * Updates a Keybinding with the given using the key as identifier.
   * 
   * @param action
   *          Updated.
   * @param key
   *          Unchanged.
   */
  public void updateActionByKey(KeyCode key, LevelElementAction action) {
    actionMap.remove(keyMap.get(key));
    actionMap.put(action, key);
    keyMap.replace(key, action);
  }

  /**
   * Updates a Keybinding with the given using the action as identifier.
   * 
   * @param action
   *          Unchanged.
   * @param key
   *          Updated.
   */
  public void updateKeyByAction(LevelElementAction action, KeyCode key) {
    keyMap.remove(actionMap.get(action));
    keyMap.put(key, action);
    actionMap.replace(action, key);
  }

  /**
   * Gets a KeyCode based on an LevelElementAction.
   * 
   * @param action
   *          The LevelElementAction to get the KeyCode for.
   * @return KeyCode The KeyCode associated with a LevelElementAction.
   */
  public KeyCode getKey(LevelElementAction action) {
    return actionMap.get(action);
  }

  /**
   * Gets a LevelElementAction based on an KeyCode.
   * 
   * @param key
   *          The KeyCode to get the LevelElementAction for.
   * @return LevelElementAction The LevelElementAction associated with a KeyCode.
   */
  public LevelElementAction getAction(KeyCode key) {
    return keyMap.get(key);
  }

  /**
   * Checks if the given key is already mapped.
   * 
   * @param key
   *          The key to check.
   * @return boolean Wether or not the key is already bound.
   */
  public boolean isAssigned(KeyCode key) {
    return getAction(key) != null;
  }
}
