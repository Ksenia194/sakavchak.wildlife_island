package service;

import map.Cell;
import organism.animals.Animal;
import organism.animals.harbivores.Herbivor;
import organism.animals.predator.Predator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintStatistics {
    private final Cell[][] cells;

    public PrintStatistics(Cell[][] cells) {
        this.cells = cells;
    }

    public void printStatistic() {
        System.out.println("===== Статистика симуляції =====");

        System.out.println("Рослини:");
        System.out.println("- Grass: " + totalGrassCount());

        System.out.println("\nТварини:");
        printAnimalStatistics();


        System.out.println("\nЛокації (загальні дані):");
        printLocationStatistics();

        System.out.println("\nЗагальна кількість:");
        System.out.println("- Тварини: " + totalAnimalsCount());
        System.out.println("- Рослини: " + totalGrassCount());

        System.out.println("=================================");
    }

    private void printLocationStatistics() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                System.out.print("Локація (" + i + ", " + j + "): ");
                printAnimalsAndPlants(cell);
            }
        }
    }

    private void printAnimalsAndPlants(Cell cell) {
        List<Animal> animals = cell.getAnimals();
        Map<String, Integer> animalCounts = new HashMap<>();

        for (Animal animal : animals) {
            animalCounts.put(animal.getClass().getSimpleName(), animalCounts.getOrDefault(animal.getClass().getSimpleName(), 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + ", ");
        }

        System.out.println("Рослини: " + cell.getPlant().getCurrentSize());
    }

    private void printAnimalStatistics() {
        Map<String, Integer> predatorStats = new HashMap<>();
        Map<String, Integer> herbivoreStats = new HashMap<>();


        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                for (Animal animal : cell.getAnimals()) {
                    if (animal instanceof Predator) {
                        predatorStats.put(animal.getClass().getSimpleName(), predatorStats.getOrDefault(animal.getClass().getSimpleName(), 0) + 1);
                    } else if (animal instanceof Herbivor) {
                        herbivoreStats.put(animal.getClass().getSimpleName(), herbivoreStats.getOrDefault(animal.getClass().getSimpleName(), 0) + 1);
                    }
                }
            }
        }

        System.out.println("Хижаки:");
        for (Map.Entry<String, Integer> entry : predatorStats.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Травоїдні:");
        for (Map.Entry<String, Integer> entry : herbivoreStats.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private int totalGrassCount() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> (int) cell.getPlant().getCurrentSize())
                .sum();
    }

    private int totalAnimalsCount() {
        int totalCount = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                totalCount += cells[i][j].getAnimals().size();
            }
        }
        return totalCount;
    }

}
