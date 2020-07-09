package ru.geekbrains.race;

import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {
    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private static AtomicInteger counter = new AtomicInteger(0);
    private static final Object lock = new Object();

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            counter.incrementAndGet();
            if (counter.get() != CARS_COUNT) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                lock.notifyAll();
            }
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).overcome(this);
        }

        if (counter.decrementAndGet() == CARS_COUNT - 1) {
            System.out.println(this.name + " WIN!!!!");
        }

        if (counter.get() == 0)
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}