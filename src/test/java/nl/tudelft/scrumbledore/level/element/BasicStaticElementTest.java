package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test case for the abstract BasicStaticElement class.
 * 
 * @author Niels Warnars
 */
public class BasicStaticElementTest {
  private ConcreteBasicStaticElement element;
  
  /**
   * Set the ConcreteBasicStaticElement field to a new ConcreteBasicStaticElement instance 
   * to use as a test object.
   */
  @Before
  public void setUp() {
    element = new ConcreteBasicStaticElement(new Vector(0, 0), new Vector(32, 32));
  }
  
  /**
   * Test the height property of a basic static element.
   */
  @Test
  public void testHeight() {
    assertEquals(32, element.height(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the width property of a basic static element.
   */
  @Test
  public void testWidth() {
    assertEquals(32, element.width(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Verify whether the distance between two basic static elements can be calculated correctly.
   */
  @Test
  public void testDistance() {
    ConcreteBasicStaticElement element2 
      = new ConcreteBasicStaticElement(new Vector(42, 0), new Vector(32, 32));

    assertEquals(42d, element.distance(element2), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Verify whether the in radius calculation of two level elements happens correctly. 
   * Verification should result in 'false'.
   */
  @Test
  public void testInRadiusRangeOfSmaller() {
    ConcreteBasicStaticElement element2 
      = new ConcreteBasicStaticElement(new Vector(42, 0), new Vector(32, 32));

    assertFalse(element.inRadiusRangeOf(element2, 41d));
  }
  /**
   * Verify whether the in radius calculation of two level elements happens correctly. 
   * Verification should result in 'true'.
   */
  @Test
  public void testInRadiusRangeOfEqual() {
    ConcreteBasicStaticElement element2 
      = new ConcreteBasicStaticElement(new Vector(42, 0), new Vector(32, 32));

    assertTrue(element.inRadiusRangeOf(element2, 42d));
  }
  
  /**
   * Verify whether the in radius calculation of two level elements happens correctly. 
   * Verification should result in 'true'.
   */
  @Test
  public void testInRadiusRangeOfEqualBigger() {
    ConcreteBasicStaticElement element2 
      = new ConcreteBasicStaticElement(new Vector(42, 0), new Vector(32, 32));

    assertTrue(element.inRadiusRangeOf(element2, 43d));
  }
  
  /**
   *  Dummy concrete instance of the abstract BasicDynamicElement class.
   */
  private static class ConcreteBasicStaticElement extends BasicDynamicElement {

    ConcreteBasicStaticElement(Vector position, Vector size) {
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
