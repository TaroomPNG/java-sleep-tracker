package ru.yandex.practicum.sleeptracker.functions.core;

import java.lang.Object;

public class SleepAnalysResult {
    private final String description;
    private final Object value;


    public SleepAnalysResult(String description, Object value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public Object getValue() {
        return value;
    }
}
