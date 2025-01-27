package map;


import organism.animals.Animal;
import organism.plant.Plant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


public class Cell {
    private final Lock lock = new ReentrantLock();
    private Plant plant;
    private final List<Animal> animals = new ArrayList<>();
    private final int x;
    private final int y;
    private int plantCount;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addAnimal(Animal animal) {
        if (animals.size() < animal.getMaxAnimalOnCell()) {
            animals.add(animal);
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
                if (plant.getCurrentSize() == 0) {
                    plantCount--;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean canAddAnimal(Animal animal) {
        return animals.size() < animal.getMaxAnimalOnCell();
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

    public String growPlant() {
        if (plant != null) {
            plant.grow();
            plantCount++;
            return "У локації(" + x + "," + y + ") виросло " + plant.getGrownRace() + " нових рослин. Усього " + plant.getCurrentSize();
        }
        return "";
    }
}
