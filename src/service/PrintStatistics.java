package service;

import interfaces.AnimalType;
import map.Cell;
import map.GameField;
import organism.animals.Animal;

import java.util.*;
import java.util.stream.Collectors;

public class PrintStatistics {
    private final Cell[][] cells;
    private Animal[] animals;
    private GameField gameField;

    public PrintStatistics(GameField gameField) {
        this.cells = gameField.getField();
        this.gameField = gameField;
        this.animals = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .flatMap(cell -> cell.getAnimals().stream())
                .toArray(Animal[]::new);
    }

    public void printInitialState() {
        System.out.println("Початковий стан острова");
        System.out.println("крок: 0");
        System.out.println("розмір острова: " + cells.length + "*" + cells[0].length);
        System.out.println("кількість локацій: " + (cells.length * cells[0].length));
        System.out.println("загальна кількість тварин: " + totalAnimalsCount());
        System.out.println("загальна кількість рослин: " + totalGrassCount());
    }

    public void printLocationStatistics() {
        System.out.println("\nСтатика за локаціями");
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                if (!cell.getAnimals().isEmpty() || cell.getPlant().getCurrentSize() > 0) {
                    String animalStats = cell.getAnimals().stream()
                            .collect(Collectors.groupingBy(
                                    animal -> animal.getClass().getSimpleName(),
                                    Collectors.counting()
                            ))
                            .entrySet().stream()
                            .map(e -> e.getKey() + ": " + e.getValue())
                            .collect(Collectors.joining(", "));

                    System.out.println("Локація (" + i + ", " + j + "): " + animalStats + ", Рослини: " + cell.getPlant().getCurrentSize());
                }
            }
        }
    }

    public void printFeeding(List<String> feedingLog) {
        System.out.println("\nХарчування");
        for (String log : feedingLog) {
            System.out.println(log);
        }
    }

    public void printMovement(List<String> movementLog) {
        System.out.println("\nПереміщення");
        for (String log : movementLog) {
            System.out.println(log);
        }
    }

    public void printReproduction(List<String> reproductionLog) {
        System.out.println("\nРозмноження");
        for (String log : reproductionLog) {
            System.out.println(log);
        }
    }

    public void printPlantGrowth(List<String> plantGrowthLog) {
        System.out.println("\nРослини");
        for (String log : plantGrowthLog) {
            System.out.println(log);
        }
    }

    public void printSummaryStatistics(int step) {
        System.out.println("\nЗведена статика за " + step + " крок");
        System.out.println("Кількісль тварин");
        Map<AnimalType, Integer> animalCounts = getAnimalCounts();
        for (AnimalType animalType : AnimalType.values()) {
            System.out.println("- " + animalType + ": " + animalCounts.getOrDefault(animalType, 0));
        }
        System.out.println("Кількість рослин: " + totalGrassCount());
    }

    private Map<AnimalType, Integer> getAnimalCounts() {
        Map<AnimalType, Integer> counts = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .flatMap(cell -> cell.getAnimals().stream())
                .collect(Collectors.groupingBy(
                        animal -> AnimalType.valueOf(animal.getClass().getSimpleName().toUpperCase()),
                        Collectors.summingInt(e -> 1)
                ));

        // Логування результатів
        System.out.println("Поточний підрахунок тварин: " + counts);
        return counts;
    }

//    private int getAnimalCount(AnimalType animalType) {
//        return (int) Arrays.stream(cells)
//                .flatMap(Arrays::stream)
//                .flatMap(cell -> cell.getAnimals().stream())
//                .filter(animal -> AnimalType.valueOf(animal.getClass().getSimpleName().toUpperCase()) == animalType)
//                .count();
//    }

    private int totalGrassCount() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> (int) cell.getPlant().getCurrentSize())
                .sum();
    }

    private int totalAnimalsCount() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> cell.getAnimals().size())
                .sum();
    }

}
