package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Deer extends Herbivor {
    public Deer(int x, int y, GameField gameField) {
        super("Deer", 300, 50, 20, 4, x, y, gameField);
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
        return new Deer(this.x, this.y, this.gameField);
    }
}
