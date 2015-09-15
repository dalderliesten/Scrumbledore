package nl.tudelft.scrumbledore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for the maintainence and creation of logging files, which track movement and actions in
 * every session and store them.
 * 
 * @author David Alderliesten
 */
public final class Logger {
  // The logging dir and file are utilized for storing of the logging file.
  static File loggingDir;
  static File loggingFile;

  /**
   * Logger constructor. Is not used as the class is a utility class, and should not be insantiated.
   */
  private Logger() {
  }

  /**
   * Starts the logging by creating the logging file upon request.
   */
  public static void start() {
    // Creating the directory location.
    loggingDir = new File(Constants.RESOURCES_DIR + Constants.LOGGER_DIR);

    // Checking to see if the directory for the logs exists. If this is no the case, it will be
    // created.
    if (loggingDir.exists() == false) {
      loggingDir.mkdir();
    }

    try {
      // Fetching the current date for the creation of the file and parsing it to allow for simple
      // formatting.
      Date currentDate = new Date();
      SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM.dd-hh-mm-ss");

      // Taking care of the file name and file creation.
      String desiredFileName = "Session-" + simpleFormat.format(currentDate) + ".log";
      loggingFile = new File(Constants.RESOURCES_DIR + Constants.LOGGER_DIR + desiredFileName);

      // Writing initial content to the file.
      BufferedWriter buffWriter = new BufferedWriter(new FileWriter(loggingFile));
      buffWriter.write("SCRUMBLEDORE LOGGING FILE");

      // Closing the stream as both an optimization and as a bug removing technique, as closing it
      // always writes content.
      buffWriter.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
