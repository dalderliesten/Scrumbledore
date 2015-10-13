package nl.tudelft.scrumbledore.keybinding;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.PlayerAction;

/**
 * A custom container for Keybindings. One Keybinding per player.
 * 
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
    for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
      keybindingList.add(new Keybinding(i + 1));
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
   * Checks if a key has been bound anywhere in the keybindings.
   * 
   * @param key
   *          The KeyCode to check for.
   * @return boolean Whether or not a key has been bound.
   */
  public boolean isAssigned(KeyCode key) {
    boolean assigned = false;
    for (Keybinding binding : keybindingList) {
      if (binding.isAssigned(key)) {
        assigned = true;
      }
    }
    return assigned;
  }

  /**
   * 
   * @param playerNumber 
   * @param action 
   * @param key 
   */
  public void updateKeyBinding(int playerNumber, PlayerAction action, KeyCode key) {

  }
}
