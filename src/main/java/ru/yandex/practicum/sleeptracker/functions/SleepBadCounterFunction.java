package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;
import ru.yandex.practicum.sleeptracker.service.QualityOfSleep;

import java.util.List;

public class SleepBadCounterFunction extends SleepFunction {
    @Override
    public SleepAnalysResult apply(List<SleepingSession> sessions) {

       long badSleepCount = sessions.stream()
               .filter(s -> s.getQualityOfSleep().equals(QualityOfSleep.BAD))
               .count();
    return new SleepAnalysResult("Количество плохого сна", badSleepCount);
    }
}
