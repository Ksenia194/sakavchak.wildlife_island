package service;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private static final Map<String, Integer> eatingProbabilities = new HashMap<>();

    static {
        eatingProbabilities.put("Wolf-Horse", 10);
        eatingProbabilities.put("Wolf-Deer", 15);
        eatingProbabilities.put("Wolf-Rabbit", 60);
        eatingProbabilities.put("Wolf-Mouse", 80);
        eatingProbabilities.put("Wolf-Goat", 60);
        eatingProbabilities.put("Wolf-Sheep", 70);
        eatingProbabilities.put("Wolf-WildBoar", 15);
        eatingProbabilities.put("Wolf-Buffalo", 10);
        eatingProbabilities.put("Wolf-Duck", 40);

        eatingProbabilities.put("Python-Fox", 15);
        eatingProbabilities.put("Python-Rabbit", 60);
        eatingProbabilities.put("Python-Mouse", 40);
        eatingProbabilities.put("Python-Duck", 10);

        eatingProbabilities.put("Fox-Rabbit", 70);
        eatingProbabilities.put("Fox-Mouse", 90);
        eatingProbabilities.put("Fox-Duck", 60);
        eatingProbabilities.put("Fox-Caterpillar", 40);

        eatingProbabilities.put("Bear-Python", 80);
        eatingProbabilities.put("Bear-Horse", 40);
        eatingProbabilities.put("Bear-Deer", 80);
        eatingProbabilities.put("Bear-Rabbit", 80);
        eatingProbabilities.put("Bear-Mouse", 90);
        eatingProbabilities.put("Bear-Goat", 70);
        eatingProbabilities.put("Bear-Sheep", 70);
        eatingProbabilities.put("Bear-WildBoar", 50);
        eatingProbabilities.put("Bear-Buffalo", 20);
        eatingProbabilities.put("Bear-Duck", 10);

        eatingProbabilities.put("Eagle-Fox", 10);
        eatingProbabilities.put("Eagle-Rabbit", 90);
        eatingProbabilities.put("Eagle-Mouse", 90);
        eatingProbabilities.put("Eagle-Duck", 80);

        eatingProbabilities.put("Horse-Grass", 100);

        eatingProbabilities.put("Deer-Grass", 100);

        eatingProbabilities.put("Rabbit-Grass", 100);

        eatingProbabilities.put("Mouse-Caterpillar", 90);
        eatingProbabilities.put("Mouse-Grass", 100);

        eatingProbabilities.put("Goat-Grass", 100);

        eatingProbabilities.put("Sheep-Grass", 100);

        eatingProbabilities.put("WildBoar-Mouse", 50);
        eatingProbabilities.put("WildBoar-Caterpillar", 90);
        eatingProbabilities.put("WildBoar-Grass", 100);

        eatingProbabilities.put("Buffalo-Grass", 100);

        eatingProbabilities.put("Duck-Caterpillar", 90);
        eatingProbabilities.put("Duck-Grass", 100);

        eatingProbabilities.put("Caterpillar-Grass", 100);
    }

    public static int getEatingProbability(String predator, String prey) {
        return eatingProbabilities.getOrDefault(predator + "-" + prey, 0);
    }

    private static void addProbability(String predator, String prey, int probability) {
        if (probability > 0) {
            eatingProbabilities.put(predator + "-" + prey, probability);
        }
    }

    public static int getPlantEatingProbability(String herbivore) {
        return eatingProbabilities.getOrDefault(herbivore + "-Plant", 0);
    }
}
