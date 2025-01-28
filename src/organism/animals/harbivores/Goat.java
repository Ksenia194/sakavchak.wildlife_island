package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Goat extends Herbivor {
    public Goat(int x, int y, GameField gameField) {
        super("Goat", 60, 10, 140, 3, x, y, gameField);
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
        return new Goat(this.x, this.y, this.gameField);
    }
}
