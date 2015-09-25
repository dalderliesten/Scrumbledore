package nl.tudelft.scrumbledore;

/**
 * A counter given to the Game class as an attribute.
 * 
 * @author Floris Doolaard
 * @author David Alderliesten
 */
public class ScoreCounter {
  private int score;
  private int highScore;

  /**
   * Contructing a a ScoreCounter object.
   *
   */
  public ScoreCounter() {
    // Initial score and highscore is 0.
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
    String toReturn = "";

    toReturn = toReturn + score;

    return toReturn;
  }

  /**
   * Updating the current score with additional score and update the high-score if needed.
   * 
   * @param addScore
   *          The score to be added to the total score.
   */
  public void updateScore(int addScore) {
    score += addScore;

    Logger.log("Player gained " + addScore + " points.");

    if (score > highScore) {
      highScore = score;
    }
  }

}