package nl.tudelft.scrumbledore.keybinding;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;

/**
 * A custom container for Keybindings. One Keybinding per player.
 * 
 * @author David Alderliesten
 * @author Jeroen Meijer
 */
public final class KeybindingContainer {
  private static KeybindingContainer keybindingContainer;
  private List<Keybinding> keybindingList;

  private KeybindingContainer() {
    keybindingList = new ArrayList<Keybinding>();
    reset();
  }

  /**
   * Gets the KeybindingContainer.
   * 
   * @return KeybindingContainer the only instance.
   */
  public static synchronized KeybindingContainer getInstance() {
    if (keybindingContainer == null) {
      keybindingContainer = new KeybindingContainer();
    }

    return keybindingContainer;
  }

  /**
   * Resets all the Keybindings to the default Keybindings.
   */
  public void reset() {
    keybindingList.clear();
    for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
      keybindingList.add(new Keybinding(i));
    }
  }

  /**
   * Gets the Keybinding for the given player.
   * 
   * @param playerNumber
   *          The Player to get the Keybinding for.
   * @return Keybinding The Keybinding associated with the given Player.
   */
  public Keybinding getKeybinding(int playerNumber) {
    return keybindingList.get(playerNumber);
  }

  /**
   * Updates a Keybinding and if necessary unbinds other Keybinding to enforce KeyCode uniqueness.
   * 
   * @param playerNumber
   *          The player for which to update the Keybinding.
   * @param action
   *          The action for which the Keycode is updated.
   * @param key
   *          The new KeyCode for the given action.
   */
  public void updateKeyBinding(int playerNumber, LevelElementAction action, KeyCode key) {
    for (Keybinding binding : keybindingList) {
      if (binding.isAssigned(key)) {
        binding.updateKeyByAction(binding.getAction(key), KeyCode.UNDEFINED);
      }
    }

    getKeybinding(playerNumber).updateKeyByAction(action, key);
  }
}
