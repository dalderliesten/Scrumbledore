package nl.tudelft.scrumbledore;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test case for the Fruit class.
 * 
 * @author Niels Warnars
 */
public class FruitTest {

  /**
   * Test the Fruit() constructor.
   */
  @Test
  public void testFruit() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
   
    assertEquals(new Vector(0, 0), fruit.getPosition());
    assertEquals(new Vector(32, 32), fruit.getSize());
    assertTrue(fruit.hasGravity());
  }
  
  /**
   * Test the get/setValue methods.
   */
  @Test
  public void testGetSetValue() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    
    fruit.setValue(42);
    assertEquals(42, fruit.getValue());
  }

}