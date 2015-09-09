package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing the NPC class.
 * 
 * @author Floris Doolaard
 * @author Niels Warnars
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
   * Test case for the floorMovementBoundaries method 
   * which checks whether the platform boundaries 
   * returned are the correct ones.
   */
  @Test
  public void testFloorBoundaries() {
    Level level = new Level();
    String testMap = "   N    \n" + "# #### #\n";

    try {
      level = (new LevelParser()).readLevelFromScanner(new Scanner(testMap));
    } catch (FileNotFoundException e) { }

    NPC npc = (NPC) level.getMovingElements().get(0);
    Vector[] boundaries = npc.floorMovementBoundaries(level.getPlatforms());
    
    // Check left and right boundaries
    assertEquals(new Vector(2 * Constants.BLOCKSIZE, 0), boundaries[0]);  
    assertEquals(new Vector(5 * Constants.BLOCKSIZE, 0), boundaries[1]);    
  }
  
  /**
   * Test case for the obstacleMovementBoundaries method 
   * which checks whether the platforms found left
   * and right from the NPC are the correct ones.
   */
  @Test
  public void testObstacleBoundaries() {
    Level level = new Level();
    String testMap = "# #  N # #\n";

    try {
      level = (new LevelParser()).readLevelFromScanner(new Scanner(testMap));
    } catch (FileNotFoundException e) { }

    NPC npc = (NPC) level.getMovingElements().get(0);
    Vector[] boundaries = npc.obstacleMovementBoundaries(level.getPlatforms());
    
    assertEquals(new Vector(3 * Constants.BLOCKSIZE, 0), boundaries[0]);  
    assertEquals(new Vector(6 * Constants.BLOCKSIZE, 0), boundaries[1]);   
  }
  
  
  /**
   * Test case for the movementBoundaries method
   * simulating an actual movement area.
   */
  @Test
  public void testMovementBoundaries() {
    Level level = new Level();
    String testMap = 
          "# # N  # #\n" 
        + "#######   \n"
        + "##########\n";


    try {
      level = (new LevelParser()).readLevelFromScanner(new Scanner(testMap));
    } catch (FileNotFoundException e) { }

    NPC npc = (NPC) level.getMovingElements().get(0);
    Vector[] boundaries = npc.movementBoundaries(level.getPlatforms());
    
    assertEquals(new Vector(3 * Constants.BLOCKSIZE, 0), boundaries[0]);  
    assertEquals(new Vector(6 * Constants.BLOCKSIZE, 0), boundaries[1]);       
  }
}
