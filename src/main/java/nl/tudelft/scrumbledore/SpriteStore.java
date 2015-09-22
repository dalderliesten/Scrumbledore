package nl.tudelft.scrumbledore;

import java.io.File;
import java.util.ArrayList;

/**
 * The Sprite Store reads and creates Sprites from the file system and allows to easily select and
 * use them.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
public class SpriteStore {

  private ArrayList<Sprite> sprites;
  private String dir;

  /**
   * Construct a new Sprite Store by reading the Sprites from the file system.
   */
  public SpriteStore() {
    this.dir = Constants.RESOURCES_DIR + Constants.SPRITES_DIR;
    read();
  }

  /**
   * Construct a new Sprite Store by reading the Sprites from the file system, from a given
   * directory.
   * 
   * @param dir
   *          The directory where sprites should be loaded from.
   */
  public SpriteStore(String dir) {
    this.dir = dir;
    read();
  }

  /**
   * Read Sprites from file system.
   */
  public void read() {
    sprites = new ArrayList<Sprite>();
    final File directory = new File(dir);
    File[] fileEntries = directory.listFiles();
    if (fileEntries != null) {
      for (final File fileEntry : fileEntries) {
        if (!fileEntry.isDirectory()) {
          String name = fileEntry.getName();
          int pos = name.lastIndexOf('.');
          String id = name.substring(0, pos);
          String ext = name.substring(pos + 1);
          Sprite spr = new Sprite(id, ext);
          sprites.add(spr);
        }
      }
    }
  }

  /**
   * Return the ArrayList of sprites in the Sprite Store.
   * 
   * @return An ArrayList of Sprites.
   */
  public ArrayList<Sprite> getAll() {
    return sprites;
  }

  /**
   * Get a sprite by its ID.
   * 
   * @param id
   *          The ID.
   * @return The Sprite.
   */
  public Sprite get(String id) {
    for (Sprite spr : sprites) {
      if (spr.getID().equals(id)) {
        return spr;
      }
    }
    return null;
  }

  /**
   * Get the path of a Sprite based on a given ID.
   * 
   * @param id
   *          The ID.
   * @return The path of a Sprite
   */
  public String getPathFromID(String id) {
    return get(id).getPath();
  }

}
