package nl.tudelft.scrumbledore;

import java.io.BufferedWriter;
import java.io.File;

/**
 * Class for the maintainence and creation of logging files, which track movement and actions in
 * every session and store them.
 * 
 * @author David Alderliesten
 */
public class Logger {
  File loggingFile;
  BufferedWriter logWriter;

  /**
   * Logger constructor for the creation of the logging file for this session, along with all the
   * needed writers and file functions.
   */
  public Logger() {
    loggingFile = new File(Constants.LOGGER_DIR);
    logWriter = null;
  }
}
