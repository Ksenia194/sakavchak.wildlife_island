package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Buffalo extends Herbivor {

    public Buffalo(int x, int y, GameField gameField) {
        super("Buffalo", 700, 100, 10, 3, x, y, gameField);
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

    @Override
    public Animal createOffspring() {
        return new Buffalo(this.x, this.y, this.gameField);
    }
}
