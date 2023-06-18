package org.example;

import java.util.HashMap;
import java.util.Map;

public class Character {
    private int position_x;
    private int position_y;
    private String previousCell;
    private Forest forest;
    private HashMap<String, Integer> Basket;
    private Main main;

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = "L";
        forest.setCell(position_x, position_y, "☺");
        Basket = new HashMap<>();
        this.main = main;
    }

    public Map<String, Integer> getBasket() {
        return Basket;
    }

    private boolean allItemsCollected = false;

    public void autoMove() {
        int size_x = forest.getSize_x();
        int size_y = forest.getSize_y();

        // Sprawdź najbliższy grzyb lub owoc
        int target_x = -1;
        int target_y = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                String cell = forest.getCell(i, j);
                if (cell.equals("B") || cell.equals("J") || cell.equals("E") || cell.equals("A") || cell.equals("C") || cell.equals("T") || cell.equals("P") || cell.equals("O") || cell.equals("M") || cell.equals("I")) {
                    int distance = Math.abs(position_x - i) + Math.abs(position_y - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                        target_x = i;
                        target_y = j;
                    }
                }
            }
        }

        // Jeśli znaleziono cel, wykonaj ruch w jego kierunku
        if (target_x != -1 && target_y != -1) {
            // Oblicz różnice pozycji postaci i celu
            int diff_x = target_x - position_x;
            int diff_y = target_y - position_y;

            // Sprawdź czy postać może iść w lewo
            if (diff_y < 0 && position_y > 0 && !isTree(position_x, position_y - 1)) {
                moveLeft();
                return;
            }

            // Sprawdź czy postać może iść w prawo
            if (diff_y > 0 && position_y < size_y - 1 && !isTree(position_x, position_y + 1)) {
                moveRight();
                return;
            }

            // Sprawdź czy postać może iść w górę
            if (diff_x < 0 && position_x > 0 && !isTree(position_x - 1, position_y)) {
                moveUp();
                return;
            }

            // Sprawdź czy postać może iść w dół
            if (diff_x > 0 && position_x < size_x - 1 && !isTree(position_x + 1, position_y)) {
                moveDown();
                return;
            }

            // Jeśli postać nie może wykonać żadnego ruchu w kierunku celu, wykonaj losowy ruch
            randomMove();
            return;
        }

        // Jeśli nie znaleziono żadnego celu, sprawdź czy istnieją jeszcze przedmioty na mapie
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                String cell = forest.getCell(i, j);
                if (cell.equals("B") || cell.equals("J") || cell.equals("E") || cell.equals("A") || cell.equals("C") || cell.equals("T") || cell.equals("P") || cell.equals("O") || cell.equals("M") || cell.equals("I")) {
                    // Na mapie istnieje jeszcze co najmniej jeden przedmiot
                    return;
                }
            }
        }

        // Jeśli nie znaleziono żadnego celu i nie ma już przedmiotów na mapie, zakończ działanie
        allItemsCollected = true;
    }
    public boolean isAllItemsCollected() {
        return allItemsCollected;
    }

    private boolean isTree(int x, int y) {
        String cell = forest.getCell(x, y);
        return cell.equals("D") || cell.equals("R") || cell.equals("S");
    }

    private void randomMove() {
        int direction = (int) (Math.random() * 4); // Wylosuj kierunek ruchu: 0 - lewo, 1 - prawo, 2 - góra, 3 - dół

        switch (direction) {
            case 0: // Ruch w lewo
                if (position_y > 0 && !isTree(position_x, position_y - 1)) {
                    moveLeft();
                }
                break;
            case 1: // Ruch w prawo
                if (position_y < forest.getSize_y() - 1 && !isTree(position_x, position_y + 1)) {
                    moveRight();
                }
                break;
            case 2: // Ruch w górę
                if (position_x > 0 && !isTree(position_x - 1, position_y)) {
                    moveUp();
                }
                break;
            case 3: // Ruch w dół
                if (position_x < forest.getSize_x() - 1 && !isTree(position_x + 1, position_y)) {
                    moveDown();
                }
                break;
        }
    }

    public void moveUp() {
        if (position_x > 0) {
            String targetCell = forest.getCell(position_x - 1, position_y);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x - 1, position_y);
                handleCell(targetCell, position_x - 1, position_y);
            } else {
                main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            main.endWindow();
        }
    }

    public void moveDown() {
        if (position_x < forest.getSize_x() - 1) {
            String targetCell = forest.getCell(position_x + 1, position_y);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x + 1, position_y);
                handleCell(targetCell, position_x + 1, position_y);
            } else {
                main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            main.endWindow();
        }
    }

    public void moveLeft() {
        if (position_y > 0) {
            String targetCell = forest.getCell(position_x, position_y - 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x, position_y - 1);
                handleCell(targetCell, position_x, position_y - 1);
            } else {
                main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            main.endWindow();
        }
    }

    public void moveRight() {
        if (position_y < forest.getSize_y() - 1) {
            String targetCell = forest.getCell(position_x, position_y + 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x, position_y + 1);
                handleCell(targetCell, position_x, position_y + 1);
            } else {
                main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            main.endWindow();
        }
    }

    private void handleCell(String cell, int x, int y) {
        forest.setCell(position_x, position_y, previousCell);
        previousCell = cell;
        if(previousCell == "W")
            previousCell = "W";
        else
            previousCell = "L";

        position_x = x;
        position_y = y;
        forest.setCell(position_x, position_y, "☺");
    }

    private void collectItem(String cell, int x, int y) {
        if (cell.equals("B") || cell.equals("J") || cell.equals("E") || cell.equals("A") || cell.equals("C") || cell.equals("T") || cell.equals("P") || cell.equals("O") || cell.equals("M") || cell.equals("I")) {
            if (!Basket.containsKey(cell)) {
                Basket.put(cell, 1);
                main.updateInfoLabel("Zebrałeś " + getNazwa(cell));
            }
        }
    }

    private void avoidWolf() {
        int size_x = forest.getSize_x();
        int size_y = forest.getSize_y();

        // Przeszukaj sąsiednie pola w poszukiwaniu bezpiecznego miejsca
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                String cell = forest.getCell(i, j);
                if (!cell.equals("W") && !cell.equals("D") && !cell.equals("R") && !cell.equals("S")) {
                    handleCell(cell, i, j);
                    main.updateInfoLabel("Uciekłeś przed wilkiem!");
                    return;
                }
            }
        }
    }

    private String getNazwa(String key) {
        // Zwraca nazwę dla danego klucza
        switch (key) {
            case "B":
                return "borówki";
            case "J":
                return "jagody";
            case "E":
                return "jeżyny";
            case "A":
                return "maliny";
            case "C":
                return "muchomor czerwony";
            case "T":
                return "muchomor sromotnikowy";
            case "P":
                return "podgrzybek";
            case "O":
                return "borowik";
            case "M":
                return "maitake";
            case "I":
                return "pieprznik";
            default:
                return "";
        }
    }
}
