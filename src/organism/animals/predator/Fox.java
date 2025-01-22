package organism.animals.predator;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;

public class Fox extends Predator{
    public Fox() {
        super("Fox", 8, 2, 30, 2);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Animal createOffspring() {
        return new Fox();
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
