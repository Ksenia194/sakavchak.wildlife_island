package map;

import interfaces.AnimalType;
import organism.animals.Animal;
import organism.animals.harbivores.*;
import organism.animals.predator.*;
import organism.plant.Grass;
import organism.plant.Plant;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameField {

    private final int height;
    private final int width;
    private Cell[][] field;
    private ExecutorService executor;

    public GameField(int height, int width, int totalAnimals) {
        this.height = height;
        this.width = width;
        field = new Cell[width][height];
        initializeField();
        int threadCount = Math.min(totalAnimals, Runtime.getRuntime().availableProcessors());
        executor = Executors.newFixedThreadPool(threadCount);
    }

    private void initializeField() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j] = new Cell();

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
        switch (animalType) {
            case WOLF:
                if (cell.canAddAnimal(new Wolf())) {
                    cell.addAnimal(new Wolf());
                }
                break;
            case PYTHON:
                if (cell.canAddAnimal(new Python())) {
                    cell.addAnimal(new Python());
                }
                break;
            case FOX:
                if (cell.canAddAnimal(new Fox())) {
                    cell.addAnimal(new Fox());
                }
                break;
            case BEAR:
                if (cell.canAddAnimal(new Bear())) {
                    cell.addAnimal(new Bear());
                }
                break;
            case EAGLE:
                if (cell.canAddAnimal(new Eagle())) {
                    cell.addAnimal(new Eagle());
                }
                break;
            case HORSE:
                if (cell.canAddAnimal(new Horse())) {
                    cell.addAnimal(new Horse());
                }
                break;
            case DEER:
                if (cell.canAddAnimal(new Deer())) {
                    cell.addAnimal(new Deer());
                }
                break;
            case RABBIT:
                if (cell.canAddAnimal(new Rabbit())) {
                    cell.addAnimal(new Rabbit());
                }
                break;
            case MOUSE:
                if (cell.canAddAnimal(new Mouse())) {
                    cell.addAnimal(new Mouse());
                }
                break;
            case GOAT:
                if (cell.canAddAnimal(new Goat())) {
                    cell.addAnimal(new Goat());
                }
                break;
            case SHEEP:
                if (cell.canAddAnimal(new Sheep())) {
                    cell.addAnimal(new Sheep());
                }
                break;
            case WILD_BOAR:
                if (cell.canAddAnimal(new WildBoar())) {
                    cell.addAnimal(new WildBoar());
                }
                break;
            case BUFFALO:
                if (cell.canAddAnimal(new Buffalo())) {
                    cell.addAnimal(new Buffalo());
                }
                break;
            case DUCK:
                if (cell.canAddAnimal(new Duck())) {
                    cell.addAnimal(new Duck());
                }
                break;
            case CATERPILLAR:
                if (cell.canAddAnimal(new Caterpillar())) {
                    cell.addAnimal(new Caterpillar());
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

    //    public void printStatistics() {
//        int totalAnimals = 0;
//        int totalPlants = 0;
//
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                int animalsCount = field[i][j].getAnimals().size();
//                int plantsCount = field[i][j].getPlantCount();
//
//                totalAnimals += animalsCount;
//                totalPlants += plantsCount;
//
//                System.out.println("Клітинка (" + i + "," + j + "): " +
//                        animalsCount + " тварини, " +
//                        plantsCount + " рослини.");
//            }
//        }
//
//        System.out.println("Всі тварини: " + totalAnimals);
//        System.out.println("Всі рослини: " + totalPlants);
//    }

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

    public void growPlants() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                field[i][j].grownPlant();
            }
        }
    }
}
