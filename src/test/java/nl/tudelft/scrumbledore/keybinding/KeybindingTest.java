package nl.tudelft.scrumbledore.keybinding;

import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.input.KeyCode;
import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;;

/**
 * Test suite for the Keybinding class.
 * 
 * @author Jeroen Meijer
 */
public class KeybindingTest {

  private Keybinding keybinding;

  /**
   * Set-up the initial Keybinding.
   */
  @Before
  public void setUp() {
    keybinding = new Keybinding(0);
  }

  /**
   * Tests if the initial keybinding is correct.
   */
  @Test
  public void testInitialKeybinding() {
    for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
      keybinding = new Keybinding(i);
      Map<KeyCode, LevelElementAction> bindings = Constants.KEY_MAPPING.get(i);
      for (Entry<KeyCode, LevelElementAction> entry : bindings.entrySet()) {
        assertEquals(bindings.get(entry.getKey()), keybinding.getAction(entry.getKey()));
      }
    }
  }

  /**
   * Tests if the getter is correct.
   */
  @Test
  public void testGetKey() {
    assertEquals(KeyCode.UP, keybinding.getKey(LevelElementAction.Jump));
  }

  /**
   * Tests if the getter is correct.
   */
  @Test
  public void testGetAction() {
    assertEquals(LevelElementAction.Jump, keybinding.getAction(KeyCode.UP));
  }

  /**
   * Tests if the isAssigned works correctly.
   */
  @Test
  public void testIsAssigned() {
    assertTrue(keybinding.isAssigned(KeyCode.UP));
  }

  /**
   * Tests the updateKeyByAction method.
   */
  @Test
  public void testUpdateKeyByAction() {
    assertEquals(LevelElementAction.Jump, keybinding.getAction(KeyCode.UP));

    keybinding.updateKeyByAction(LevelElementAction.Jump, KeyCode.LEFT);

    assertEquals(LevelElementAction.Jump, keybinding.getAction(KeyCode.LEFT));

    assertFalse(keybinding.isAssigned(KeyCode.UP));
  }

  /**
   * Tests the updateKeyByAction method.
   */
  @Test
  public void testUpdateActionByKey() {
    assertEquals(KeyCode.UP, keybinding.getKey(LevelElementAction.Jump));

    keybinding.updateActionByKey(KeyCode.UP, LevelElementAction.Shoot);

    assertEquals(KeyCode.UP, keybinding.getKey(LevelElementAction.Shoot));
  }

}
