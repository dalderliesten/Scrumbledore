package nl.tudelft.scrumbledore.sprite;

import java.util.ArrayList;

/**
 * Class representing an Animated Sprite. Has multiple sprites as its animation frames.
 * 
 * @author Jesse Tilro
 */
public class AnimatedSprite {
  private String id;
  private ArrayList<Sprite> frames;
  private double interval;

  /**
   * Construct a new AnimatedSprite.
   * 
   * @param id
   *          The ID to be assigned to this animated sprite.
   *          
   * @param interval
   *          The number of (partial) steps between each frame.
   */
  public AnimatedSprite(String id, double interval) {
    this.id = id;
    this.frames = new ArrayList<Sprite>();
    this.interval = interval;
  }

  /**
   * Construct a new AnimatedSprite given a predefined sequence of frames.
   * 
   * @param id
   *          The ID to be assigned to this animated sprite.
   *          
   * @param interval
   *          The number of (partial) steps between each frame.
   *          
   * @param frames
   *          The frames the animated sprite should consist of.
   */
  public AnimatedSprite(String id, double interval, ArrayList<Sprite> frames) {
    this.id = id;
    this.frames = frames;
    this.interval = interval;
  }

  /**
   * Add a Sprite instance as the next frame in the sequence to this Animated Sprite.
   * 
   * @param frame
   *          The next frame in the sequence.
   */
  public void addFrame(Sprite frame) {
    frames.add(frame);
  }

  /**
   * Get the current frame of the Animated Sprite, based on the given amount of steps that have
   * passed since the Game was started.
   * 
   * @param steps
   *          The number of steps passed so far.
   *          
   * @return The current frame in the animation.
   */
  public Sprite getFrame(double steps) {
    return frames.get(getIndex(steps));
  }

  /**
   * Computes the index of the current frame given the exact absolute number of steps passed in the
   * Game.
   * 
   * @param steps
   *          The number of steps passed so far.
   *          
   * @return The index of the current frame.
   */
  private int getIndex(double steps) {
    double cycle = frames.size() * interval;
    double currentCycle = steps % cycle;
    int index = (int) Math.floor(currentCycle / interval);
    return index;
  }

  /**
   * Get the ID of this Animated Sprite.
   * 
   * @return The ID.
   */
  public String getID() {
    return id;
  }

  /**
   * Get the current interval of steps between two frames.
   * 
   * @return The interval.
   */
  public double getInterval() {
    return interval;
  }

  /**
   * Set the current interval of steps between two frames.
   * 
   * @param interval
   *          The interval.
   */
  public void setInterval(double interval) {
    this.interval = interval;
  }

}
