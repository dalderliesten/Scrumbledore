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
  private File loggingDir;
  private File loggingFile;

  private static volatile Logger instance;

  /**
   * Logger constructor, which should be used only once since this is a Singleton class.
   */
  private Logger() {
    createLoggingDir();
    createLoggingFile();
  }

  /**
   * Creates a new Logger instance if it has not yet been instantiated.
   * 
   * @return The single Logger instance.
   */
  public static Logger getInstance() {
    if (instance == null) {
      synchronized (Logger.class) {
        if (instance == null) {
          instance = new Logger();
        }
      }
    }
    return instance;
  }

  /**
   * Create the directory in which logging files should be stored if it does not exists yet on the
   * file system.
   */
  private void createLoggingDir() {
    File loggingDir = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR);
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
  }

  /**
   * Creates a new Logging File for this session where all logged lines should be written to.
   */
  private void createLoggingFile() {
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
  }

  /**
   * Allows the caller to log the passed string in the current session's logging file.
   * 
   * @param toLog
   *          The content that the caller wishes to be logged in the logging file.
   */
  public void log(String toLog) {
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
