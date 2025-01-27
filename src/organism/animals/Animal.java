package organism.animals;

import interfaces.*;
import map.Cell;
import map.GameField;
import organism.Organism;
import organism.plant.Grass;


public abstract class Animal extends Organism implements Move, Reproduce, Eat, Die {
    protected GameField gameField;
    protected double foodNeeded;
    protected int maxAnimalOnCell;
    protected int movementSpeed;
    protected double health;
    protected int age;
    protected int x;
    protected int y;

    public Animal(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed, int x, int y, GameField gameField) {
        super(name, weight);
        this.foodNeeded = foodNeeded;
        this.maxAnimalOnCell = maxAnimalOnCell;
        this.movementSpeed = movementSpeed;
        this.health = 100.0;
        this.age = 0;
        this.x = x;
        this.y = y;
        this.gameField = gameField;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getMaxAnimalOnCell() {
        return maxAnimalOnCell;
    }


    public String move() {
        Direction direction = chooseDirection();
        int newX = this.x + direction.getX() * movementSpeed;
        int newY = this.y + direction.getY() * movementSpeed;
        if (gameField.isValidMove(newX, newY)) {
            gameField.getCell(x, y).removeAnimal(this);
            this.x = newX;
            this.y = newY;
            gameField.getCell(x, y).addAnimal(this);
            return name + " переміщено в (" + x + ", " + y + ")";
        } else {
            return name + " не можна переміщатися за межі поля!";
        }
    }

    public String eat(Cell currentCell) {
        return null;
//        Cell cell = gameField.getCell(x, y);
//        Grass grass = (Grass) cell.getPlant();
//        if (grass != null && grass.getTotalWeight() > 0) {
//            grass.consume();
//            return name + " їсть в клітинці (" + x + ", " + y + ")";
//        } else {
//            return name + " не знайшов рослин на клітинці.";
//        }
    }

    public String reproduce(Cell currentCell) {
        if (hasMate(currentCell)) {
            Animal offspring = createOffspring();
            currentCell.addAnimal(offspring);
            return name + " розмножено. Нороджується новий " + offspring.getName() + " в клітинці (" + x + ", " + y + ")";
        } else {
            return name + " не може розмножитися, оскільки немає пари.";
        }
    }

    public Direction chooseDirection() {
        Direction[] directions = Direction.values();
        return directions[(int) (Math.random() * directions.length)];
    }

    protected boolean hasMate(Cell currentCell) {
        long count = currentCell.getAnimals().stream()
                .filter(animal -> animal.getClass().equals(this.getClass()))
                .count();
        return count >= 2;
    }

    public void age() {
        this.age++;
        this.health -= 0.5;

        if (this.age > 10) {
            this.health -= 1.0;
        }

        if (this.health <= 0) {
            die();
        }
    }

    public void die() {
        System.out.println(this.name + " помер.");
        gameField.getCell(x, y).removeAnimal(this);
    }

    public void act() {
        move();
        eat(gameField.getCell(x, y));
        reproduce(gameField.getCell(x, y));
        age();

        if (isDead()) {
            die();
        }
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public boolean isDead() {
        return this.health <= 0 || this.age > 10;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    protected abstract Animal createOffspring();
}
