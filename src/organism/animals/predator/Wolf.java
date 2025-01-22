package organism.animals.predator;

import map.Cell;
import organism.animals.Animal;

public class Wolf extends Predator {

    public Wolf() {
        super("Wolf", 50, 8, 30, 3);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Animal createOffspring() {
        return new Wolf();
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
}

