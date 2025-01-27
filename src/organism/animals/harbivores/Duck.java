package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Duck extends Herbivor {
    public Duck(int x, int y, GameField gameField) {
        super("Duck", 1, 0.15, 200, 4, x, y, gameField);
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
        return new Duck(this.x, this.y, this.gameField);
    }
}
