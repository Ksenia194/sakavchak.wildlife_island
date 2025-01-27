import map.GameField;
import service.Simulation;

public class Main {
    public static void main(String[] args) {

        GameField gameField = new GameField(100, 20, 100);
        Simulation simulation = new Simulation(gameField, 1000);

        simulation.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulation.pause();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        simulation.resume();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        simulation.stop();

    }
}
