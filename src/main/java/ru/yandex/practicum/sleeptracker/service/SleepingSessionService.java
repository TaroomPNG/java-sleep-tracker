package ru.yandex.practicum.sleeptracker.service;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static ru.yandex.practicum.sleeptracker.customExceptions.Logger.logging;

public class SleepingSessionService {

  public static List<SleepingSession> getSleepingSessionList(List<String> sleepingList) {
    return sleepingList.stream()
        .map(
            s -> {
              String[] parts = s.split(";");
              logging("Элемент " + Arrays.toString(parts));
              return new SleepingSession(parts[0], parts[1], QualityOfSleep.valueOf(parts[2]));
            })
        .collect(Collectors.toList());
  }
}
