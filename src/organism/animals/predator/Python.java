package organism.animals.predator;


import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Python extends Predator {
    public Python(int x, int y, GameField gameField) {
        super("Python", 15, 3, 30, 1, x, y, gameField);
    }

    @Override
    public Animal createOffspring() {
        return new Python(this.x, this.y, this.gameField);
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
