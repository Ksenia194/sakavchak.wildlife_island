package map;


import organism.animals.Animal;
import organism.plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static organism.plant.Plant.currentSize;

public class Cell {
    private boolean verboseOutput = false;
    private final Lock lock = new ReentrantLock();
    private Plant plant;
    private List<Animal> animals = new ArrayList<>();
    private int plantCount;
    private static final int maxPlantCount = 200;

    public Cell() {

    }

    public void addAnimal(Animal animal) {
        if (animals.size() < animal.getMaxAnimalOnCell()) {
            animals.add(animal);
            if (verboseOutput) {
                System.out.println(animal.getName() + " додано да клітинки.");
            }
        } else {
            System.out.println("Неможливо додати більше " + animal.getName() + " до комірки");
        }
    }

    public void removeAnimal(Animal animal) {

        animals.remove(animal);
        System.out.println(animal.getName() + " видалено з комірки.");
    }

    public void animalEatPlant(Animal animal) {
        if (plant != null) {
            lock.lock();
            try {
                double foodAmount = Math.min(animal.getFoodNeeded(), plant.getCurrentSize());
                plant.beEaten(foodAmount);
                plantCount = (plant.getCurrentSize() == 0) ? plantCount - 1 : plantCount;
            } finally {
                lock.unlock();
            }
        }
    }

    public void animalInteraction() {
        animals.forEach(Animal::act);
    }

    public boolean canAddAnimal(Animal animal) {
        return animals.size() < animal.getMaxAnimalOnCell();
    }

    public int getPlantCount() {
        return plantCount;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void grownPlant() {
        if (plant != null && plantCount < maxPlantCount) {
            lock.lock();
            try {
                plant.grown();
                if (verboseOutput) {
                    System.out.println("Grass виріс. Поточний розмір: " + currentSize);
                }
                plantCount++;
            } finally {
                lock.unlock();
            }
        }
    }
    public void setVerboseOutput(boolean verboseOutput) {
        this.verboseOutput = verboseOutput;
    }
}
