import map.GameField;
import service.PrintStatistics;
import service.Simulation;

public class Main {
    public static void main(String[] args) {
        int width = 100;
        int height = 20;
        int totalAnimals = 100;

        GameField gameField = new GameField(width, height, totalAnimals);
        PrintStatistics print = new PrintStatistics(gameField.getField());
        Simulation simulation = new Simulation(gameField, 1000);

        simulation.start();
        print.printStatistic();

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
