package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

/**
 * Test suite for the Logger class.
 * 
 * @author Niels Warnars
 */
public class LoggerTest {

  /**
   * Test whether only one instance of a Logger can exist.
   */
  @Test
  public final void testGetInstance() {
    Logger logger = Logger.getInstance();
    Logger logger2 = Logger.getInstance();
    
    assertEquals(logger, logger2);
  }

  /**
   * Test whether the correct logging directory has been created.
   */
  @Test
  public final void testLoggingDir() {
    Logger.getInstance();
    
    File loggingDir = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR);
    assertTrue(loggingDir.exists());
  }
  
  
  /**
   * Test whether the correct logging file has been created.
   */
  @Test
  public final void testLoggingFile() {
    Logger.getInstance();
    
    Date currentDate = new Date();
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM.dd-hh-mm-ss");
    
    String desiredFileName = "Session-" + simpleFormat.format(currentDate) + ".log";
    File loggingFile = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR + desiredFileName);
    
    assertTrue(loggingFile.exists());
  }
  
  /**
   * Test whether the desired data is written to disk when the Logger.log() method 
   * is being called.
   */
  @Test
  public final void testLog() {
    Logger logger = Logger.getInstance();
    
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM.dd-hh-mm-ss");
    String desiredFileName = "Session-" + simpleFormat.format(new Date()) + ".log";
    File loggingFile = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR + desiredFileName);

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
