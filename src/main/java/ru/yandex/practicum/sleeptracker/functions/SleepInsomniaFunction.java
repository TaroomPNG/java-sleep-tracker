package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SleepInsomniaFunction extends SleepFunction {

  @Override
  public SleepAnalysResult apply(List<SleepingSession> sessions) {

      LocalDateTime firstAsleep = sessions.getFirst().getAsleep();
      LocalDateTime lastWokeUp = sessions.getLast().getWokeUp();

      LocalDate startNight = firstAsleep.toLocalDate();
      LocalDate lastNight = lastWokeUp.toLocalDate();

      if (firstAsleep.getHour() >= 12) {
          startNight = startNight.plusDays(1);
      }


      Long insomniaCount = startNight
              .datesUntil(lastNight.plusDays(1))
              .filter(night ->
                  sessions.stream()
                          .noneMatch((session) ->
                              session.getAsleep().isBefore(night.atTime(6,0)) &&
                              session.getWokeUp().isAfter(night.atTime(0,0))
                          )
              ).count();

    return new SleepAnalysResult("Количество бессоных ночей", insomniaCount);
  }
}
