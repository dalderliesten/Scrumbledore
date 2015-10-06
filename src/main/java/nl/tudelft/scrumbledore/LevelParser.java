package nl.tudelft.scrumbledore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for reading a map from disk and converting it into a Level object.
 * 
 * @author Niels Warnars
 */
public class LevelParser {
  private ArrayList<Level> levels;

  /**
   * Creates a new LevelParser.
   */
  public LevelParser() {
    levels = loadLevelsFromDisk(Constants.LEVELS_DIR);
  }

  /**
   * Creates a new LevelParser and loads levels from a user defined path.
   * 
   * @param dir
   *          A given directory
   */
  public LevelParser(String dir) {
    levels = loadLevelsFromDisk(dir);
  }

  /**
   * Loads an array of Level objects from disk.
   * 
   * @param dir
   *          A given directory
   * @return An array of Level objects
   */
  protected ArrayList<Level> loadLevelsFromDisk(String dir) {
    ArrayList<String> levelFiles = listFilesInDir(dir);
    ArrayList<Level> levels = new ArrayList<Level>();

    for (int idx = 0; idx < levelFiles.size(); idx++) {
      try {
        levels.add(readLevelFromFile(dir + "/" + levelFiles.get(idx)));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    return levels;
  }

  /**
   * Lists all files in a given directory.
   * 
   * @param dir
   *          A given directory
   * @return A list of file names
   */
  protected ArrayList<String> listFilesInDir(String dir) {
    ArrayList<String> levelFiles = new ArrayList<String>();
    File resourceFolder = new File(dir);
    File[] files = resourceFolder.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isFile()) {
          levelFiles.add(file.getName());
        }
      }
    }
    return levelFiles;
  }

  /**
   * Reads a level object from a given file.
   * 
   * @param inFile
   *          File name of level layout on disk.
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   * @return A level object
   */
  protected Level readLevelFromFile(String inFile) throws FileNotFoundException {
    return readLevelFromScanner(new Scanner(new File(inFile), "UTF-8"));
  }

  /**
   * Reads a level object from a given scanner.
   * 
   * @param lineScanner
   *          A given Scanner object
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   * @return A level object
   */
  protected Level readLevelFromScanner(Scanner lineScanner) throws FileNotFoundException {
    Level tmpLevel = new Level();
    int lineNumber = 0;

    do {
      String line = lineScanner.nextLine();

      // A Scanner cannot be used to iterate over
      // the chars in a line, so a for loop has
      // to be used.
      for (int idx = 0; idx < line.length(); idx++) {
        char ch = line.charAt(idx);

        // Since blank spaces correspond to no element at the given position.
        if (ch != ' ') {
          tmpLevel.addElement(getElementFromChar(ch, idx, lineNumber));
        }
      }

      lineNumber++;
    } while (lineScanner.hasNextLine());
    lineScanner.close();

    return tmpLevel;
  }

  /**
   * Returns an instance of a LevelElement child based on the corresponding caracter in the level
   * map.
   * 
   * @param ch
   *          Character representing a LevelElement.
   * @param i
   *          Horizontal position in level map on disk
   * @param j
   *          Vertical position in level map on disk
   * @return An instance of a LevelElement child.
   */
  protected LevelElement getElementFromChar(char ch, int i, int j) {
    Vector blockPos = getBlockPosition(i, j);
    Vector size = new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE);
    switch (ch) {
    case '#':
      return new Platform(blockPos, size);
    case '_':
      Platform platform = new Platform(blockPos, size);
      platform.setPassable(true);
      return platform;
    case 'P':
      return new Player(blockPos, size);
    case 'N':
      return new NPC(blockPos, size);
    case 'F':
      return new Fruit(blockPos, size);
    default:
      return null;
    }
  }

  /**
   * Gets the screen position of a block based on given coordinates in the level map on disk.
   * 
   * @param i
   *          Horizontal position in level map on disk
   * @param j
   *          Vertical position in level map on disk
   * @return Screen position of a block
   */
  public Vector getBlockPosition(int i, int j) {
    double x = i * Constants.BLOCKSIZE;
    double y = j * Constants.BLOCKSIZE;

    return new Vector(x, y);
  }

  /**
   * Returns an ArrayList of Level objects.
   * 
   * @return An ArrayList of level objects
   */
  public ArrayList<Level> getLevels() {
    return levels;
  }

}
