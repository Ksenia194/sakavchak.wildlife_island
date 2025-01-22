package organism.animals.harbivores;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;
import organism.plant.Plant;

public class Rabbit extends Herbivor {

    public Rabbit() {
        super("Rabbit", 2, 0.45, 150, 2);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void eat(Cell currentCell) {
        super.eat(currentCell);
    }

    @Override
    public void reproduce(Cell currentCell) {
        super.reproduce(currentCell);
    }

    @Override
    public Animal createOffspring() {
        return new Rabbit();
    }
}
