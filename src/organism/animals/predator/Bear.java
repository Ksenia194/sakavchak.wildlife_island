package organism.animals.predator;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Bear extends Predator{
    public Bear(int x, int y, GameField gameField) {
        super("Bear", 500, 80, 5, 2, x, y, gameField);
    }

    @Override
    public Animal createOffspring() {
        return new Bear(this.x, this.y, this.gameField);
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
