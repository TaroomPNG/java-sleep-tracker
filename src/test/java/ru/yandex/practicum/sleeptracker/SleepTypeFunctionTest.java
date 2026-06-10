package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.practicum.sleeptracker.functions.SleepTypeFunction;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;
import ru.yandex.practicum.sleeptracker.functions.core.SleepType;
import ru.yandex.practicum.sleeptracker.functions.core.SleepTypes;
import ru.yandex.practicum.sleeptracker.service.QualityOfSleep;

import java.util.List;

public class SleepTypeFunctionTest {

  @Test
  void testTypeFunctionForOwl() {
    List<SleepingSession> sessions =
        List.of(
            new SleepingSession("01.01.25 23:30", "02.01.25 10:30", QualityOfSleep.GOOD),
            new SleepingSession("02.01.25 23:30", "03.01.25 10:30", QualityOfSleep.GOOD),
            new SleepingSession("04.01.25 01:30", "04.01.25 12:30", QualityOfSleep.GOOD),
            new SleepingSession("04.01.25 20:00", "05.01.25 07:00", QualityOfSleep.GOOD),
            new SleepingSession("05.01.25 20:00", "06.01.25 07:00", QualityOfSleep.GOOD));

    SleepFunction function = new SleepTypeFunction();
    SleepAnalysResult result = function.apply(sessions);
    SleepType expectedType = new SleepType(SleepTypes.OWL);

    assertEquals(expectedType, result.getValue());
  }

  @Test
  void testTypeFunctionForLark() {
    List<SleepingSession> sessions =
        List.of(
            new SleepingSession("01.01.25 19:30", "02.01.25 06:30", QualityOfSleep.GOOD),
            new SleepingSession("02.01.25 20:30", "03.01.25 06:50", QualityOfSleep.GOOD),
            new SleepingSession("04.01.25 17:30", "05.01.25 05:30", QualityOfSleep.GOOD),
            new SleepingSession("05.01.25 23:30", "06.01.25 10:00", QualityOfSleep.GOOD),
            new SleepingSession("06.01.25 23:30", "07.01.25 10:00", QualityOfSleep.GOOD));

    SleepFunction function = new SleepTypeFunction();
    SleepAnalysResult result = function.apply(sessions);

    SleepType expectedType = new SleepType(SleepTypes.LARK);

    assertEquals(expectedType, result.getValue());
  }
}
