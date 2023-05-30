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
        Forest forest = new Forest();
        System.out.println("\nLegenda:");

        String colorCode;

        System.out.println("╔════════════╦═══════════════════════╗");
        System.out.println("║   Symbol   ║       Opis            ║");
        System.out.println("╠════════════╬═══════════════════════╣");

        System.out.println("║  \u263A        ║ główny bohater        ║");

        colorCode = forest.getColorCode("B");
        System.out.println("║ " + colorCode + "B" + "\u001B[0m" + "          ║ borówki               ║");

        colorCode = forest.getColorCode("J");
        System.out.println("║ " + colorCode + "J" + "\u001B[0m" + "          ║ jagody                ║");

        colorCode = forest.getColorCode("E");
        System.out.println("║ " + colorCode + "E" + "\u001B[0m" + "          ║ jeżyny                ║");

        colorCode = forest.getColorCode("A");
        System.out.println("║ " + colorCode + "A" + "\u001B[0m" + "          ║ maliny                ║");

        colorCode = forest.getColorCode("R");
        System.out.println("║ " + colorCode + "R" + "\u001B[0m" + "          ║ brzoza                ║");

        colorCode = forest.getColorCode("S");
        System.out.println("║ " + colorCode + "S" + "\u001B[0m" + "          ║ sosna                 ║");

        colorCode = forest.getColorCode("D");
        System.out.println("║ " + colorCode + "D" + "\u001B[0m" + "          ║ dąb                   ║");

        colorCode = forest.getColorCode("I");
        System.out.println("║ " + colorCode + "I" + "\u001B[0m" + "          ║ pieprznik             ║");

        colorCode = forest.getColorCode("M");
        System.out.println("║ " + colorCode + "M" + "\u001B[0m" + "          ║ maitake               ║");

        colorCode = forest.getColorCode("O");
        System.out.println("║ " + colorCode + "O" + "\u001B[0m" + "          ║ borowik               ║");

        colorCode = forest.getColorCode("P");
        System.out.println("║ " + colorCode + "P" + "\u001B[0m" + "          ║ podgrzybek            ║");

        colorCode = forest.getColorCode("C");
        System.out.println("║ " + colorCode + "C" + "\u001B[0m" + "          ║ muchomor czerwony     ║");

        colorCode = forest.getColorCode("T");
        System.out.println("║ " + colorCode + "T" + "\u001B[0m" + "          ║ muchomor sromotnikowy ║");

        colorCode = forest.getColorCode("L");
        System.out.println("║ " + colorCode + "L" + "\u001B[0m" + "          ║ puste pole lasu       ║");

        colorCode = forest.getColorCode("W");
        System.out.println("║ " + colorCode + "W" + "\u001B[0m" + "          ║ wilk                  ║");

        System.out.println("╚════════════╩═══════════════════════╝");
    }



}
