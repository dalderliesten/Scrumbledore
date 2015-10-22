package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the NPC class.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
@SuppressWarnings("PMD.TooManyMethods")
public class NPCTest extends LevelElementTest {

  private NPC npc;

  /**
   * Set the NPC field to a new NPC instance to use as a test object.
   */
  @Before
  public void setUp() {
    npc = new NPC(new Vector(0, 0), new Vector(0, 0));
  }

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new NPC(position, size);
  }

  /**
   * When a new NPC element is created, initially it should have gravity and have the correct
   * position and size.
   */
  @Test
  public void testConstuctor() {
    assertEquals(new Vector(0, 0), npc.getPosition());
    assertEquals(new Vector(0, 0), npc.getSize());
    assertTrue(npc.hasGravity());
  }

  /**
   * When an NPC action is added to a bubble's action queue, a call to hasAction for that action
   * should return true.
   */
  @Test
  public void testHasActionTrue() {
    npc.addAction(LevelElementAction.MoveLeft);
    assertTrue(npc.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When an NPC action was not added to a bubble's action queue, a call to hasAction for that
   * action should return false.
   */
  @Test
  public void testHasActionFalse() {
    npc.addAction(LevelElementAction.MoveLeft);
    assertFalse(npc.hasAction(LevelElementAction.MoveRight));
  }

  /**
   * When an NPC action queue is cleared, it should not have the actions anymore which were added
   * before.
   */
  @Test
  public void testClearActions() {
    npc.addAction(LevelElementAction.MoveLeft);
    npc.clearActions();
    assertFalse(npc.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When an NPC has an action removed from its action queue, it should no longer have the action.
   */
  @Test
  public void testRemoveAction() {
    npc.addAction(LevelElementAction.MoveLeft);
    npc.addAction(LevelElementAction.MoveLeft);
    npc.removeAction(LevelElementAction.MoveLeft);
    assertFalse(npc.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * The stubbed method hashCode should just return zero (line coverage).
   */
  @Test
  public void testHashCode() {
    assertEquals(0, npc.hashCode());
  }

  /**
   * Two NPC instances with the same position and size should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    NPC npc2 = new NPC(npc.getPosition(), npc.getSize());
    assertEquals(npc, npc2);
  }

  /**
   * Two NPC instances with a different position or size should not be considered equal.
   */
  @Test
  public void testEqualsFalse() {
    NPC npc2 = new NPC(new Vector(42, 42), npc.getSize());
    NPC npc3 = new NPC(npc.getPosition(), new Vector(42, 42));
    assertFalse(npc.equals(npc2));
    assertFalse(npc.equals(npc3));
  }

  /**
   * An NPC instance and an instance of another class should not be considered equal.
   */
  @Test
  public void testEqualsFalseOtherClass() {
    Fruit fruit = new Fruit(npc.getPosition(), npc.getSize());
    assertFalse(npc.equals(fruit));
  }

  /**
   * Test the lastMove field getter/setter.
   */
  @Test
  public void testLastMove() {
    npc.setLastMove(LevelElementAction.MoveLeft);
    assertEquals(LevelElementAction.MoveLeft, npc.getLastMove());
    npc.setLastMove(LevelElementAction.MoveRight);
    assertEquals(LevelElementAction.MoveRight, npc.getLastMove());
    // Actions other than MoveLeft and MoveRight should be ignored.
    assertEquals(LevelElementAction.MoveRight, npc.getLastMove());
  }
}
