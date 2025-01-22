package organism.animals.harbivores;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;

public class Mouse extends Herbivor{
    public Mouse() {
        super("Mouse", 0.05, 0.01, 500, 1);
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
        return new Mouse();
    }

}
