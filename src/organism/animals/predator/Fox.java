package organism.animals.predator;

import map.Cell;
import map.GameField;
import organism.animals.Animal;

public class Fox extends Predator{
    public Fox(int x, int y, GameField gameField) {
        super("Fox", 8, 2, 30, 2, x, y, gameField);
    }

    @Override
    public Animal createOffspring() {
        return new Fox(this.x, this.y, this.gameField);
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
