package ru.yandex.practicum.sleeptracker.service;

public enum QualityOfSleep {
    BAD,
    NORMAL,
    GOOD,
    UNDETECTED;

    public static QualityOfSleep getQuality(String input) {
        return switch (input) {
            case "BAD" -> BAD;
            case "NORMAL" -> NORMAL;
            case "GOOD" -> GOOD;
            default -> UNDETECTED;
        };
    }
}
