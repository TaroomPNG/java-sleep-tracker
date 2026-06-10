package ru.yandex.practicum.sleeptracker.customExceptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

  private static final String LOGS_DIRECTORY_NAME = "logs";
  private static final String LOGS_FILE_NAME = "logs.log";
  private static final Path PATH =
      Paths.get(System.getProperty("user.dir"), LOGS_DIRECTORY_NAME, LOGS_FILE_NAME);

  public static void logging(String message) {
    initLogs();

    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(PATH.toFile(), StandardCharsets.UTF_8, true))) {

      bufferedWriter.write(
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
              + " - "
              + message
              + System.lineSeparator());

    } catch (IOException e) {
      System.err.println("Ошибка инициализации файла логов " + e.getMessage());
    }
  }

  public static void logging(String message, Exception exception) {
    initLogs();

    try (BufferedWriter bufferedWriter =
        new BufferedWriter(new FileWriter(PATH.toFile(), StandardCharsets.UTF_8, true))) {

      bufferedWriter.write(
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
              + " - "
              + message
              + "\t"
              + exception.getMessage()
              + System.lineSeparator());

    } catch (IOException e) {
      System.err.println("Ошибка инициализации файла логов " + e.getMessage());
    }
  }

  public static void logging(String message, Exception exception, boolean OutputSerr) {
    initLogs();
    System.err.println(exception.getMessage());

    try (BufferedWriter bufferedWriter =
                 new BufferedWriter(new FileWriter(PATH.toFile(), StandardCharsets.UTF_8, true))) {

      bufferedWriter.write(
              LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                      + " - "
                      + message
                      + "\n -> "
                      + exception.getMessage()
                      + System.lineSeparator());

    } catch (IOException e) {
      System.err.println("Ошибка инициализации файла логов " + e.getMessage());
    }
  }

  public static void initLogs() {
    if (Files.notExists(PATH.getParent())) {
      try {
        Files.createDirectories(PATH.getParent());
      } catch (IOException e) {
        System.err.println("Ошибка инициализации папки логов " + e.getMessage());
      }
    }
  }
}
