package organism.animals.harbivores;

import interfaces.Direction;
import map.Cell;
import organism.Organism;
import organism.animals.Animal;
import organism.plant.Plant;
import service.Statistics;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivor extends Animal {

    public Herbivor(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed) {
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
            Plant plant = currentCell.getPlant();
            if (plant != null) {
                    int probability = Statistics.getPlantEatingProbability(this.getClass().getSimpleName());
                    int randomValue = ThreadLocalRandom.current().nextInt(100);
                    if (randomValue < probability) {
                        currentCell.animalEatPlant(this);
                        this.foodNeeded -= foodNeeded;
                        this.weight += plant.getCurrentSize();
                        System.out.println(this.name + " з'їв " + plant.getType());
                    } else {
                        System.out.println(this.name + " не знайшов їстівних рослин.");
                    }
            } else{
                System.out.println(this.name + " не знайшов рослин на клітинці.");
            }
        }
    }


    @Override
    public void age() {
        super.age();
        if (!isAlive()) {
            die();
        }
    }

    protected abstract Animal createOffspring();
}
