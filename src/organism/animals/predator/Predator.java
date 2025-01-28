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
            System.out.println(this.name + " намагається знайти жертву серед " + animals.size() + " тварин.");

            Random random = new Random();
            boolean eaten = false;

            for (Animal animal : animals) {
                System.out.println("Знайдено тварину: " + animal.getName() + ", тип: " + animal.getClass().getSimpleName());
            }
            Animal preyToEat = null;
            for (Animal animal : animals) {
                if (animal instanceof Herbivor && !animal.equals(this)) {
                    preyToEat = animal;
                    break;
                }
            }

            if (preyToEat == null) {
                System.out.println(this.name + " не знайшов травоїдних тварин на клітинці.");
                return null;
            }

            System.out.println(this.name + " намагається з'їсти " + preyToEat.getName());

            int probability = Statistics.getEatingProbability(this.getClass().getSimpleName(), preyToEat.getClass().getSimpleName());
            int randomValue = random.nextInt(100);
            System.out.println(this.name + " намагається з'їсти з ймовірністю: " + probability + "% (випадкове значення: " + randomValue + ")");

            if (randomValue < probability) {
                currentCell.removeAnimal(preyToEat);
                this.weight += preyToEat.getWeight();
                System.out.println(this.name + " з'їв " + preyToEat.getName());
                eaten = true;
            } else {
                System.out.println(this.name + " не зміг з'їсти " + preyToEat.getName());
            }

            if (!eaten) {
                System.out.println(this.name + " не знайшов жертву або не зміг з'їсти жодну.");
            }
        }
        return null;
    }

    @Override
    public void age() {
        super.age();
        if (!isAlive())
            die();
    }
}


