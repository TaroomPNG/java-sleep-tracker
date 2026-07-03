package ru.yandex.practicum.sleeptracker.functions.core;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public abstract class SleepFunction implements Function<List<SleepingSession>, SleepAnalysResult> {
}
