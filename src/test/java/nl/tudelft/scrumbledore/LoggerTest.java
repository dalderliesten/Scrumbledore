package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.junit.Before;
import org.junit.Test;


/**
 * Test suite for the Logger class.
 * 
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
   * Test whether the desired data is written to disk when the Logger.log() method 
   * is being called.
   */
  @Test
  public final void testLog() {
    File loggingFile = logger.getLoggingFile();
    logger.log("log_write_test");
        
    try {
      BufferedReader reader 
        = new BufferedReader(new InputStreamReader(new FileInputStream(loggingFile), "UTF-8"));
      
      assertEquals("--------------------SCRUMBLEDORE LOGGING FILE", reader.readLine());
      assertEquals("log_write_test", reader.readLine());
      
      reader.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
