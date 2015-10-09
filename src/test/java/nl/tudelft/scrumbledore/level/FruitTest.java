package nl.tudelft.scrumbledore.level;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.Fruit;
import nl.tudelft.scrumbledore.level.NPC;
import nl.tudelft.scrumbledore.level.Vector;

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
  
  /**
   * Test dummy hashCode method.
   */
  @Test
  public void testHashCode() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    assertEquals(0, fruit.hashCode());
 
  }
  /**
   * Test the equals method with two the same objects.
   */
  @Test
  public void testEqualsTrue() {
    Fruit fruit1 = new Fruit(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit2 = new Fruit(new Vector(0, 0), new Vector(32, 32));
   
    assertEquals(fruit1, fruit2);
  }
  
  /**
   * Test the equals method with two different objects.
   */
  @Test
  public void testEqualsFalse() {
    Fruit fruit1 = new Fruit(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit2 = new Fruit(new Vector(1, 1), new Vector(32, 32));
   
    assertFalse(fruit1.equals(fruit2));
  }
  
  /**
   * Test the equals method with two different object classes.
   */
  @Test
  public void testEqualsOtherObject() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
   
    assertFalse(fruit.equals(npc));
  }

}