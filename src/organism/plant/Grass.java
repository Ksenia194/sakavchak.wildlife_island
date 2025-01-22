package organism.plant;

public class Grass extends Plant{
    private static final int MAX_SIZE = 3;

    public Grass() {
        super("Grass", 1);
    }

    public void grow() {
        if (currentSize < MAX_SIZE) {
            currentSize++;
            System.out.println("Grass виріс. Поточний розмір: " + currentSize);
        } else {
            System.out.println("Grass досяг максимального розміру.");
        }
    }

    @Override
    public void beEaten(double amount) {
        if (currentSize > 0) {
            currentSize -= amount;
            if (currentSize < 0) {
                currentSize = 0;
            }
            System.out.println("Grass з'їдено на " + amount + ". Поточний розмір: " + currentSize);
        } else {
            System.out.println("Grass повністю з'їдено.");
        }
    }

    public static int getCurrentSize(Plant plant) {
        return currentSize;
    }
}
