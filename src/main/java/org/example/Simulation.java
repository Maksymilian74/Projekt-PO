package org.example;

import java.util.Scanner;

public class Simulation {
    private int Round;
    private int CharacterMove;
    private Forest Forest;

    public Simulation(int Round, int CharacterMove, Forest Forest) {
        this.Round = Round;
        this.CharacterMove = CharacterMove;
        Forest = new Forest();
    }

    public int getRound() {
        return Round;
    }

    public int getCharacterMove() {
        return CharacterMove;
    }

    public void setCharacterMove(int CharacterMove) {
        this.CharacterMove = CharacterMove;
    }

    public static void runSimulation() {
        System.out.println("Wszystko działa");
    }

    public void characterMove() {
        Scanner scanner = new Scanner(System.in);
        String direction;

        do {
            System.out.println("Wybierz kierunek ruchu (lewo, prawo, góra, dół):");
            direction = scanner.nextLine();
        } while (!direction.equalsIgnoreCase("lewo") &&
                !direction.equalsIgnoreCase("prawo") &&
                !direction.equalsIgnoreCase("góra") &&
                !direction.equalsIgnoreCase("dół"));

        switch (direction.toLowerCase()) {
            case "lewo":
                break;
            case "prawo":
                break;
            case "góra":
                break;
            case "dół":
                break;
        }
    }
}
