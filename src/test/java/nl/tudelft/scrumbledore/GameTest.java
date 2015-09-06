package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
  private ArrayList<Level> list1;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void before() {
    l1 = new Level();
    list1 = new ArrayList<Level>();
    list1.add(l1);
    g1 = new Game(list1);
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testRemainingLevels() {
    
    assertEquals(l1, g1.getCurrentLevel());
    assertEquals(l1, g1.getLevels().get(0));
    assertEquals(0, g1.remainingLevels());
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testGoToNextLevel() {

  }

  /**
   * Deleting test properties after testing.
   * 
   * @throws Exception
   */
  @After
  public void tearDownAfterClass() {
  }

}
