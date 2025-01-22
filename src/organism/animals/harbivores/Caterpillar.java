package organism.animals.harbivores;

import interfaces.Direction;
import map.Cell;
import organism.animals.Animal;
import organism.plant.Plant;

public class Caterpillar extends Herbivor{
    public Caterpillar() {
        super("Caterpillar", 0.01, 0, 1000, 0);
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
        return new Caterpillar();
    }
}
