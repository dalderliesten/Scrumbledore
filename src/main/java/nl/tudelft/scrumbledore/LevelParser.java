package nl.tudelft.scrumbledore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class responsible for reading a map from disk
 * and converting it into a Level object.
 * 
 * @author Niels Warnars
 */
public class LevelParser {
  private Level level;
  private final int BLOCKSIZE = 19;
  
  /**
   * Creates a new LevelParser.
   */
  public LevelParser() {
    level = new Level();
  }
  
  /**
   * 
   * @param inFile
   *           File name of level layout on disk.
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   */
  public void read(String inFile) throws FileNotFoundException {
    readFromScanner(new Scanner(new File(inFile)));
  }
  
    /**
   * 
   * @param lineScanner
   *           A given Scanner object
   * @throws FileNotFoundException
   *           Exception thrown in case the file does not exist
   */
  public void readFromScanner(Scanner lineScanner) throws FileNotFoundException {
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
          level.addElement(getElementFromChar(ch, idx, lineNumber));
        }
      } 

      // Increment the current line
      lineNumber++;
    } while (lineScanner.hasNextLine());
    lineScanner.close();
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
  public LevelElement getElementFromChar(char ch, int i, int j) {
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
  public int getMiddleOfBlock(int blockSize) {
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
    int middleOfBlock = getMiddleOfBlock(BLOCKSIZE);
    
    int x = i * BLOCKSIZE + middleOfBlock;
    int y = j * BLOCKSIZE + middleOfBlock;
    
    return new Vector(x, y);
  }
  
  /**
   * Returns a Level objects.
   * 
   * @return
   *        A level object
   */
  public Level getLevel() {
    return level;
  }
  
  /**
   * Returns the size of a level element.
   * 
   * @return
   *        The size of a level element
   */
  public int getBlockSize() {
    return BLOCKSIZE;
  }
  
}
