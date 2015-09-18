package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the Game class.
 * 
 * @author Floris Doolaard
 *
 */
public class GameTest {
  private Game g1;
  private Level l1;
  private Level l2;
  private ArrayList<Level> list1;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void before() {
    l1 = new Level();
    l2 = new Level();
    list1 = new ArrayList<Level>();
    list1.add(l1);
    list1.add(l2);
    g1 = new Game(list1);
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testRemainingLevels() {

    assertEquals(l1, g1.getCurrentLevel());
    assertEquals(l1, g1.getLevels().get(0));
    assertEquals(1, g1.remainingLevels());
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testGoToNextLevel() {

    assertEquals(l1, g1.getCurrentLevel());
    assertEquals(1, g1.remainingLevels());
    g1.goToNextLevel();
    assertEquals(l2, g1.getCurrentLevel());
    assertEquals(0, g1.remainingLevels());
  }

  /**
   * Test the restart() method.
   */
  @Test
  public void testRestart() {
    assertEquals(1, g1.remainingLevels());
    
    g1.goToNextLevel();
    assertEquals(0, g1.remainingLevels());  
  
    g1.restart();
    assertTrue(g1.remainingLevels() > 0);
  }
    
  /**
   * Deleting test properties after testing.
   * 
   * @throws Exception
   */
  @After
  public void tearDownAfterClass() {
    l1 = null;
    l2 = null;
    list1 = null;
    g1 = null;
  }

}
