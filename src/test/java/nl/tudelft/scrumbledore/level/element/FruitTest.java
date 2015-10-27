package nl.tudelft.scrumbledore.level.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.sprite.Sprite;

/**
 * Test case for the Fruit class.
 * 
 * @author Niels Warnars
 */
@SuppressWarnings("PMD.TooManyMethods")
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
  
  /**
   * Test the getter/setter method for the 'pickable' attribute.
   */
  @Test
  public void testIsPickable() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    fruit.setPickable(true);
    assertTrue(fruit.isPickable());
  }
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public void testGetSprites() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    ArrayList<Sprite> sprites = fruit.getSprites(1);
    
    assertEquals(1, sprites.size());
    assertEquals("apple", sprites.get(0).getID());
    assertEquals("images" + File.separator + "sprites" + File.separator 
        + "fruit/apple.png", sprites.get(0).getPath());
  }
  
  /**
   * Perform a dummy test to make sure that the action returned by Fruit.getLastMove
   * is always 'null'.
   */
  @Test
  public void testGetLastMove() {
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    fruit.removeAction(LevelElementAction.MoveRight);
    fruit.setLastMove(LevelElementAction.MoveRight);
    assertNull(fruit.getLastMove());
  }
  
}