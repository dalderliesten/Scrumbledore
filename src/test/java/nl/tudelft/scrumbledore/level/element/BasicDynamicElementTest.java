package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test case for the abstract BasicDynamicElement class.
 *  
 * @author Niels Warnars
 */
public class BasicDynamicElementTest {
  private ConcreteBasicDynamicElement element;
  
  /**
   * Set the ConcreteBasicDynamicElement field to a new ConcreteBasicDynamicElement instance 
   * to use as a test object.
   */
  @Before
  public void setUp() {
    element = new ConcreteBasicDynamicElement(new Vector(0, 0), new Vector(0, 0));
  }
  
  /**
   * When an element action was not added to a bubble's action queue, a call to hasAction for that
   * action should return false.
   */
  @Test
  public void testHasActionFalse() {
    element.addAction(LevelElementAction.MoveLeft);
    assertFalse(element.hasAction(LevelElementAction.MoveRight));
  }

  /**
   * When an element action queue is cleared, it should not have the actions anymore 
   * which were added before.
   */
  @Test
  public void testClearActions() {
    element.addAction(LevelElementAction.MoveLeft);
    element.clearActions();
    assertFalse(element.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When an element has an action removed from its action queue, it should no longer have 
   * the action.
   */
  @Test
  public void testRemoveAction() {
    element.addAction(LevelElementAction.MoveLeft);
    element.addAction(LevelElementAction.MoveLeft);
    element.removeAction(LevelElementAction.MoveLeft);
    assertFalse(element.hasAction(LevelElementAction.MoveLeft));
  }

  /**
   * When the DynamicElement.getActions is called it should return all the actions 
   * assigned to a element object.
   */
  @Test
  public void testGetActions() {
    element.clearActions();
    element.addAction(LevelElementAction.MoveLeft);
    element.addAction(LevelElementAction.MoveRight);
    assertEquals(2, element.getActions().size());
    assertEquals(LevelElementAction.MoveLeft, element.getActions().get(0));
    assertEquals(LevelElementAction.MoveRight, element.getActions().get(1));
  }
  
  /**
   *  Dummy concrete instance of the abstract BasicDynamicElement class.
   */
  private static class ConcreteBasicDynamicElement extends BasicDynamicElement {

    /**
     * Create a new ConcreteBasicLevelElement instance.
     * 
     * @param position
     *          Position of the element in the level.
     * @param size
     *          Size of the element.
     */
    ConcreteBasicDynamicElement(Vector position, Vector size) {
      super(position, size);
    }

    public LevelElementAction getLastMove() {
      return null;
    }

    public void setLastMove(LevelElementAction action) {      
    }

    @Override
    public ArrayList<Sprite> getSprites(double steps) {
      return null;
    }
    
  }
}
