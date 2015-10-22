package nl.tudelft.scrumbledore.keybinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.LevelElementAction;

/**
 * Test suite for the KeybindingContainer class.
 * 
 * @author Jeroen Meijer
 */
public class KeybindingContainerTest {

  private KeybindingContainer keybindingContainer = KeybindingContainer.getInstance();

  /**
   * Resets the keybindingcontainer after each test.
   */
  @After
  public void reset() {
    keybindingContainer.reset();
  }

  /**
   * Tests if the getInstance method returns a KeybindingContainer.
   */
  @Test
  public void testGetInstance() {
    assertTrue(KeybindingContainer.getInstance() instanceof KeybindingContainer);
  }

  /**
   * Tests the getter method.
   */
  @Test
  public void testGetKeybinding() {
    Keybinding binding = new Keybinding(0);
    for (KeyCode key : Constants.KEY_MAPPING.get(0).keySet()) {
      assertEquals(binding.getAction(key), keybindingContainer.getKeybinding(0).getAction(key));
    }
  }

  /**
   * Tests if the updateKeybinding method updates the keybinding properly.
   */
  @Test
  public void testUpdateKeybinding() {
    assertEquals(KeyCode.UP, keybindingContainer.getKeybinding(0).getKey(LevelElementAction.Jump));

    keybindingContainer.updateKeyBinding(0, LevelElementAction.Jump, KeyCode.LEFT);

    assertEquals(KeyCode.LEFT,
        keybindingContainer.getKeybinding(0).getKey(LevelElementAction.Jump));

    assertFalse(keybindingContainer.getKeybinding(0).isAssigned(KeyCode.UP));

    assertEquals(KeyCode.UNDEFINED,
        keybindingContainer.getKeybinding(0).getKey(LevelElementAction.MoveLeft));
  }
  
  /**
   * Tests whether the reset functions resets.
   */
  @Test
  public void testReset() {
    assertEquals(KeyCode.UP, keybindingContainer.getKeybinding(0).getKey(LevelElementAction.Jump));

    keybindingContainer.updateKeyBinding(0, LevelElementAction.Jump, KeyCode.LEFT);

    assertEquals(KeyCode.LEFT,
        keybindingContainer.getKeybinding(0).getKey(LevelElementAction.Jump));

    assertFalse(keybindingContainer.getKeybinding(0).isAssigned(KeyCode.UP));

    assertEquals(KeyCode.UNDEFINED,
        keybindingContainer.getKeybinding(0).getKey(LevelElementAction.MoveLeft));

    keybindingContainer.reset();

    assertEquals(KeyCode.UP, keybindingContainer.getKeybinding(0).getKey(LevelElementAction.Jump));
  }
}
