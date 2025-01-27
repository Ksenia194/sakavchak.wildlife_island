package organism.animals.harbivores;

import interfaces.Direction;
import map.Cell;
import map.GameField;
import organism.animals.Animal;
import organism.plant.Plant;
import service.Statistics;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivor extends Animal {

    public Herbivor(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed, int x, int y, GameField gameField) {
        super(name, weight, foodNeeded, maxAnimalOnCell, movementSpeed, x, y, gameField);
    }

    @Override
    public String move() {
        Direction direction = chooseDirection();
        return name + " moves " + direction;
    }

    @Override
    public String eat(Cell currentCell) {
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
        return null;
    }

//    @Override
//    public String eat(Cell currentCell) {
//        synchronized (currentCell) {
//            Plant plant = currentCell.getPlant();
//            if (plant != null) {
//                AnimalType herbivorType = AnimalType.valueOf(this.getClass().getSimpleName().toUpperCase());
//                FoodType plantType = FoodType.valueOf(plant.getClass().getSimpleName().toUpperCase());
//                    int probability = Statistics.getEatingProbability(herbivorType, plantType);
//                    int randomValue = ThreadLocalRandom.current().nextInt(100);
//                    if (randomValue < probability) {
//                        currentCell.animalEatPlant(this);
//                        this.foodNeeded -= foodNeeded;
//                        this.weight += plant.getCurrentSize();
//                        System.out.println(this.name + " з'їв " + plant.getType());
//                    } else {
//                        System.out.println(this.name + " не знайшов їстівних рослин.");
//                    }
//            } else{
//                System.out.println(this.name + " не знайшов рослин на клітинці.");
//            }
//        }
//        return null;
//    }


    @Override
    public void age() {
        super.age();
        if (!isAlive()) {
            die();
        }
    }

    protected abstract Animal createOffspring();
}
