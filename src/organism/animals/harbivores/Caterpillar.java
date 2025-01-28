package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Caterpillar extends Herbivor {
    public Caterpillar(int x, int y, GameField gameField) {
        super("Caterpillar", 0.01, 0, 1000, 0, x, y, gameField);
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
        return new Caterpillar(this.x, this.y, this.gameField);
    }
}
