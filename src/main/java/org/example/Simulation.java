package org.example;

public class Simulation {
    private Forest forest;
    private Character character;
    private boolean running;

    public Simulation(Forest forest) {
        this.forest = forest;
        character = new Character(forest);
        running = false;
    }

    public void start() {
        running = true;
        simulate();
    }

    public void stop() {
        running = false;
    }

    private void simulate() {
        while (running) {
            // Wykonaj operacje symulacji

            try {
                Thread.sleep(1000); // Odczekaj 1 sekundę przed kolejną iteracją
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}