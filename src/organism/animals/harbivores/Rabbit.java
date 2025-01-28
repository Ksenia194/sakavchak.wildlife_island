package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Rabbit extends Herbivor {

    public Rabbit(int x, int y, GameField gameField) {
        super("Rabbit", 2, 0.45, 150, 2, x, y, gameField);
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
        return new Rabbit(this.x, this.y, this.gameField);
    }
}
