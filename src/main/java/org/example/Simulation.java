package org.example;

import java.util.Scanner;

public class Simulation {
    private int round;
    private int characterMove;
    private Forest forest;
    private int characterX;
    private int characterY;
    private String previousCell;

    public Simulation(int round, int characterMove, Forest forest) {
        this.round = round;
        this.characterMove = characterMove;
        this.forest = forest;
        this.characterX = -1;
        this.characterY = -1;
        this.previousCell = null;
    }

    public int getRound() {
        return round;
    }

    public int getCharacterMove() {
        return characterMove;
    }

    public void setCharacterMove(int characterMove) {
        this.characterMove = characterMove;
    }

    public void placeCharacter(int x, int y) {
        if (x >= 0 && x < forest.getSize_x() && y >= 0 && y < forest.getSize_y()) {
            characterX = x;
            characterY = y;
            previousCell = forest.getForestMap().get(y).get(x);
            forest.getForestMap().get(y).set(x, "*");
            System.out.println("Postać została umieszczona na pozycji (" + x + "," + y + ").");
        } else {
            System.out.println("Nieprawidłowa pozycja. Postać nie może zostać umieszczona na tej pozycji.");
        }
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
                moveCharacter(characterX - 1, characterY);
                break;
            case "prawo":
                moveCharacter(characterX + 1, characterY);
                break;
            case "góra":
                moveCharacter(characterX, characterY - 1);
                break;
            case "dół":
                moveCharacter(characterX, characterY + 1);
                break;
        }
    }

    private void moveCharacter(int newX, int newY) {
        if (newX >= 0 && newX < forest.getSize_x() && newY >= 0 && newY < forest.getSize_y()) {
            String newCell = forest.getForestMap().get(newY).get(newX);

            if (newCell.equals("D") || newCell.equals("K")) {
                System.out.println("Nie możesz przesunąć postaci na to pole.");
                return;
            }

            forest.getForestMap().get(characterY).set(characterX, previousCell);
            previousCell = newCell;
            characterX = newX;
            characterY = newY;
            forest.getForestMap().get(newY).set(newX, "*");

            if (newCell.equals("G")) {
                System.out.println("Znalazłeś grzyba!");
            }

            System.out.println("Postać przesunięta na pozycję (" + newX + "," + newY + ").");
        } else {
            System.out.println("Nieprawidłowy ruch!");
        }
    }
}
