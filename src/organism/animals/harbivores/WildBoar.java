package organism.animals.harbivores;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class WildBoar extends Herbivor{
    public WildBoar(int x, int y, GameField gameField) {
        super("WildBoar", 400, 50, 50, 2, x, y, gameField);
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
        return new WildBoar(this.x, this.y, this.gameField);
    }
}
