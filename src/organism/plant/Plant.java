package organism.plant;

import organism.Organism;

public class Plant extends Organism {
    private String type;
    private double growthRace;
    public static int currentSize;

    public Plant(String type, int growthRace) {
        super();
        this.type = type;
        this.growthRace = growthRace;
        this.currentSize = 1;
    }

    @Override
    public void act() {

    }

    public String getType() {
        return type;
    }

    public void grown() {
        currentSize += growthRace;
    }

    public static double getCurrentSize() {
        return currentSize;
    }

    public void beEaten(double amount) {
        currentSize -= amount;
        if (currentSize < 0)
            currentSize = 0;
        System.out.println(type + " було з'їдено. Поточний розмір: " + currentSize);
    }

    public boolean isEdible() {
        return currentSize > 0;
    }
}
