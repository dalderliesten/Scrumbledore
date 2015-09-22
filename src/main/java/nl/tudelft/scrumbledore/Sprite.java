package nl.tudelft.scrumbledore;

import javafx.scene.image.Image;

/**
 * Represents a sprite that can be drawn in the GUI as a visual representation of a LevelElement.
 * 
 * @author Jesse Tilro
 *
 */
public class Sprite {

  private String id;
  private String ext;
  private String dir;

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
  
}
