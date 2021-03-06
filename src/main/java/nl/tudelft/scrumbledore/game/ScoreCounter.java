package nl.tudelft.scrumbledore.game;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;

/**
 * A counter given to the Game class as an attribute that keeps track of the score.
 * 
 * @author Floris Doolaard
 * @author David Alderliesten
 */
public class ScoreCounter {
  private int score;
  private int highScore;

  /**
   * Constructing a ScoreCounter object.
   */
  public ScoreCounter() {
    this.score = 0;
    this.highScore = 0;
  }

  /**
   * Returns the current total score.
   * 
   * @return The current score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Returns the current high score.
   * 
   * @return The current high-score.
   */
  public int getHighScore() {
    return highScore;
  }

  /**
   * Returns the current total score in the form of a string.
   * 
   * @return The current score as a string.
   */
  public String getScoreString() {
    return Integer.toString(score);
  }

  /**
   * Updating the current score with additional score and update the high-score if needed.
   * 
   * @param addScore
   *          The score to be added to the total score.
   */
  public void updateScore(int addScore) {
    score += addScore;

    if (Constants.isLoggingWantPoints()) {
      Logger.getInstance().log(
          "Player gained " + addScore + " points, totalling at " + score + " points.");
    }

    if (score > highScore) {
      highScore = score;

      if (Constants.isLoggingWantPoints()) {
        Logger.getInstance().log(
            "The high-score has been changed and is now worth " + highScore + " points!");
      }
    }
  }

  /**
   * Resets the score to a value of 0 when called.
   */
  public void resetScore() {
    score = 0;
  }
}