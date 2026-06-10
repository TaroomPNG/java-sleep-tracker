package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;

import java.time.Duration;
import java.util.List;

public class SleepMaxFunction extends SleepFunction {

    @Override
    public SleepAnalysResult apply(List<SleepingSession> sessions) {
        Duration duration = sessions.stream()
                .map(SleepingSession::getDuration)
                .max(Duration::compareTo)
                .orElse(Duration.ZERO);
    return new SleepAnalysResult("Максимальная длительность сна", duration.toMinutes());
    }
}
