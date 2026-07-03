package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.functions.core.SleepAnalysResult;
import ru.yandex.practicum.sleeptracker.functions.core.SleepFunction;
import ru.yandex.practicum.sleeptracker.functions.core.SleepTypes;
import static ru.yandex.practicum.sleeptracker.customExceptions.FunctionLogger.logging;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SleepTypeFunction extends SleepFunction {

  @Override
  public SleepAnalysResult apply(List<SleepingSession> sessions) {

    logging("Анализ SleepTypeFunction");
    List<SleepingSession> filtredList =
        sessions.stream()
            .filter(
                (session -> {
                  return session.getAsleep().getDayOfMonth() != session.getWokeUp().getDayOfMonth()
                      || session.getAsleep().getHour() < 9
                      || session.getWokeUp().getHour() > 20;
                }))
            .toList();

    logging(
        "Фильтрация прошла было элементов " + sessions.size() + " стало -> " + filtredList.size());
    List<SleepTypes> sleepTypes =
        filtredList.stream()
            .map(
                session -> {
                  int asleepHour = session.getAsleep().getHour();
                  int wokeUpHour = session.getWokeUp().getHour();

                  boolean isOwl = (asleepHour >= 23 || asleepHour < 5) && wokeUpHour > 9;
                  boolean isLark = (asleepHour < 22 & wokeUpHour < 7);

                  logging("Взят элемент # " + session.getAsleep() + " -> " + session.getWokeUp());
                  if (isLark) {
                    logging("Указан как жаворонок");
                    return SleepTypes.LARK;
                  }

                  if (isOwl) {
                    logging("Указан как сова");
                    return SleepTypes.OWL;
                  }

                  logging("Указан как голубь");
                  return SleepTypes.PIGEON;
                })
            .toList();

    logging("Маппинг прошел - считаем");

    Map<SleepTypes, Long> counter =
        sleepTypes.stream()
            .collect(Collectors.groupingBy(sleepType -> sleepType, Collectors.counting()));

    counter.forEach((s, l) -> logging(s + " -> " + l));

    SleepTypes type =
        counter.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(SleepTypes.PIGEON);
    logging("Выбран " + type);

    return new SleepAnalysResult("Учитывая ваш циркадный ритм вы", type);
  }
}
