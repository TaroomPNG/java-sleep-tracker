package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;

import java.util.List;

public class SleepCounterFunction extends SleepFunction {

    @Override
    public SleepAnalysResult apply(List<SleepingSession> sessions) {
         return new SleepAnalysResult("Всего сессий сна", sessions.size());
    }
}
