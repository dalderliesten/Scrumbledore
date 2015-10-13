package nl.tudelft.scrumbledore.keybinding;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Player;

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
   * 
   */
  public void reset() {
    for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
      keybindingList.add(new Keybinding(i + 1));
    }
  }

  public Keybinding getKeybinding(Player player) {
    return keybindingList.get(player.getPlayerNumber());
  }

  public void addKeybinding() {

  }
}
