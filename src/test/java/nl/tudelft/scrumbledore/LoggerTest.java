package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Test suite for the Logger class.
 * 
 * @author David Alderliesten
 * @author Niels Warnars
 */
public class LoggerTest {
  private Logger logger;

  /**
   * Set-up the initial Game Factory.
   */
  @Before
  public void setUp() {
    logger = Logger.getInstance();
  }

  /**
   * Test whether only one instance of a Logger can exist.
   */
  @Test
  public final void testGetInstance() {
    Logger logger2 = Logger.getInstance();

    assertEquals(logger, logger2);
  }

  /**
   * Test whether the correct logging directory has been created.
   */
  @Test
  public final void testLoggingDir() {
    File loggingDir = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR);
    assertTrue(loggingDir.exists());
  }

  /**
   * Test whether the correct logging file has been created.
   */
  @Test
  public final void testLoggingFile() {
    File loggingFile = logger.getLoggingFile();

    assertTrue(loggingFile.exists());
  }

  /**
   * Test whether the desired data is written to disk when the Logger.log() method is being called.
   */
  @Test
  @SuppressWarnings("PMD.AvoidDuplicateLiterals")
  public final void testLog() {
    logger.log("log_write_test");
    
    ArrayList<String> fetchedLog = logger.getLines();

    assertEquals("--------------------SCRUMBLEDORE LOGGING FILE", fetchedLog.get(0));
    
    assertEquals("log_write_test", fetchedLog.get(fetchedLog.size() - 1));
  }

  /**
   * Test whether the getLines function works by checking if the returning list is not empty.
   */
  @Test
  public final void testGetLines() {
    logger.log("log_write_test");

    ArrayList<String> toTest = logger.getLines();

    assertFalse(toTest.isEmpty());
  }

  /**
   * Test whether the getFirstLine works by querying the method and comparing it to the default
   * first line.
   */
  @Test
  public final void testGetFirstLine() {
    String toCompare = logger.getFirstLine();
    assertEquals(toCompare, "--------------------SCRUMBLEDORE LOGGING FILE");
  }

  /**
   * Test whether the getLastLine function works by checking if the last element matches the final
   * element of the default log.
   */
  @Test
  public final void testGetLastLine() {
    String toCompare = logger.getLastLine();

    assertEquals(toCompare, "log_write_test");
  }

}
