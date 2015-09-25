package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing an Animated Sprite. Has multiple sprites as its animation frames.
 * 
 * @author Jesse Tilro
 *
 */
public class AnimatedSprite {

  private ArrayList<Sprite> frames;
  private double interval;

  /**
   * Construct a new AnimatedSprite.
   * 
   * @param interval
   *          The number of (partial) steps between each frame.
   */
  public AnimatedSprite(double interval) {
    this.frames = new ArrayList<Sprite>();
    this.interval = interval;
  }

  /**
   * Get the current frame of the Animated Sprite, based on the given amount of steps that have
   * passed since the Game was started.
   * 
   * @param steps
   *          The number of steps passed so far.
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
   * @return The index of the current frame.
   */
  private int getIndex(double steps) {
    double cycle = frames.size() * interval;
    double currentCycle = steps % cycle;

    return (int) Math.ceil(currentCycle / interval);
  }

}
