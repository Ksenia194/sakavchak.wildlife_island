package organism.animals.predator;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Eagle extends Predator{
    public Eagle(int x, int y, GameField gameField) {
        super("Eagle", 6, 1, 20, 3, x, y, gameField);
    }

    @Override
    public Animal createOffspring() {
        return new Eagle(this.x, this.y, this.gameField);
    }

    @Override
    public String move() {
        return super.move();
    }

    @Override
    public String eat(Cell currentCell) {
        return super.eat(currentCell);
    }

    @Override
    public String reproduce(Cell currentCell) {
        return super.reproduce(currentCell);
    }
}
