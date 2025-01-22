package organism.animals.predator;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;
import organism.animals.harbivores.Herbivor;
import service.Statistics;

import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed) {
        super(name, weight, foodNeeded, maxAnimalOnCell, movementSpeed);
    }

    @Override
    public void move() {
        Direction direction = chooseDirection();
        System.out.println(name + " moves " + direction);
    }

    @Override
    public void eat(Cell currentCell) {
        synchronized (currentCell) {
            List<Animal> animals = currentCell.getAnimals();
            Random random = new Random();
            for (Animal animal : animals) {
                if (animal instanceof Herbivor && this.weight < foodNeeded) {
                    int probability = Statistics.getEatingProbability(this.getClass().getSimpleName(), animal.getClass().getSimpleName());
                    if (random.nextInt(100) < probability) {
                        animals.remove(animal);
                        this.weight += animal.getWeight();
                        System.out.println(this.name + " eaten " + animal.getName());

                        if (this.weight >= foodNeeded) {
                            System.out.println(this.name + " ate his fill.");
                            break;
                        }
                    }
                }
            }
        }
    }


    @Override
    public void age() {
        super.age();
        if (!isAlive())
            die();
    }
}


