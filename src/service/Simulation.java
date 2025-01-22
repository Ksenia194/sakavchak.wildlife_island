package service;

import interfaces.Direction;
import map.Cell;
import map.GameField;
import organism.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private GameField gameField;
    private PrintStatistics printStatistics;
    private final ScheduledExecutorService scheduledExecutor;
    private final ExecutorService lifecycleExecutor;
    private final int simulationSpeed;
    private boolean isRunning;
    private volatile boolean isPaused;
    private final Object pauseLock = new Object();
    private boolean verboseOutput = false;
    private final Cell[][] cells;

    public Simulation(GameField gameField, int simulationSpeed) {
        this.gameField = gameField;
        this.printStatistics = new PrintStatistics(gameField.getField());
        this.scheduledExecutor = Executors.newScheduledThreadPool(3);
        this.lifecycleExecutor = Executors.newCachedThreadPool();
        this.simulationSpeed = simulationSpeed;
        this.isRunning = false;
        this.isPaused = false;
        this.cells = new Cell[gameField.getWidth()][gameField.getHeight()];
    }

    public void start() {
        setVerboseOutput(false);
        System.out.println("Симуляція запущена.");
        isRunning = true;
        lifecycleExecutor.submit(() -> {
            while (isRunning) {
                synchronized (this) {
                    while (isPaused) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }

                System.out.println("Запуск нового циклу симуляції...");

                printStatistics.printStatistic();
                gameField.growPlants();
                runAnimalLifecycle();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void stop() {
        isRunning = false;
        scheduledExecutor.shutdown();
        lifecycleExecutor.shutdown();
        try {
            if (!scheduledExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduledExecutor.shutdownNow();
            }
            if (!lifecycleExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                lifecycleExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutor.shutdownNow();
            lifecycleExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Симуляція зупинена.");
    }

    public synchronized void pause() {
        isPaused = true;
        System.out.println("Симуляцію призупинено.");
        notify();
    }

    public synchronized void resume() {
        if (!isRunning) {
            System.out.println("Симуляцію не запущено.");
            return;
        }
        isPaused = false;
        System.out.println("Симуляцію відновлено.");
        notify();
    }

    public void runAnimalLifecycle() {
        moveAnimals();
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                List<Animal> animals = gameField.getCell(i, j).getAnimals();

                for (Animal animal : animals) {
                    animal.act();
                    if (animal.canReproduce()) {
                        animal.reproduce(gameField.getCell(i, j));
                    }
                    if (animal.isDead()) {
                        System.out.println("Тварина " + animal.getClass().getSimpleName() + " вмирає.");
                        gameField.getCell(i, j).removeAnimal(animal);
                    }
                }
            }
        }
    }

    public void moveAnimals() {
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                List<Animal> animals = gameField.getCell(i, j).getAnimals();
                for (Animal animal : animals) {
                    Direction direction = animal.chooseDirection();
                    int newX = i, newY = j;
                    switch (direction) {
                        case NORTH -> newY = Math.max(0, j - 1);
                        case SOUTH -> newY = Math.min(gameField.getHeight() - 1, j + 1);
                        case EAST -> newX = Math.min(gameField.getWidth() - 1, i + 1);
                        case WEST -> newX = Math.max(0, i - 1);
                    }
                    if (newX != i || newY != j) {
                        gameField.getCell(newX, newY).addAnimal(animal);
                        gameField.getCell(i, j).removeAnimal(animal);
                        animal.move();
                    }
                }
            }
        }
    }

    public void setVerboseOutput(boolean verboseOutput) {
        this.verboseOutput = verboseOutput;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell != null) {
                    cell.setVerboseOutput(verboseOutput);
                }
            }
        }
    }
}
