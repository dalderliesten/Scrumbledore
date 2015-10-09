package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;

/**
 * Test Suite for the Bubble Actions Level Modifier class.
 * 
 * @author Jesse Tilro
 *
 */
public class BubbleActionsLevelModifierTest {

  private Bubble bubble;
  private Bubble bubble2;
  private Level level;
  private BubbleActionsLevelModifier modifier;

  /**
   * Setup a test object, Player Actions Level Modifier, and dependencies: a Player and a Level
   * instance.
   */
  @Before
  public void setUp() {
    Player player = new Player(new Vector(0, 0), new Vector(0, 0));
    bubble = new Bubble(new Vector(0, 0), new Vector(0, 0));
    bubble2 = new Bubble(new Vector(0, 0), new Vector(0, 0));
    level = new Level();
    level.addElement(player);
    level.addElement(bubble);
    level.addElement(bubble2);
    modifier = new BubbleActionsLevelModifier();
  }

  /**
   * When a Level is modified and its Bubbles have the action to move left or right, their speed
   * vector needs to updated correspondingly.
   */
  @Test
  public void testModifyMove() {
    bubble.addAction(BubbleAction.MoveLeft);
    bubble2.addAction(BubbleAction.MoveRight);
    modifier.modify(level, .5);
    double expected = -Constants.BUBBLE_SPEED;
    double expected2 = Constants.BUBBLE_SPEED;
    assertEquals(expected, bubble.getSpeed().getX(), Constants.DOUBLE_PRECISION);
    assertEquals(expected2, bubble2.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and one of its Bubbles is floating at maximal velocity, it should not
   * be accelerated anymore.
   */
  @Test
  public void testModifyFloatMax() {
    double expected = -Constants.BUBBLE_FLOAT;
    bubble.getSpeed().setY(expected);
    modifier.modify(level, .5);
    assertEquals(expected, bubble.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a Level is modified and one of its bubbles is out of lifetime, this bubble should be
   * removed from the level.
   */
  @Test
  public void testModifyRemoveNoLifetimeLeft() {
    bubble.decreaseLifetime(bubble.getLifetime());
    bubble2.addAction(BubbleAction.MoveLeft);
    modifier.modify(level, .5);
    assertFalse(level.getBubbles().contains(bubble));
  }

  /**
   * When a Level is modified, after processing all actions in a bubble's action queue, the queue
   * should be cleared.
   */
  @Test
  public void testModifyClear() {
    bubble.addAction(BubbleAction.MoveLeft);
    bubble2.addAction(BubbleAction.MoveRight);
    modifier.modify(level, .5);
    assertFalse(bubble.hasAction(BubbleAction.MoveLeft));
    assertFalse(bubble2.hasAction(BubbleAction.MoveRight));
  }
}
