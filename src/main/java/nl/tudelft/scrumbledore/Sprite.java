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

  /**
   * Constructs a new sprite instance with the given id to reference an image file.
   * 
   * @param id
   *          Reference to an image file.
   */
  public Sprite(String id) {
    this.id = id;
    this.ext = "png";
  }

  /**
   * Constructs a new sprite instance with the given id to reference an image file.
   * 
   * @param id
   *          Reference to an image file.
   * @param ext
   *          The extension of the image file.
   */
  public Sprite(String id, String ext) {
    this.id = id;
    this.ext = ext;
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
    return Constants.SPRITES_DIR + id + "." + ext;
  }

  /**
   * Get a JavaFX Image instance for this sprite.
   * 
   * @return An Image instance.
   */
  public Image getImage() {
    return new Image(getPath());
  }

}
