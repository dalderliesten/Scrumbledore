package nl.tudelft.scrumbledore;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * The Sprite Store reads and creates Sprites from the file system and allows to easily select and
 * use them.
 * 
 * @author Jesse Tilro
 *
 */
public class SpriteStore {

  private ArrayList<Sprite> sprites;

  /**
   * Construct a new Sprite Store by reading the Sprites from the file system.
   */
  public SpriteStore() {
    read();
  }

  /**
   * Read Sprites from file system.
   */
  public void read() {
    sprites = new ArrayList<Sprite>();
    final File dir = new File(Constants.RESOURCES_DIR + Constants.SPRITES_DIR);
    for (final File fileEntry : dir.listFiles()) {
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
   * Get an Image instance of the Sprite with the given ID.
   * 
   * @param id
   *          The ID.
   * @return An Image.
   */
  public Image getImage(String id) {
    return get(id).getImage();
  }

}
