package map;

import interfaces.AnimalType;
import organism.animals.harbivores.*;
import organism.animals.predator.*;
import organism.plant.Grass;
import java.util.Random;

public class GameField {

    private final int height;
    private final int width;
    private Cell[][] field;
    private static Random random = new Random();

    public GameField(int height, int width, int totalAnimal) {
        this.height = height;
        this.width = width;
        field = new Cell[width][height];
        initializeField();
    }

    public void initializeField() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = new Cell(i, j);

                if (random.nextDouble() < 0.5) {
                    field[i][j].setPlant(new Grass());
                }

                double randomValue = random.nextDouble();
                AnimalType animalType = getAnimalType(randomValue);
                if (animalType != null) {
                    addAnimalToCell(animalType, field[i][j]);
                }
            }
        }
    }

    private void addAnimalToCell(AnimalType animalType, Cell cell) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);

        switch (animalType) {
            case WOLF:
                if (cell.canAddAnimal(new Wolf(x, y, this))) {
                    cell.addAnimal(new Wolf(x, y, this));
                }
                break;
            case PYTHON:
                if (cell.canAddAnimal(new Python(x, y, this))) {
                    cell.addAnimal(new Python(x, y, this));
                }
                break;
            case FOX:
                if (cell.canAddAnimal(new Fox(x, y, this))) {
                    cell.addAnimal(new Fox(x, y, this));
                }
                break;
            case BEAR:
                if (cell.canAddAnimal(new Bear(x, y, this))) {
                    cell.addAnimal(new Bear(x, y, this));
                }
                break;
            case EAGLE:
                if (cell.canAddAnimal(new Eagle(x, y, this))) {
                    cell.addAnimal(new Eagle(x, y, this));
                }
                break;
            case HORSE:
                if (cell.canAddAnimal(new Horse(x, y, this))) {
                    cell.addAnimal(new Horse(x, y, this));
                }
                break;
            case DEER:
                if (cell.canAddAnimal(new Deer(x, y, this))) {
                    cell.addAnimal(new Deer(x, y, this));
                }
                break;
            case RABBIT:
                if (cell.canAddAnimal(new Rabbit(x, y, this))) {
                    cell.addAnimal(new Rabbit(x, y, this));
                }
                break;
            case MOUSE:
                if (cell.canAddAnimal(new Mouse(x, y, this))) {
                    cell.addAnimal(new Mouse(x, y, this));
                }
                break;
            case GOAT:
                if (cell.canAddAnimal(new Goat(x, y, this))) {
                    cell.addAnimal(new Goat(x, y, this));
                }
                break;
            case SHEEP:
                if (cell.canAddAnimal(new Sheep(x, y, this))) {
                    cell.addAnimal(new Sheep(x, y, this));
                }
                break;
            case WILD_BOAR:
                if (cell.canAddAnimal(new WildBoar(x, y, this))) {
                    cell.addAnimal(new WildBoar(x, y, this));
                }
                break;
            case BUFFALO:
                if (cell.canAddAnimal(new Buffalo(x, y, this))) {
                    cell.addAnimal(new Buffalo(x, y, this));
                }
                break;
            case DUCK:
                if (cell.canAddAnimal(new Duck(x, y, this))) {
                    cell.addAnimal(new Duck(x, y, this));
                }
                break;
            case CATERPILLAR:
                if (cell.canAddAnimal(new Caterpillar(x, y, this))) {
                    cell.addAnimal(new Caterpillar(x, y, this));
                }
                break;
            default:
                break;
        }
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    public Cell[][] getField() {
        return field;
    }

    private AnimalType getAnimalType(double randomValue) {
        if (randomValue < 0.05) {
            return AnimalType.WOLF;
        } else if (randomValue < 0.10) {
            return AnimalType.PYTHON;
        } else if (randomValue < 0.15) {
            return AnimalType.FOX;
        } else if (randomValue < 0.20) {
            return AnimalType.BEAR;
        } else if (randomValue < 0.25) {
            return AnimalType.EAGLE;
        } else if (randomValue < 0.30) {
            return AnimalType.HORSE;
        } else if (randomValue < 0.35) {
            return AnimalType.DEER;
        } else if (randomValue < 0.40) {
            return AnimalType.RABBIT;
        } else if (randomValue < 0.45) {
            return AnimalType.MOUSE;
        } else if (randomValue < 0.50) {
            return AnimalType.GOAT;
        } else if (randomValue < 0.55) {
            return AnimalType.SHEEP;
        } else if (randomValue < 0.60) {
            return AnimalType.WILD_BOAR;
        } else if (randomValue < 0.65) {
            return AnimalType.BUFFALO;
        } else if (randomValue < 0.70) {
            return AnimalType.DUCK;
        } else if (randomValue < 0.75) {
            return AnimalType.CATERPILLAR;
        } else {
            return null;
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
