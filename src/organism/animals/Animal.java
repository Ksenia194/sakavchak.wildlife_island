package organism.animals;

import interfaces.*;
import map.Cell;
import map.GameField;
import organism.Organism;



public abstract class Animal extends Organism implements Move, Reproduce, Eat, Die {
    GameField gameField;
    protected double foodNeeded;
    protected int maxAnimalOnCell;
    protected int movementSpeed;
    protected double health;
    protected int age;
    protected int x;
    protected int y;
    private Cell currentCell;

    public Animal(String name, double weight, double foodNeeded, int maxAnimalOnCell, int movementSpeed) {
        super(name, weight);
        this.foodNeeded = foodNeeded;
        this.maxAnimalOnCell = maxAnimalOnCell;
        this.movementSpeed = movementSpeed;
        this.health = 100.0;
        this.age = 0;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public int getMaxAnimalOnCell() {
        return maxAnimalOnCell;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }


    public void move() {
        Direction direction = chooseDirection();
        int newX = this.x + direction.getX() * movementSpeed;
        int newY = this.y + direction.getY() * movementSpeed;
        if (gameField.isValidMove(newX, newY)) {
            gameField.getCell(x, y).removeAnimal(this);
            this.x = newX;
            this.y = newY;
            gameField.getCell(x, y).addAnimal(this);
            System.out.println(name + " переміщено в (" + x + ", " + y + ")");
        } else {
            System.out.println(name + " не можна переміщатися за межі поля!");
        }
    }

    public void eat(Cell currentCell) {
        System.out.println(name + " їсть в клітинці (" + x + ", " + y + ")");
    }

    public void reproduce(Cell currentCell) {
        System.out.println(name + " розмножується в клітинці (" + x + ", " + y + ")");
        if (canReproduce() && hasMate()) {
            Animal offspring = createOffspring();
            if (currentCell.canAddAnimal(offspring)) {
                currentCell.addAnimal(offspring);
                System.out.println(name + " розмножено. Нороджується новий " + offspring.getName() + " в клітинці (" + x + ", " + y + ")");
            } else {
                System.out.println(name + " не може розмножуватися, тому що клітина заповнена");
            }
        } else {
            System.out.println(name + " не може розмножитися, оскільки немає пари.");
        }
    }

    public Direction chooseDirection() {
        Direction[] directions = Direction.values();
        return directions[(int) (Math.random() * directions.length)];
    }

    protected boolean hasMate() {
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

    public boolean isDead() {
        return this.health <= 0 || this.age > 10;
    }

    public boolean canReproduce() {
        return this.age > 2 && this.health > 50;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    protected abstract Animal createOffspring();
}
