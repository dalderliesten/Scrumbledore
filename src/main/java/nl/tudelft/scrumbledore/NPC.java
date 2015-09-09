package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {
  private int life;
  private int status;
  private boolean hasFruit;
  
  /**
   * Create a new NPC instance.
   * 
   * @param position
   *          Position of the NPC in the level.
   * @param size
   *          Size of the NPC.
   */
  public NPC(Vector position, Vector size) {
    super(position, size);
    
    setGravity(true);
  }

  
  /**
   * Helper method used to determine the position of 
   * a Platform in an ArrayList<Platform> based on
   * a given position.
   * 
   * @param platforms
   *          An ArrayList of Platforms
   * @param pos
   *          The position of a certain Platform
   * @return
   *          The index of a certain Platform in an
   *          ArrayList
   */
  protected static int getIndexFromPos(ArrayList<Platform> platforms, Vector pos) {
    for (int i = 0; i < platforms.size(); i++) {
      if (pos.equals(platforms.get(i).getPosition())) {
        return i;
      }
    }
    
    return -1;
  }
  
  
  /**
   * Returns a vector array with the left and right
   * boundaries of the underlying floor.
   * 
   * @param platforms
   *          An ArrayList of Platforms
   * @return
   *          A Vector array with the left and right
   *          boundaries of the underlying floor
   */
  protected Vector[] floorBoundaries(ArrayList<Platform> platforms) {
    Vector[] floorBoundaries = new Vector[2]; 
    Vector enemyPos = getPosition();
    Vector pUnderEnemyPos = Vector.sum(enemyPos, new Vector(0, Constants.BLOCKSIZE));
    
    // Get left most platform boundary
    for (int i = getIndexFromPos(platforms, pUnderEnemyPos); i > 0; i--) {
      Vector currentPlatformPos = platforms.get(i).getPosition();
      Vector previousPlatformPos = platforms.get(i - 1).getPosition();
      Vector expectedPreviousPlatformPos = Vector.difference(currentPlatformPos, new Vector(Constants.BLOCKSIZE, 0));

      if (!previousPlatformPos.equals(expectedPreviousPlatformPos)) {
        floorBoundaries[0] = currentPlatformPos;
        break;
      }
    }
    
    // Get right most platform boundary
    for (int i = getIndexFromPos(platforms, pUnderEnemyPos); i < platforms.size(); i++) {
      Vector currentPlatformPos = platforms.get(i).getPosition();
      Vector nextPlatformPos = platforms.get(i + 1).getPosition();
      Vector expectedNextPlatformPos = Vector.sum(currentPlatformPos, new Vector(Constants.BLOCKSIZE, 0));

      if (!nextPlatformPos.equals(expectedNextPlatformPos)) {
        floorBoundaries[1] = currentPlatformPos;
        break;
      }
    }
    
    return floorBoundaries;
  }
  
  
  /**
   * Returns an ArrayList of platform objects with 
   * the same height.
   * 
   * @param platforms
   *            An ArrayList of platform objects
   * @return
   *            An ArrayList of platform objects
   *            with the same height
   */
  protected ArrayList<Platform> getSameHeightPlatforms(ArrayList<Platform> platforms) {
    ArrayList<Platform> sameHeightPlatforms = new ArrayList<Platform>();
    
    for (Platform platform : platforms) {
      if (this.getPosition().getY() == platform.getPosition().getY()) {
        sameHeightPlatforms.add(platform);
      }
    }
    
    return sameHeightPlatforms;
  }
  
  /**
   * Returns a vector array with the left and right
   * obstacle boundaries.
   * 
   * @param platforms
   *          An ArrayList of Platforms
   * @return
   *          A Vector array with the left and right
   *          obstacle boundaries
   */
  protected Vector[] obstacleBoundaries(ArrayList<Platform> platforms) {
    Vector[] boundaries = new Vector[2]; 
    ArrayList<Platform> sameHeightPlatforms = getSameHeightPlatforms(platforms);

    boundaries[0] = new Vector(0, getPosition().getY());
    boundaries[1] = new Vector(Constants.NUM_BLOCKS * Constants.BLOCKSIZE, getPosition().getY());
    
    // Get left obstacle boundary
    for (Platform platform : sameHeightPlatforms) {
      double platformX = platform.getPosition().getX();
      if (platformX < getPosition().getX() && platformX > boundaries[0].getX()) {
        boundaries[0] = platform.getPosition();
      }
    }
    
    // Get right obstacle boundary
    for (Platform platform : sameHeightPlatforms) {
      double platformX = platform.getPosition().getX();
      if (platformX > getPosition().getX() && platformX < boundaries[1].getX()) {
        boundaries[1] = platform.getPosition();
      }
    }
    
    return boundaries;
  }
  
  
  /**
   * 
   * @param platforms
   * @return
   */
  protected Vector[] getBoundaries(ArrayList<Platform> platforms) {
    Vector[] boundaries = new Vector[2]; 
    Vector leftBoundary;
    Vector rightBoundary;
    
    Vector[] floorBoundaries = floorBoundaries(platforms);
    Vector[] obstacleBoundaries = obstacleBoundaries(platforms);

    // Construct left boundary
    // Construct right boundary
    
    return boundaries;    
  }
  
  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof NPC) {
      NPC that = (NPC) other;
      return (this.getPosition().equals(that.getPosition()) 
          && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }


  /**
   * Get the life of an NPC.
   * 
   * @return
   *        The life of an NPC
   */
  public int getLife() {
    return life;
  }


  /**
   * Set the life of an NPC.
   * 
   * @param life
   *        The life of an NPC
   */
  public void setLife(int life) {
    this.life = life;
  }


  /**
   * Get the status of an NPC.
   * 
   * @return
   *        The status of an NPC
   */
  public int getStatus() {
    return status;
  }


  /**
   * Set the status of an NPC.
   * 
   * @param status
   *        The status of an NPC
   */
  public void setStatus(int status) {
    this.status = status;
  }


  /**
   * Check whether an NPC has a fruit.
   * 
   * @return
   *        Whether an NPC has a fruit
   */
  public boolean hasFruit() {
    return hasFruit;
  }


  /**
   * Set whether an NPC has a fruit.
   * 
   * @param hasFruit
   *         Whether an NPC has a fruit.
   */
  public void setHasFruit(boolean hasFruit) {
    this.hasFruit = hasFruit;
  }
  
}
