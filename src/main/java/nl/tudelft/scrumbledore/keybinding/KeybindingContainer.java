package nl.tudelft.scrumbledore.keybinding;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Player;

public class KeybindingContainer {
  private static KeybindingContainer keybindingContainer;

  private List<Keybinding> keybindingList;

  private KeybindingContainer() {
    keybindingList = new ArrayList<Keybinding>();
    reset();
  }

  public synchronized static KeybindingContainer getInstance() {
    if (keybindingContainer == null) {
      keybindingContainer = new KeybindingContainer();
    }

    return keybindingContainer;
  }

  public void reset() {
    for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
      keybindingList.add(new Keybinding(i + 1));
    }
  }

  public Keybinding getKeybindg(Player player) {
    return keybindingList.get(player.getPlayerNumber());
  }

  public void addKeybinding() {

  }
}
