package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;

/**
 * Test suite for the LevelParser class.
 * 
 * @author Niels Warnars
 */
@SuppressWarnings("checkstyle:JUnitTestContainsTooManyAsserts")
public class LevelParserTest {
  private static final double B_ONE = 0;
  private static final double B_TWO = B_ONE + Constants.BLOCKSIZE;
  private static final double B_THREE = B_TWO + Constants.BLOCKSIZE;

  private static final Vector SIZE = new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE);

  /**
   * Test case for the loadLevelsFromDisk method. Two dummy levels are loaded from disk and
   * consequently checked for the presence of expected level elements.
   */
  @Test
  public void testLoadLevelsFromDisk() {
    LevelParser lp = new LevelParser();
    ArrayList<Level> levels = lp.loadLevelsFromDisk("src/main/resources/test");

    ArrayList<Platform> platformsLevel1 = levels.get(0).getPlatforms();

    // Check whether level 1 only contains 1 Platform element
    assertEquals(platformsLevel1.size(), 1);
    assertEquals(new Platform(new Vector(B_ONE, B_ONE), SIZE), platformsLevel1.get(0));

    // Check whether level 2 only contains 1 Player element
    assertEquals(new Player(new Vector(B_ONE, B_ONE), SIZE), levels.get(1).getPlayers().get(0));
  }

  /**
   * Test case for the listFilesInDir in which a the existence of two dummy files is checked.
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
   * Test case in which the scenario is tested where the file that is being read does not exist and
   * an exception is being thrown.
   */
  @Test
  public void testFileNotFound() {
    LevelParser lp = new LevelParser();
    try {
      lp.readLevelFromFile("dummy.txt");
    } catch (FileNotFoundException e) {
      assertEquals(e.getClass(), FileNotFoundException.class);
    }
  }

  /**
   * Test case for readFromScanner method in which a test map is being parsed for validation.
   */
  @Test
  public void testReadLevelFromScanner() {
    LevelParser lp = new LevelParser();
    Level level = new Level();
    String testMap = " #_\n" + "PNF\n";

    Scanner sc = new Scanner(testMap);

    try {
      level = lp.readLevelFromScanner(sc);
    } catch (FileNotFoundException e) {
      assertEquals(e.getClass(), FileNotFoundException.class);
    }

    ArrayList<NPC> npcs = level.getNPCs();
    ArrayList<Platform> platforms = level.getPlatforms();

    // Check lengths of array lists
    assertEquals(platforms.size(), 2);
    assertEquals(npcs.size(), 1);

    assertEquals(new Platform(new Vector(B_TWO, B_ONE), SIZE), platforms.get(0));

    // Assess the passable platform
    Platform passablePlatform = new Platform(new Vector(B_THREE, B_ONE), SIZE);
    passablePlatform.setPassable(true);
    assertEquals(passablePlatform, platforms.get(1));

    assertEquals(new Player(new Vector(B_ONE, B_TWO), SIZE), level.getPlayers().get(0));
    assertEquals(new NPC(new Vector(B_TWO, B_TWO), SIZE), npcs.get(0));
  }

  /**
   * Test case for getElementFromChar method in which it is checked whether the correct objects are
   * being returned.
   */
  @Test
  public void testGetElementFromChar() {
    LevelParser lp = new LevelParser();

    // Check for Platform match
    Platform platform = (Platform) lp.getElementFromChar('#', 0, 0);
    assertEquals(new Platform(new Vector(B_ONE, B_ONE), SIZE), platform);

    // Check for passable Platform match
    Platform passablePlatform = (Platform) lp.getElementFromChar('_', 0, 0);
    Platform passablePlatformExpected = new Platform(new Vector(B_ONE, B_ONE), SIZE);
    passablePlatformExpected.setPassable(true);

    assertEquals(passablePlatformExpected, passablePlatform);

    // Check for Player match
    Player player = (Player) lp.getElementFromChar('P', 0, 0);
    assertEquals(new Player(new Vector(B_ONE, B_ONE), SIZE), player);

    // Check for NPC match
    NPC npc = (NPC) lp.getElementFromChar('N', 0, 0);
    assertEquals(new NPC(new Vector(B_ONE, B_ONE), SIZE), npc);

    // Check for Fruit match
    Fruit fruit = (Fruit) lp.getElementFromChar('F', 0, 0);
    assertEquals(new Fruit(new Vector(B_ONE, B_ONE), SIZE), fruit);

    // Check for null if space is given
    assertNull(lp.getElementFromChar(' ', 0, 0));
  }

  /**
   * Test case for getBlockPosition method that validates whether the correct position calculated
   * position is being returned.
   */
  @Test
  public void testGetBlockPosition() {
    LevelParser lp = new LevelParser();
    assertEquals(new Vector(B_ONE, B_ONE), lp.getBlockPosition(0, 0));
    assertEquals(new Vector(B_TWO, B_TWO), lp.getBlockPosition(1, 1));
  }

  /**
   * Test whether the getLevels method returns the desired levels.
   */
  @Test
  public void testGetLevels() {
    LevelParser lp = new LevelParser("src/main/resources/test");
    ArrayList<Level> levels = lp.getLevels();

    Platform platform = new Platform(new Vector(0, 0),
        new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));
    Player player = new Player(new Vector(0, 0),
        new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));

    // Check whether two test levels are available
    assertEquals(2, levels.size());

    // Check whether level 1 contains a platform and level 2 doesn't
    assertEquals(platform, levels.get(0).getPlatforms().get(0));
    assertEquals(new ArrayList<Platform>(), levels.get(1).getPlatforms());

    // Check whether level 1 contains a player and level 2 doesn't
    assertEquals(0, levels.get(0).getPlayers().size());
    assertEquals(player, levels.get(1).getPlayers().get(0));
  }
}
