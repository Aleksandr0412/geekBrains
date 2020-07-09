package ru.geekbrains.race;

import ru.geekbrains.race.stages.Road;
import ru.geekbrains.race.stages.Tunnel;

public class RaceApp {
    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        race.begin();
    }
}
