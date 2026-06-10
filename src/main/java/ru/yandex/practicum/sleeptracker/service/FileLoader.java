package ru.yandex.practicum.sleeptracker.service;

import ru.yandex.practicum.sleeptracker.customExceptions.LoaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.sleeptracker.customExceptions.Logger.logging;

public class FileLoader {
  private static final int TIME_LOG_LENGTH = 14;

  public static List<String> loadFile(String fileName) {
    Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", fileName);
    logging("Загружаем файл " + fileName + " по адрссу: " + path);

    List<String> list = new ArrayList<>();
    try (BufferedReader bufferedReader =
        new BufferedReader(new FileReader(path.toFile(), StandardCharsets.UTF_8))) {

      list =
          bufferedReader.lines().toList().stream()
              .filter(
                  s -> {
                    String[] parts = s.split(";");
                    return parts.length == 3
                        & parts[0].length() == TIME_LOG_LENGTH
                        & parts[1].length() == TIME_LOG_LENGTH;
                  })
              .toList();
      logging("Файл загружен размер массива " + list.size());

      if (list.isEmpty()) {
        list = List.of();
        throw new LoaderException("Файл " + fileName + " пуст");
      }
    } catch (FileNotFoundException e) {
      logging("Не найден файл " + fileName, e, true);
    } catch (IOException e) {
      logging("Непридвиденная ошибка " + e.getMessage(), e, true);
    }
    return list;
  }
}
