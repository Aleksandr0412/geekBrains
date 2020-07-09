package ru.geekbrains.race.stages;

import ru.geekbrains.race.Car;

public abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void overcome(Car c);
}
