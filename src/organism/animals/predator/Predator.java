package organism.animals.predator;

import interfaces.Direction;
import map.Cell;
import map.GameField;
import organism.animals.Animal;
import organism.animals.harbivores.Herbivor;
import service.Statistics;

import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed, int x, int y, GameField gameField) {
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
        return null;
    }

//    @Override
//    public String eat(Cell currentCell) {
//        synchronized (currentCell) {
//            List<Animal> animals = currentCell.getAnimals();
//            Random random = new Random();
//            for (Animal animal : animals) {
//                if (animal instanceof Herbivor && this.weight < foodNeeded) {
//                    AnimalType predatorType = AnimalType.valueOf(this.getClass().getSimpleName().toUpperCase());
//                    FoodType preyType = FoodType.valueOf(animal.getClass().getSimpleName().toUpperCase());
//                    int probability = Statistics.getEatingProbability(predatorType, preyType);
//                    if (random.nextInt(100) < probability) {
//                        animals.remove(animal);
//                        this.weight += animal.getWeight();
//                        System.out.println(this.name + " з'їдено " + animal.getName());
//
//                        if (this.weight >= foodNeeded) {
//                            System.out.println(this.name + " наївся.");
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }


    @Override
    public void age() {
        super.age();
        if (!isAlive())
            die();
    }
}


