package organism.animals.harbivores;

import map.Cell;
import organism.animals.Animal;

public class Goat extends Herbivor {
    public Goat() {
        super("Goat", 60, 10, 140, 3);
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
        return new Goat();
    }
}
