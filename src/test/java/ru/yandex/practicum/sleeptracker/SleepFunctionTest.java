package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.*;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;
import ru.yandex.practicum.sleeptracker.service.QualityOfSleep;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

public class SleepFunctionTest {

  List<SleepingSession> sessions =
      List.of(
          new SleepingSession("01.01.25 22:00", "02.01.25 06:30", QualityOfSleep.GOOD),
          new SleepingSession("02.01.25 23:00", "03.01.25 07:00", QualityOfSleep.BAD),
          new SleepingSession("03.01.25 23:30", "04.01.25 05:45", QualityOfSleep.NORMAL),
          new SleepingSession("04.01.25 23:00", "05.01.25 08:00", QualityOfSleep.NORMAL),
          new SleepingSession("05.01.25 19:00", "06.01.25 05:00", QualityOfSleep.NORMAL),
          new SleepingSession("07.01.25 01:00", "07.01.25 10:00", QualityOfSleep.NORMAL),
          new SleepingSession("08.01.25 02:00", "08.01.25 05:00", QualityOfSleep.NORMAL),
          new SleepingSession("08.01.25 16:00", "08.01.25 23:00", QualityOfSleep.NORMAL),
          new SleepingSession("09.01.25 23:00", "10.01.25 07:00", QualityOfSleep.NORMAL));

  @Test
  void testInsomniaFunction() {
      SleepFunction function = new SleepInsomniaFunction();
      SleepAnalysResult result = function.apply(sessions);

      Long expectedInsomniaCount = 1L;

      assertEquals(expectedInsomniaCount, result.getValue());
  }

  @Test
  void testAvgDurationFunction() {
    SleepFunction function = new SleepAvgDurationFunction();
    SleepAnalysResult result = function.apply(sessions);

    Duration expectedAvg =
        sessions.stream()
            .map(SleepingSession::getDuration)
            .reduce(Duration.ZERO, Duration::plus)
            .dividedBy(sessions.size());
    assertEquals(expectedAvg.toMinutes(), result.getValue());
  }

  @Test
  void testBadCounterFunction() {
    SleepFunction function = new SleepBadCounterFunction();
    SleepAnalysResult result = function.apply(sessions);

    Long expectedBadCounter =
        sessions.stream().filter(s -> s.getQualityOfSleep().equals(QualityOfSleep.BAD)).count();

    assertEquals(expectedBadCounter, result.getValue());
  }

  @Test
  void testCounterFunction() {
    SleepFunction function = new SleepCounterFunction();
    SleepAnalysResult result = function.apply(sessions);

    Integer expectedCounter = sessions.size();

    assertEquals(expectedCounter, result.getValue());
  }

  @Test
  void testMaxFunction() {
    SleepFunction function = new SleepMaxFunction();
    SleepAnalysResult result = function.apply(sessions);

    Duration expectedMax =
        sessions.stream()
            .map(SleepingSession::getDuration)
            .max(Duration::compareTo)
            .orElse(Duration.ZERO);

    assertEquals(expectedMax.toMinutes(), result.getValue());
  }

  @Test
  void testMinFunction() {
    SleepFunction function = new SleepMinFunction();
    SleepAnalysResult result = function.apply(sessions);

    Duration expectedMin =
        sessions.stream()
            .map(SleepingSession::getDuration)
            .min(Duration::compareTo)
            .orElse(Duration.ZERO);

    assertEquals(expectedMin.toMinutes(), result.getValue());
  }
}
