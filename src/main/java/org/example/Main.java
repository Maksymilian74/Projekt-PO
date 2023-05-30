package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Forest forest = new Forest();
        Simulation simulation = new Simulation(0, 0, forest);

        while (true) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Wygeneruj nową mapę lasu");
            System.out.println("2. Wyświetl aktualną mapę lasu");
            System.out.println("3. Ustaw postać na pozycji 0,0");
            System.out.println("4. Porusz postacią");
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
                    displayLegend();
                    break;
                case 3:
                    simulation.placeCharacter(0, 0);
                    System.out.println("Postać została umieszczona na pozycji 0,0.");
                    break;
                case 4:
                    simulation.characterMove();
                    break;
                case 0:
                    System.out.println("Koniec symulacji");
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja. Spróbuj ponownie.");
            }
        }
    }

    private static void displayLegend() {
        System.out.println("\nLegenda:");
        System.out.println("B - borówki");
        System.out.println("J - jagody");
        System.out.println("E - jeżyny");
        System.out.println("A - maliny");
        System.out.println("R - brzoza");
        System.out.println("S - sosna");
        System.out.println("D - dąb");
        System.out.println("I - pieprznik");
        System.out.println("M - maitake");
        System.out.println("O - borowik");
        System.out.println("P - podgrzybek");
        System.out.println("C - muchomor czerwony");
        System.out.println("T - muchomor sromotnikowy");
        System.out.println("L - puste pole lasu");
        System.out.println("W - wilk");
    }
}
