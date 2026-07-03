package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.service.FileLoader;
import ru.yandex.practicum.sleeptracker.service.SleepingSessionService;

import java.util.*;
import java.util.function.Function;

import static ru.yandex.practicum.sleeptracker.customExceptions.Logger.logging;

public class SleepTrackerApp {

  public static void main(String[] args) {
    logging("Программа запушена");

    List<String> sleepLogList = requestFileLoader(new Scanner(System.in));
    List<SleepingSession> sleepingSessionList = requestSleepingList(sleepLogList);
    Set<Function<List<SleepingSession>, SleepAnalysResult>> functions = new LinkedHashSet<>();
    functions.add(new SleepCounterFunction());
    functions.add(new SleepMaxFunction());
    functions.add(new SleepMinFunction());
    functions.add(new SleepAvgDurationFunction());
    functions.add(new SleepBadCounterFunction());
    functions.add(new SleepInsomniaFunction());
    functions.add(new SleepTypeFunction());

    printFunctions(functions, sleepingSessionList);
  }

  private static List<SleepingSession> requestSleepingList(List<String> sessions) {
    logging("Начинаем заполнять List<SleepingSession>");

    return SleepingSessionService.getSleepingSessionList(sessions);
  }

  private static void printFunctions(
      Set<Function<List<SleepingSession>, SleepAnalysResult>> functions,
      List<SleepingSession> sleepingSessionList) {
    functions.forEach(
        f -> {
          SleepAnalysResult result = f.apply(sleepingSessionList);
          System.out.println(result.getDescription() + " -> " + result.getValue());
        });
  }

  private static List<String> requestFileLoader(Scanner scanner) {
    System.out.println("Введите наименования файла по шаблону -> <FileName.log>");
    String fileName = scanner.nextLine();
    if (fileName.isEmpty()) {
      fileName = "sleep_log.txt";
    }
    List<String> sleepLogList = FileLoader.loadFile(fileName);

    return sleepLogList.isEmpty() ? requestFileLoader(scanner) : sleepLogList;
  }
}
