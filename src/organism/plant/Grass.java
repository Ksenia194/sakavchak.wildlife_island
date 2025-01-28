package organism.plant;

public class Grass extends Plant {
    private static final int MAX_SIZE = 3;

    public Grass() {
        super("Grass", 1);
    }

    @Override
    public void grow() {
        if (getCurrentSize() < MAX_SIZE) {
            super.grow();
            System.out.println("Grass виріс. Поточний розмір: " + getCurrentSize());
        } else {
            System.out.println("Grass досяг максимального розміру.");
        }
    }

    @Override
    public void beEaten(double amount) {
        if (getCurrentSize() > 0) {
            super.beEaten(amount);
        } else {
            System.out.println("Grass повністю з'їдено.");
        }
    }
}
