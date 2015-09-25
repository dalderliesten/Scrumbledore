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
  private ArrayList<AnimatedSprite> animatedSprites;
  private String dir;
  private String dirSprite;

  /**
   * Construct a new Sprite Store by reading the Sprites from the file system.
   */
  public SpriteStore() {
    this.dir = Constants.RESOURCES_DIR + Constants.SPRITES_DIR;
    this.dirSprite = Constants.SPRITES_DIR;
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
    sprites = readSprites(directory, dirSprite);
    animatedSprites = readAnimatedSprites(directory, dirSprite);
  }

  /**
   * Get all Sprites read from the file system.
   * 
   * @param directory
   *          The directory to search for sprites.
   * @return All Sprites.
   */
  private ArrayList<Sprite> readSprites(File directory, String dir) {
    ArrayList<Sprite> spr = new ArrayList<Sprite>();
    File[] fileEntries = directory.listFiles();
    if (fileEntries != null) {
      for (final File fileEntry : fileEntries) {
        if (!fileEntry.isDirectory()) {
          spr.add(getSpriteFromFile(fileEntry, dir));
        }
      }
    }
    return spr;
  }

  /**
   * Get all Animated Sprites read from the file system.
   * 
   * @param directory
   *          The directory to search for animated sprites.
   * @return All Animated Sprites.
   */
  private ArrayList<AnimatedSprite> readAnimatedSprites(File directory, String dir) {
    ArrayList<AnimatedSprite> spr = new ArrayList<AnimatedSprite>();
    File[] fileEntries = directory.listFiles();
    if (fileEntries != null) {
      for (final File fileEntry : fileEntries) {
        if (fileEntry.isDirectory()) {
          String dirFrame = dir + fileEntry.getName() + "/";
          ArrayList<Sprite> frames = readSprites(fileEntry, dirFrame);
          double interval = Constants.ANIMATED_SPRITES_INTERVAL;
          AnimatedSprite animatedSprite = new AnimatedSprite(fileEntry.getName(), interval, frames);
          spr.add(animatedSprite);
        }
      }
    }
    return spr;
  }

  /**
   * Get a new Sprite instance based on the given image file.
   * 
   * @param file
   *          The image file that the Sprite should correspond to.
   * @param dir
   *          The path to the image file.
   * @return A Sprite.
   */
  private Sprite getSpriteFromFile(File file, String dir) {
    String name = file.getName();
    int pos = name.lastIndexOf('.');
    String id = name.substring(0, pos);
    String ext = name.substring(pos + 1);
    return new Sprite(id, ext, dir);
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
   * Get an animated sprite by its ID.
   * 
   * @param id
   *          The ID.
   * @return The Animated Sprite.
   */
  public AnimatedSprite getAnimated(String id) {
    for (AnimatedSprite spr : animatedSprites) {
      if (spr.getID().equals(id)) {
        return spr;
      }
    }
    return null;
  }

}
