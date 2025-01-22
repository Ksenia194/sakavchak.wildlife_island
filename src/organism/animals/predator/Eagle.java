package organism.animals.predator;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;

public class Eagle extends Predator{
    public Eagle() {
        super("Eagle", 6, 1, 20, 3);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Animal createOffspring() {
        return new Eagle();
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
