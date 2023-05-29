package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Simulation.runSimulation();
        Scanner scanner = new Scanner(System.in);
        Forest forest = new Forest();

        while (true) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Wygeneruj nową mapę lasu");
            System.out.println("2. Wyświetl aktualną mapę lasu");
            System.out.println("0. Zakończ program");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    forest.getForestSize();
                    forest.generateForest();
                    System.out.println("Mapa lasu została wygenerowana.");
                    break;
                case 2:
                    System.out.println("Aktualna mapa lasu:");
                    forest.displayForest();
                    break;
                case 0:
                    System.out.println("Koniec symulacji");
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }
}