package nl.tudelft.scrumbledore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Class for the maintainence and creation of logging files, which track movement and actions in
 * every session and store them.
 * 
 * @author David Alderliesten
 */
public class Logger {
  // The logging dir and file are utilized for storing of the logging file.
  File loggingDir;
  File loggingFile;

  // The log writer is used to actually write content to the created file(s).
  BufferedWriter logWriter;

  /**
   * Logger constructor for the creation of the logging file for this session, along with all the
   * needed writers and file functions.
   */
  public Logger() {
    loggingDir = new File(Constants.RESOURCES_DIR + Constants.LOGGER_DIR);

    // Checking to see if the directory for the logs exists. If this is no the case, it will be
    // created.
    if (loggingDir.exists() == false) {
      loggingDir.mkdir();
    }
  }

}
