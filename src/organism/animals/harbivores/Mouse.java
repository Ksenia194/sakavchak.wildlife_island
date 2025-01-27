package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Mouse extends Herbivor{
    public Mouse(int x, int y, GameField gameField) {
        super("Mouse", 0.05, 0.01, 500, 1, x, y, gameField);
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
        return new Mouse(this.x, this.y, this.gameField);
    }

}
