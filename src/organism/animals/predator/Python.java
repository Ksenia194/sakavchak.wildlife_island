package organism.animals.predator;


import map.Cell;
import organism.animals.Animal;

public class Python extends Predator {
    public Python() {
        super("Python", 15, 3, 30, 1);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Animal createOffspring() {
        return new Python();
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
