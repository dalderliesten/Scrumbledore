package nl.tudelft.scrumbledore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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

  private static boolean started = false;

  /**
   * Logger constructor. Is not used as the class is a utility class, and should not be
   * instantiated.
   */
  private Logger() {
  }

  /**
   * Starts the logging by creating the logging file upon request and ensuring that all associated
   * logging directories exist.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public static void start() {
    loggingDir = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR);

    try {
      if (!loggingDir.exists()) {
        boolean result = loggingDir.mkdir();

        if (!result) {
          throw new IOException();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      Date currentDate = new Date();
      SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM.dd-hh-mm-ss");

      String desiredFileName = "Session-" + simpleFormat.format(currentDate) + ".log";
      loggingFile = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR + desiredFileName);

      BufferedWriter buffWriter = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream(loggingFile), "UTF-8"));
      buffWriter.write("--------------------SCRUMBLEDORE LOGGING FILE");

      buffWriter.close();
    } catch (IOException e) {
      System.out.println(e);
    }

    started = true;
  }

  /**
   * Allows the caller to log the passed string in the current sessions' logging file.
   * 
   * @param toLog
   *          The content that the caller wishes to be logged in the logging file.
   */
  public static void log(String toLog) {
    if (started) {
      try {
        BufferedWriter buffWriter = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(loggingFile, true), "UTF-8"));

        buffWriter.newLine();
        buffWriter.write(toLog);

        buffWriter.close();
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }

}
