package nl.tudelft.scrumbledore.keybinding;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * Represents a Keybinding in the form of a key value pair.
 * 
 * @author Jeroen Meijer
 */
public class Keybinding {
  private Map<KeyCode, PlayerAction> keyMap;
  private Map<PlayerAction, KeyCode> actionMap;

  /**
   * Creates a new KeyBinding with the default binding for the given player number.
   * 
   * @param playerNumber
   *          The number of a given player.
   */
  public Keybinding(int playerNumber) {
    keyMap = new HashMap<KeyCode, PlayerAction>();
    actionMap = new HashMap<PlayerAction, KeyCode>();

    keyMap = Constants.KEY_MAPPING.get(playerNumber);
    for (KeyCode key : keyMap.keySet()) {
      actionMap.put(keyMap.get(key), key);
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
  public void updateActionByKey(KeyCode key, PlayerAction action) {
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
  public void updateKeyByAction(PlayerAction action, KeyCode key) {
    keyMap.remove(actionMap.get(action));
    keyMap.put(key, action);
    actionMap.replace(action, key);
  }

  /**
   * Gets a KeyCode based on an PlayerAction.
   * 
   * @param action
   *          The PlayerAction to get the KeyCode for.
   * @return KeyCode The KeyCode associated with a PlayerAction.
   */
  public KeyCode getKey(PlayerAction action) {
    return actionMap.get(action);
  }

  /**
   * Gets a PlayerAction based on an KeyCode.
   * 
   * @param key
   *          The KeyCode to get the PlayerAction for.
   * @return PlayerAction The PlayerACtion associated with a KeyCode.
   */
  public PlayerAction getAction(KeyCode key) {
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
