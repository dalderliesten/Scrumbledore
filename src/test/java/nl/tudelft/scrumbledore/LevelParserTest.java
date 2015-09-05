package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
  private static int bOneMid = (Constants.BLOCKSIZE - 1) / 2;
  private static int bTwoMid = bOneMid + Constants.BLOCKSIZE;
  private static int bThreeMid = bTwoMid + Constants.BLOCKSIZE;
  
  /**
   * Test case for the loadLevelsFromDisk method.
   * Two dummy levels are loaded from disk and
   * consequently checked for the presence of 
   * expected level elements.
   */
  @Test
  public void testLoadLevelsFromDisk() {
    LevelParser lp = new LevelParser();
    Level[] levels = lp.loadLevelsFromDisk("src/main/resources/test");

    ArrayList<LevelElement> elementsLevel1 = levels[0].getElements();
    ArrayList<LevelElement> elementsLevel2 = levels[1].getElements();
    
    // Check whether level 1 only contains 1 Platform element
    assertEquals(elementsLevel1.size(), 1);
    assertEquals(new Platform(new Vector(bOneMid, bOneMid), null), elementsLevel1.get(0));
    
    // Check whether level 2 only contains 1 Player element
    assertEquals(elementsLevel2.size(), 1);
    assertEquals(new Player(new Vector(bOneMid, bOneMid), null), elementsLevel2.get(0));
  }
  
  /**
   * Test case for the listFilesInDir in which
   * a the existence of two dummy files is 
   * checked.
   */
  @Test
  public void testListFilesInDir() {
    LevelParser lp = new LevelParser();
    ArrayList<String> testFiles = lp.listFilesInDir("src/main/resources/test");
    
    assertEquals(testFiles.size(), 2);
    assertTrue(testFiles.contains("level01.txt"));
    assertTrue(testFiles.contains("level02.txt"));
  }
  
  /**
   * Test case in which the scenario is tested
   * where the file that is being read does not
   * exist and an exception is being thrown.
   */
  @Test
  public void testFileNotFound() {
    LevelParser lp = new LevelParser();
    try {
      lp.readLevelFromFile("C:\\dummy.txt");
    } catch (FileNotFoundException e) {
      assertEquals(e.getClass(), FileNotFoundException.class);
    }
  }

  /**
   * Test case for readFromScanner method in
   * which a test map is being parsed for
   * validation.
   */
  @Test
  public void testReadLevelFromScanner() {
    LevelParser lp = new LevelParser();
    Level level = new Level();
    String testMap = 
          " #\n"
        + "PNF\n";
    
    Scanner sc = new Scanner(testMap);
    
    try {
      level = lp.readLevelFromScanner(sc);
    } catch (FileNotFoundException e) { }
    
    ArrayList<LevelElement> elements = level.getElements();
    assertEquals(elements.size(), 4);
    assertEquals(new Platform(new Vector(bTwoMid, bOneMid), null), elements.get(0));
    assertEquals(new Player(new Vector(bOneMid, bTwoMid), null), elements.get(1));
    assertEquals(new NPC(new Vector(bTwoMid, bTwoMid), null), elements.get(2));
    assertEquals(new Fruit(new Vector(bThreeMid, bTwoMid), null), elements.get(3));

  }
  
  /**
   * Test case for getElementFromChar method 
   * in which it is checked whether the correct
   * objects are being returned.
   */
  @Test
  public void testGetElementFromChar() {
    LevelParser lp = new LevelParser();
    
    // Check for Platform match
    Platform platform = (Platform) lp.getElementFromChar('#', 0, 0);
    assertEquals(new Platform(new Vector(bOneMid, bOneMid), null), platform);

    // Check for Player match
    Player player = (Player) lp.getElementFromChar('P', 0, 0);
    assertEquals(new Player(new Vector(bOneMid, bOneMid), null), player);

    // Check for NPC match
    NPC npc = (NPC) lp.getElementFromChar('N', 0, 0);
    assertEquals(new NPC(new Vector(bOneMid, bOneMid), null), npc);
    
    // Check for Fruit match
    Fruit fruit = (Fruit) lp.getElementFromChar('F', 0, 0);
    assertEquals(new Fruit(new Vector(bOneMid, bOneMid), null), fruit);
    
    // Check for null if space is given
    assertNull(lp.getElementFromChar(' ', 0, 0));
  }

  /**
   * Test case for getBlockPosition method that
   * validates whether the correct position 
   * calculated position is being returned.
   */
  @Test
  public void testGetBlockPosition() {
    LevelParser lp = new LevelParser();
    assertEquals(new Vector(bOneMid, bOneMid), lp.getBlockPosition(0, 0));
    assertEquals(new Vector(bTwoMid, bTwoMid), lp.getBlockPosition(1, 1));
  } 
  
  /**
   * Test case for GetMiddleOfBlock method in 
   * which it is determined whether the correct
   * expected center of the block is returned.
   */
  @Test 
  public void testGetMiddleOfBlock() {
    LevelParser lp = new LevelParser();
    assertEquals(-1, lp.getMiddleOfBlock(1));
    assertEquals(-1, lp.getMiddleOfBlock(2));
    assertEquals(1, lp.getMiddleOfBlock(3));
    assertEquals(bOneMid, lp.getMiddleOfBlock(Constants.BLOCKSIZE));
  }
}
