package organism.animals.predator;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Wolf extends Predator {

    public Wolf(int x, int y, GameField gameField) {
        super("Wolf", 50, 8, 30, 3, x, y, gameField);
    }

    @Override
    public Animal createOffspring() {
        return new Wolf(this.x, this.y, this.gameField);
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

