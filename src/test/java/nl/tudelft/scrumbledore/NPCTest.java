package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

//import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.Test;

/**
 * Testing the NPC class.
 * 
 * @author Floris Doolaard
 *
 */
public class NPCTest extends LevelElementTest {

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * Deleting test properties after testing.
   * 
   * @throws Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new NPC(position, size);
  }

  
  /**
   * Test case for the getIndexFromPos that checks whether
   * the returned index matches the actual position of the
   * intended Platform object.
   */
  @Test
  public void testGetIndexFromPos() {
    Level level = new Level();
    String testMap = " N  \n" + " ## \n";

    try {
      level = (new LevelParser()).readLevelFromScanner(new Scanner(testMap));
    } catch (FileNotFoundException e) { }
    
    Vector vt = new Vector(2 * Constants.BLOCKSIZE, Constants.BLOCKSIZE);
    assertEquals(1, NPC.getIndexFromPos(level.getPlatforms(), vt));    
  }
  
  /**
   * Test case for the getUnderlayingPlatformsBoundaries method
   * which checks whether the platform boundaries returned are
   * the correct ones.
   */
  @Test
  public void testGetUnderlayingFloorBoundaries() {
    Level level = new Level();
    String testMap = "   N    \n" + "# #### #\n";

    try {
      level = (new LevelParser()).readLevelFromScanner(new Scanner(testMap));
    } catch (FileNotFoundException e) { }

    NPC npc = (NPC) level.getMovingElements().get(0);
    Vector[] boundaries = npc.getUnderlayingFloorBoundaries(level.getPlatforms());
    
    // Check left and right boundaries
    assertEquals(new Vector(2 * Constants.BLOCKSIZE, Constants.BLOCKSIZE), boundaries[0]);  
    assertEquals(new Vector(5 * Constants.BLOCKSIZE, Constants.BLOCKSIZE), boundaries[1]);    
  }
}
