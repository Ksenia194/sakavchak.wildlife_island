package organism.animals.predator;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;

public class Bear extends Predator{
    public Bear() {
        super("Bear", 500, 80, 5, 2);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Animal createOffspring() {
        return new Bear();
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
