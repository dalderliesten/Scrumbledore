package nl.tudelft.scrumbledore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class responsible for reading a map from disk
 * and converting it into a Level object.
 * 
 * @author Niels Warnars
 */
public class LevelParser {
  private Level[] levels;
  private final String levelsDir = "src/main/resources/";
  
  /**
   * Creates a new LevelParser.
   */
  public LevelParser() {
    levels = loadLevelsFromDisk(levelsDir);
  }
  
  /**
   * Loads an array of Level objects from 
   * disk.
   * 
   * @param dir
   *            A given directory
   * @return
   *            An array of Level objects
   */
  protected Level[] loadLevelsFromDisk(String dir) {
    ArrayList<String> levelFiles = listFilesInDir(dir);
    Level[] levels = new Level[levelFiles.size()];
    
    for (int idx = 0; idx < levels.length; idx++) {
      try {
        levels[idx] = readLevelFromFile(dir + "/" + levelFiles.get(idx));
      } catch (FileNotFoundException e) { }
    }
    return levels;
  }
  /**
   * Lists all files in a given directory.
   * 
   * @param dir
   *          A given directory
   * @return
   *          A list of file names
   */
  protected ArrayList<String> listFilesInDir(String dir) {
    ArrayList<String> levelFiles = new ArrayList<String>();
    File resourceFolder = new File(dir);
    File[] files = resourceFolder.listFiles();
    
    for (File file : files) {
      if (file.isFile()) {
        levelFiles.add(file.getName());
      }  
    }
    
    return levelFiles;
  }
    
  /**
   * Reads a level object from a given file.
   * 
   * @param inFile
   *           File name of level layout on disk.
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   * @return    
   *           A level object
   */
  protected Level readLevelFromFile(String inFile) throws FileNotFoundException {
    return readLevelFromScanner(new Scanner(new File(inFile)));
  }
  
   /**
   * Reads a level object from a given scanner.
   * 
   * @param lineScanner
   *           A given Scanner object
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   * @return    
   *           A level object
   */
  protected Level readLevelFromScanner(Scanner lineScanner) throws FileNotFoundException {
    Level tmpLevel = new Level();
    int lineNumber = 0;
    
    do {
      String line = lineScanner.nextLine();

      // A Scanner cannot be used to iterate over
      // the chars in a line, so a for loop has 
      // to be used
      for (int idx = 0; idx < line.length(); idx++) {
        char ch = line.charAt(idx);
        
        // Add an element to a level if it's not 
        // a blank space.
        if (ch != ' ') { 
          tmpLevel.addElement(getElementFromChar(ch, idx, lineNumber));
        }
      } 

      // Increment the current line
      lineNumber++;
    } while (lineScanner.hasNextLine());
    lineScanner.close();
    
    return tmpLevel;
  }
  
  
  /**
   * Returns an instance of a LevelElement child
   * based on the corresponding caracter in the
   * level map.
   * 
   * @param ch
   *        Character representing a LevelElement.
   * @param i
   *        Horizontal position in level map on disk
   * @param j
   *        Vertical position in level map on disk
   * @return
   *        An instance of a LevelElement child.
   */
  protected LevelElement getElementFromChar(char ch, int i, int j) {
    Vector blockPos = getBlockPosition(i, j);
   
    switch (ch) {
      case '#':
        return new Platform(blockPos, null);
      case 'P':
        return new Player(blockPos, null);
      case 'N':
        return new NPC(blockPos, null);
      case 'F':
        return new Fruit(blockPos, null);
      default:
        return null;
    }
  }
  
  
  /**
   * Gets the middle of a block size if
   * its uneven.
   * 
   * @param blockSize
   *        The size of a block
   * @return
   *        The middle of a block size or
   *        -1 if the block size is even
   */
  protected int getMiddleOfBlock(int blockSize) {
    if (blockSize % 2 == 1 && blockSize > 2) {
      return Math.round(blockSize / 2);
    } 
    
    return -1;
  }
  
  
  /**
   * Gets the screen position of a block based
   * on given coordinates in the level map on
   * disk.
   * 
   * @param i
   *        Horizontal position in level map on disk
   * @param j
   *        Vertical position in level map on disk
   * @return
   *        Screen position of a block
   */
  public Vector getBlockPosition(int i, int j) {
    int middleOfBlock = getMiddleOfBlock(Constants.BLOCKSIZE);
    
    int x = i * Constants.BLOCKSIZE + middleOfBlock;
    int y = j * Constants.BLOCKSIZE + middleOfBlock;
    
    return new Vector(x, y);
  }
  
  /**
   * Returns an array of Level objects.
   * 
   * @return
   *        An array of level objects
   */
  public Level[] getLevels() {
    return levels;
  }
  
}
