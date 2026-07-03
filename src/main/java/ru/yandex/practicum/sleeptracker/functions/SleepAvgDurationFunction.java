package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;

import java.time.Duration;
import java.util.List;

public class SleepAvgDurationFunction extends SleepFunction {

  @Override
  public SleepAnalysResult apply(List<SleepingSession> sessions) {
    int sessionsSize = sessions.size();
    Duration duration =
        sessions.stream()
                .map(SleepingSession::getDuration)
                .reduce(Duration.ZERO, Duration::plus);

    Long average = duration.dividedBy(sessionsSize).toMinutes();

    return new SleepAnalysResult("Средняя длительность сна", average);
  }
}
