package nl.tudelft.scrumbledore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class for the maintainence and creation of logging files, which track movement and actions in
 * every session and store them.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class Logger {
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
    File appDir = new File(Constants.APPDATA_DIR);
    File loggingDir = new File(Constants.APPDATA_DIR + Constants.LOGGER_DIR);

    boolean result = true;
    try {
      if (!appDir.exists()) {
        result = appDir.mkdir();
        if (!result) {
          throw new IOException();
        }
      }

      if (!loggingDir.exists()) {
        result = loggingDir.mkdir();
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

      BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
          loggingFile), "UTF-8"));
      buffWriter.write("--------------------SCRUMBLEDORE LOGGING FILE");

      buffWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
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
      BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
          loggingFile, true), "UTF-8"));

      buffWriter.newLine();
      buffWriter.write(toLog);

      buffWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the logging file.
   * 
   * @return The logging file
   */
  public File getLoggingFile() {
    return loggingFile;
  }

  /**
   * Returns all the lines currently in the Logging file.
   * 
   * @return ArrayList with current logger contents.
   */
  public ArrayList<String> getLines() {
    ArrayList<String> toReturn = new ArrayList();

    try {
      BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(
          loggingFile), "UTF-8"));

      String currentElement = null;

      while ((currentElement = fileReader.readLine()) != null) {
        toReturn.add(currentElement);
      }

      fileReader.close();
    } catch (IOException e) {
      System.out.println(e);
    }

    return toReturn;
  }
  
  /**
   * Fetches the first line of the current logger and returns it.
   * 
   * @return A string with the first line.
   */
  public String getFirstLine() {
    String toReturn = null;
    
    try {
      BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(
          loggingFile), "UTF-8"));

      toReturn = fileReader.readLine();

      fileReader.close();
    } catch (IOException e) {
      System.out.println(e);
    }
    
    return toReturn;
  }

  /**
   * Fetches the last line of the current logger and returns it.
   * 
   * @return A string with the last line.
   */
  public String getLastLine() {
    String toReturn = null;

    try {
      BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(
          loggingFile), "UTF-8"));
      
      String currentElement = null;

      while ((currentElement = fileReader.readLine()) != null) {
        toReturn = currentElement;
      }

      fileReader.close();
    } catch (IOException e) {
      System.out.println(e);
    }

    return toReturn;
  }

}
