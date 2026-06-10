package ru.yandex.practicum.sleeptracker.functions.core;

public class SleepType {

    SleepTypes type;

    public SleepType(SleepTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return switch (this.type) {
            case OWL -> "Сова";
            case PIGEON -> "Голубь";
            case LARK -> "Жаворонок";
        };
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        SleepType objectType = (SleepType) object;
        return this.type == objectType.type;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
