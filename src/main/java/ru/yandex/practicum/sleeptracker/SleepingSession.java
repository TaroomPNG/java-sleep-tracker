package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.service.QualityOfSleep;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
  private final LocalDateTime asleep;
  private final LocalDateTime wokeUp;
  private final QualityOfSleep qualityOfSleep;
  private final Duration duration;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

  public SleepingSession(String asleep, String wokeUp, QualityOfSleep qualityOfSleep) {
    this.asleep = LocalDateTime.parse(asleep, FORMATTER);
    this.wokeUp = LocalDateTime.parse(wokeUp, FORMATTER);
    this.qualityOfSleep = qualityOfSleep;

    this.duration = Duration.between(this.asleep, this.wokeUp);
  }

  public LocalDateTime getAsleep() {
    return asleep;
  }

  public LocalDateTime getWokeUp() {
    return wokeUp;
  }

  public QualityOfSleep getQualityOfSleep() {
    return qualityOfSleep;
  }

  public Duration getDuration() {
    return duration;
  }
}
