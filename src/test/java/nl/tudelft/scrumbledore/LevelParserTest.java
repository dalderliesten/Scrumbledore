package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

/**
 * Test suite for the LevelParser class.
 * 
 * @author Niels Warnars
 */
public class LevelParserTest {
  
  /**
   * Test case in which the scenario is tested
   * where the file that is being read does not
   * exist and an exception is being thrown.
   */
  @Test
  public void testFileNotFound() {
    LevelParser lp = new LevelParser();
    try {
      lp.read("C:\\dummy.txt");
    } catch (FileNotFoundException e) {
      assertEquals(e.getClass(), FileNotFoundException.class);
    }
  }

  
  /**
   * Test case for readFromScanner method.
   */
  @Test
  public void testReadFromScanner() {
    LevelParser lp = new LevelParser();
    
    String testMap = 
          " #\n"
        + "PNF\n";
    
    Scanner sc = new Scanner(testMap);
    
    try {
      lp.readFromScanner(sc);
    } catch (FileNotFoundException e) { }
    
    ArrayList<LevelElement> elements = lp.getLevel().getElements();
    assertEquals(elements.size(), 4);
    assertEquals(new Platform(new Vector(28, 9), null), elements.get(0));
    assertEquals(new Player(new Vector(9, 28), null), elements.get(1));
    assertEquals(new NPC(new Vector(28, 28), null), elements.get(2));
    assertEquals(new Fruit(new Vector(47, 28), null), elements.get(3));

  }
  
  /**
   * Test case for getElementFromChar method.
   */
  @Test
  public void testGetElementFromChar() {
    LevelParser lp = new LevelParser();
    
    // Check for Platform match
    Platform platform = (Platform) lp.getElementFromChar('#', 0, 0);
    assertEquals(new Platform(new Vector(9, 9), null), platform);

    // Check for Player match
    Player player = (Player) lp.getElementFromChar('P', 0, 0);
    assertEquals(new Player(new Vector(9, 9), null), player);

    // Check for NPC match
    NPC npc = (NPC) lp.getElementFromChar('N', 0, 0);
    assertEquals(new NPC(new Vector(9, 9), null), npc);
    
    // Check for Fruit match
    Fruit fruit = (Fruit) lp.getElementFromChar('F', 0, 0);
    assertEquals(new Fruit(new Vector(9, 9), null), fruit);
    
    // Check for null if space is given
    assertNull(lp.getElementFromChar(' ', 0, 0));
  }
 

  /**
   * Test case for getBlockPosition method.
   */
  @Test
  public void testGetBlockPosition() {
    LevelParser lp = new LevelParser();
    assertEquals(new Vector(9, 9), lp.getBlockPosition(0, 0));
    assertEquals(new Vector(28, 28), lp.getBlockPosition(1, 1));
  }
  
  
  /**
   * Test case for GetMiddleOfBlock method.
   */
  @Test 
  public void testGetMiddleOfBlock() {
    LevelParser lp = new LevelParser();
    assertEquals(-1, lp.getMiddleOfBlock(1));
    assertEquals(-1, lp.getMiddleOfBlock(2));
    assertEquals(1, lp.getMiddleOfBlock(3));
    assertEquals(9, lp.getMiddleOfBlock(19));
  }
}
