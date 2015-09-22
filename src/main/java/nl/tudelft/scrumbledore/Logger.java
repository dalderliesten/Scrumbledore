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
  private static File loggingDir;
  private static File loggingFile;

  // A boolean to ensure that logging is only done if the file has been created.
  private static boolean started = false;

  /**
   * Logger constructor. Is not used as the class is a utility class, and should not be
   * instantiated.
   */
  private Logger() {

  }

  /**
   * Starts the logging by creating the logging file upon request.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public static void start() {
    loggingDir = new File(Constants.RESOURCES_DIR + Constants.LOGGER_DIR);

    if (!loggingDir.exists()) {
      loggingDir.mkdir();
    }

    try {
      // Fetching the current date for the creation of the file and parsing it to allow for simple
      // formatting.
      Date currentDate = new Date();
      SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM.dd-hh-mm-ss");

      String desiredFileName = "Session-" + simpleFormat.format(currentDate) + ".log";
      loggingFile = new File(Constants.RESOURCES_DIR + Constants.LOGGER_DIR + desiredFileName);

      BufferedWriter buffWriter = new BufferedWriter(new FileWriter(loggingFile));
      buffWriter.write("--------------------SCRUMBLEDORE LOGGING FILE");

      // Closing the stream as both an optimization and as a bug removing technique, as closing it
      // always writes content and prevents buffer overflow-type errors.
      buffWriter.close();
    } catch (IOException e) {
      System.out.println(e);
    }

    started = true;
  }

  /**
   * Allows the caller to log the passed string in the current sessions' loging file.
   * 
   * @param toLog
   *          The content that the caller wishes to be logged in the logging file.
   */
  public static void log(String toLog) {
    if (started) {
      try {
        BufferedWriter buffWriter = new BufferedWriter(new FileWriter(loggingFile, true));

        // Adds a new line to ensure that each log item takes up a new line.
        buffWriter.newLine();
        buffWriter.write(toLog);

        // Closing the stream as both an optimization and as a bug removing technique, as closing it
        // always writes content and prevents buffer overflow-type errors.
        buffWriter.close();
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }

}
