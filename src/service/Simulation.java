package service;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Simulation {
    private GameField gameField;
    public PrintStatistics printStatistics;
    private final ScheduledExecutorService scheduledExecutor;
    private final ExecutorService lifecycleExecutor;
    private final int simulationSpeed;
    private boolean isRunning;
    private volatile boolean isPaused;
    private final Cell[][] cells;

    public List<String> feedingLog = new ArrayList<>();
    public List<String> movementLog = new ArrayList<>();
    public List<String> reproductionLog = new ArrayList<>();
    public List<String> plantGrowthLog = new ArrayList<>();

    public Simulation(GameField gameField, int simulationSpeed) {
        this.gameField = gameField;
        this.printStatistics = new PrintStatistics(gameField);
        this.scheduledExecutor = Executors.newScheduledThreadPool(3);
        this.lifecycleExecutor = Executors.newCachedThreadPool();
        this.simulationSpeed = simulationSpeed;
        this.isRunning = false;
        this.isPaused = false;
        this.cells = gameField.getField();
    }

    public void start() {
        System.out.println("Симуляція запущена.");
        isRunning = true;
        lifecycleExecutor.submit(() -> {
            int step = 0;
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

                System.out.println("Запуск нового циклу симуляції... Крок: " + step);
                printStatistics.printInitialState();
                printStatistics.printLocationStatistics();

                runAnimalLifecycle(feedingLog, movementLog, reproductionLog);

                runPlantLifecycle(plantGrowthLog);

                printStatistics.printFeeding(feedingLog);
                printStatistics.printMovement(movementLog);
                printStatistics.printReproduction(reproductionLog);
                printStatistics.printPlantGrowth(plantGrowthLog);
                printStatistics.printSummaryStatistics(step);

                step++;

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

    public void runAnimalLifecycle(List<String> feedingLog, List<String> movementLog, List<String> reproductionLog) {
        moveAnimals(movementLog);
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                List<Animal> animals = gameField.getCell(i, j).getAnimals();
                for (Animal animal : animals) {
                    Cell currentCell = gameField.getCell(i, j);
                    String eatingResult = animal.eat(currentCell);
                    if (eatingResult != null && !eatingResult.isEmpty()) {
                        feedingLog.add(eatingResult);
                    }
                    String reproductionResult = animal.reproduce(currentCell);
                    if (reproductionResult != null && !reproductionResult.isEmpty()) {
                        reproductionLog.add(reproductionResult);

                    }
                    animal.age();
                    if (animal.isDead()) {
                        animal.die();
                    }
                }
            }
        }
    }

    public void moveAnimals(List<String> movementLog) {
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                List<Animal> animals = gameField.getCell(i, j).getAnimals();
                for (Animal animal : animals) {
                    String movementResult = animal.move();
                    if (movementResult != null && !movementResult.isEmpty()) {
                        movementLog.add(movementResult);
                    }
                }
            }
        }
    }

    public void runPlantLifecycle(List<String> plantGrowthLog) {
        for (int i = 0; i < gameField.getWidth(); i++) {
            for (int j = 0; j < gameField.getHeight(); j++) {
                Cell cell = gameField.getCell(i, j);
                String plantGrowthResult = cell.growPlant();
                if (plantGrowthResult != null && !plantGrowthResult.isEmpty()) {
                    plantGrowthLog.add(plantGrowthResult);
                }
            }
        }
    }
}
