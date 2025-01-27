package organism.plant;

import organism.Organism;

public class Plant extends Organism {
    private String type;
    private double grownRace;
    private static double currentSize;

    public Plant(String type, int growthRace) {
        super(type, growthRace);
        this.type = type;
        this.grownRace = growthRace;
        this.currentSize = 1;
    }

    @Override
    public void act() {
        grow();
    }

    public String getType() {
        return type;
    }

    public void grow() {
        currentSize += grownRace;
    }

    public static double getCurrentSize() {
        return currentSize;
    }

    public double getGrownRace() {
        return grownRace;
    }

    public void beEaten(double amount) {
        currentSize -= amount;
        if (currentSize < 0)
            currentSize = 0;
        System.out.println(type + " було з'їдено. Поточний розмір: " + currentSize);
    }
}
