package nl.tudelft.scrumbledore.sprite;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * Represents a sprite that can be drawn in the GUI as a visual representation of a LevelElement.
 * 
 * @author Jesse Tilro
 * @author Niels Warnars
 */
public class Sprite {

  private String id;
  private String ext;
  private String dir;
  private Vector size;

  /**
   * Constructs a new sprite instance with the given id to reference an image file.
   * 
   * @param id
   *          Reference to an image file.
   */
  public Sprite(String id) {
    this.id = id;
    this.ext = "png";
    this.dir = Constants.SPRITES_DIR;
  }

  /**
   * Constructs a new sprite instance with the given id to reference an image file and the extension
   * of the file.
   * 
   * @param id
   *          Reference to an image file.
   * @param ext
   *          The extension of the image file.
   */
  public Sprite(String id, String ext) {
    this.id = id;
    this.ext = ext;
    this.dir = Constants.SPRITES_DIR;
  }

  /**
   * Constructs a new sprite instance with the given id to reference an image file, the extension of
   * the file and the directory to look for it.
   * 
   * @param id
   *          Reference to an image file.
   * @param ext
   *          The extension of the image file.
   * @param dir
   *          The directory to look for the file.
   */
  public Sprite(String id, String ext, String dir) {
    this.id = id;
    this.ext = ext;
    this.dir = dir;
  }

  /**
   * Constructs a new sprite instance with the given id to reference an image file, the extension of
   * the file, the directory to look for it and its size.
   * 
   * @param id
   *          Reference to an image file.
   * @param ext
   *          The extension of the image file.
   * @param dir
   *          The directory to look for the file.
   * @param size
   *          A Vector describing the dimensions of the image.
   */
  public Sprite(String id, String ext, String dir, Vector size) {
    this.id = id;
    this.ext = ext;
    this.dir = dir;
    this.size = size;
  }

  /**
   * Get the ID of this Sprite.
   * 
   * @return The ID.
   */
  public String getID() {
    return id;
  }

  /**
   * The the path to the file to be used for this sprite.
   * 
   * @return File path.
   */
  public String getPath() {
    return dir + id + "." + ext;
  }

  /**
   * Get the drawing position of the image given the absolute position where it's origin point
   * should be.
   * 
   * @param origin
   *          The absolute position for the origin of the sprite.
   * @return A Vector describing the drawing position.
   */
  public Vector getDrawPosition(Vector origin) {
    return Vector.sum(origin, Vector.scale(size, -.5));
  }

}
