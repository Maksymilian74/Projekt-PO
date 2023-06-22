package org.example;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class Character {
    private int position_x;
    private int position_y;
    private String previousCell;
    private Forest forest;
    private HashMap<String, Integer> Basket;
    private boolean allItemsCollected = false;
    int repeatedMovesCount;

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = "L";
        repeatedMovesCount = 0;
        forest.setCell(position_x, position_y, "☺");
        Basket = new HashMap<>();
    }

    public Map<String, Integer> getBasket() {
        return Basket;
    }
    public boolean isAllItemsCollected() {
        return allItemsCollected;
    }
    private void endWindow() {
        Main.endWindow(this);
    }

    /*
        Metoda odpowiedzialna za poruszanie się w górę
    */

    public void moveUp() {
        if (position_x > 0) {
            String targetCell = forest.getCell(position_x - 1, position_y);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x - 1, position_y);
                handleCell(targetCell, position_x - 1, position_y);
            } else {
                Main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            endWindow();
        }
    }

    /*
        Metoda odpowiedzialna za poruszanie się w dół
    */

    public void moveDown() {
        if (position_x < forest.getSize_x() - 1) {
            String targetCell = forest.getCell(position_x + 1, position_y);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x + 1, position_y);
                handleCell(targetCell, position_x + 1, position_y);
            } else {
                Main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            endWindow();
        }
    }

    /*
        Metoda odpowiedzialna za poruszanie się w lewo
    */

    public void moveLeft() {
        if (position_y > 0) {
            String targetCell = forest.getCell(position_x, position_y - 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x, position_y - 1);
                handleCell(targetCell, position_x, position_y - 1);
            } else {
                Main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            endWindow();
        }
    }

    /*
        Metoda odpowiedzialna za poruszanie się w prawo
    */

    public void moveRight() {
        if (position_y < forest.getSize_y() - 1) {
            String targetCell = forest.getCell(position_x, position_y + 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                collectItem(targetCell, position_x, position_y + 1);
                handleCell(targetCell, position_x, position_y + 1);
            } else {
                Main.updateInfoLabel("Nie możesz tam iść");
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        } else {
            endWindow();
        }
    }

    /*
        Metoda odpowiedzialna za zbieranie przedmiotów do koszyka
    */

    private void collectItem(String targetCell, int new_x, int new_y) {
        String nazwa = "";
        boolean ifCollected = false;
        switch (targetCell) {
            case "B":
                nazwa = "Borówki";
                Main.updateInfoLabel("");
                break;
            case "J":
                nazwa = "Jagody";
                Main.updateInfoLabel("");
                break;
            case "E":
                nazwa = "Jeżyny";
                Main.updateInfoLabel("");
                break;
            case "A":
                nazwa = "Maliny";
                Main.updateInfoLabel("");
                break;
            case "C":
                nazwa = "Muchomor Czerwony";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "T":
                nazwa = "Muchomor Sromotnikowy";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "P":
                nazwa = "Podgrzybek";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "O":
                nazwa = "Borowik";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "M":
                nazwa = "Maitake";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "I":
                nazwa = "Pieprznik";
                Main.updateInfoLabel("Zebrałeś grzyba");
                break;
            case "W":
                Basket.clear();
                Main.updateInfoLabel("Wilk zjadł ci wszystko");
                break;
            case "L":
                Main.updateInfoLabel("");
                break;
            default:
                break;
        }

        if (!targetCell.equals("L") && !targetCell.equals("W")) {
            if (targetCell.equals("B") || targetCell.equals("J") || targetCell.equals("E") || targetCell.equals("A")) {
                //sprawdzenie czy jest zebrany
                for (ForestFruits forestFruits : Data.ListFruits) {
                    if (forestFruits.getPosition_x() == new_x && forestFruits.getPosition_y() == new_y) {
                        ifCollected = forestFruits.getIfCollected();
                        break;
                    }

                }

                if (ifCollected == true) {//jak krzak jest zebrany
                    Main.updateInfoLabel("Krzak był już zebrany");
                } else { //jak krzak nie jest zebrany
                    Main.updateInfoLabel("Zebrałeś owoc");
                    if (Basket.containsKey(nazwa)) {
                        int count = Basket.get(nazwa);
                        Basket.put(nazwa, count + 1);
                    } else {
                        Basket.put(nazwa, 1);
                    }
                }
            } else {
                if (Basket.containsKey(nazwa)) {
                    int count = Basket.get(nazwa);
                    Basket.put(nazwa, count + 1);
                } else {
                    Basket.put(nazwa, 1);
                }
            }
        }
        System.out.println("Ilość ruchów: " + Main.getRound());
        for (Map.Entry<String, Integer> entry : Basket.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + ": " + count);
        }
        System.out.println();
    }

    /*
        Metoda odpowiedzialna za automatyczne poruszanie się postacia, wywoływana przez autoMoveCharacter z klasy Main
    */

    public void autoMove() {
        int size_x = forest.getSize_x();
        int size_y = forest.getSize_y();
        boolean ifCollected = false;
        int distance;
        int target_x = -1;
        int target_y = -1;
        int minDistance = Integer.MAX_VALUE;
        final int MAX_REPEATED_MOVES = 6;
        int lastPositionX = position_x;
        int lastLastPositionX = position_x;
        int lastPositionY = position_x;
        int lastLastPositionY = position_y;
        int Stuck = 0;

        if (canMoveDown(position_x,position_y) || canMoveUp(position_x,position_y) || canMoveLeft(position_x,position_y) || canMoveRight(position_x,position_y)) {
            // Przeszukiwanie lasu w poszukiwaniu celu
            for (int i = 0; i < size_x; i++) {
                for (int j = 0; j < size_y; j++) {
                    String cell = forest.getCell(i, j);

                    // Jeśli cel jest grzybem
                    if (cell.equals("C") || cell.equals("T") || cell.equals("P") || cell.equals("O") || cell.equals("M") || cell.equals("I")) {
                        distance = Math.abs(position_x - i) + Math.abs(position_y - j);
                        if (distance < minDistance) {
                            minDistance = distance;
                            target_x = i;
                            target_y = j;
                        }
                    }
                    // Jeśli cel jest owocem, sprawdź czy został już zebrany
                    else if ((cell.equals("B") || cell.equals("J") || cell.equals("E") || cell.equals("A"))) {
                        for (ForestFruits forestFruits : Data.ListFruits) {
                            if (forestFruits.getPosition_x() == i && forestFruits.getPosition_y() == j) {
                                ifCollected = forestFruits.getIfCollected();
                                break;
                            }
                        }
                        if (!ifCollected) {
                            distance = Math.abs(position_x - i) + Math.abs(position_y - j);
                            if (distance < minDistance) {
                                minDistance = distance;
                                target_x = i;
                                target_y = j;
                            }
                        }
                    }
                }
            }

            // Wykonaj ruch w kierunku znalezionego celu
            if (target_x != -1 && target_y != -1) {
                // Sprawdź, czy drzewo blokuje bezpośrednią ścieżkę
                if (isTree(target_x, target_y)) {
                    // Sprawdź, czy można ominąć drzewo
                    List<Point> alternativePaths = findAlternativePaths(target_x, target_y);
                    if (!alternativePaths.isEmpty()) {
                        // Wybierz najbardziej optymalną trasę za pomocą algorytmu A*
                        Point optimalPath = findOptimalPath(alternativePaths);
                        if (optimalPath != null) {
                            int diff_x = optimalPath.x - position_x;
                            int diff_y = optimalPath.y - position_y;

                            // Sprawdź, czy istnieje inny drzewo na polu powyżej postaci
                            boolean isTreeAbove = isTree(position_x, position_y - 1);
                            if (isTreeAbove && position_y > 1 && isTree(position_x, position_y - 2)) {
                                // Jeśli istnieje drzewo powyżej i na odległość 2 pól, wykonaj inny ruch
                                // Ominięcie drzewa wykonując ruch w lewo, jeśli to możliwe
                                if (position_x > 0 && !isTree(position_x - 1, position_y - 1)) {
                                    moveUp();
                                    moveLeft();
                                    if(repeatedMovesCount>0)
                                    repeatedMovesCount--;
                                    return;
                                }
                                // Ominięcie drzewa wykonując ruch w prawo, jeśli to możliwe
                                if (position_x < size_x - 1 && !isTree(position_x + 1, position_y - 1)) {
                                    moveUp();
                                    moveRight();
                                    if(repeatedMovesCount>0)
                                    repeatedMovesCount--;
                                    return;
                                }
                            }

                            // Wykonaj ruch w kierunku optymalnej ścieżki
                            if (diff_y < 0 && position_y > 0 && !isTree(position_x, position_y - 1)) {
                                moveLeft();
                                if(repeatedMovesCount>0)
                                repeatedMovesCount--;
                                return;
                            }
                            if (diff_y > 0 && position_y < size_y - 1 && !isTree(position_x, position_y + 1)) {
                                moveRight();
                                if(repeatedMovesCount>0)
                                repeatedMovesCount--;
                                return;
                            }
                            if (diff_x < 0 && position_x > 0 && !isTree(position_x - 1, position_y)) {
                                moveUp();
                                if(repeatedMovesCount>0)
                                repeatedMovesCount--;
                                return;
                            }
                            if (diff_x > 0 && position_x < size_x - 1 && !isTree(position_x + 1, position_y)) {
                                moveDown();
                                if(repeatedMovesCount>0)
                                repeatedMovesCount--;
                                return;
                            }
                        }
                    } else {
                        // Brak alternatywnych tras - wykonaj losowy ruch
                        randomMove();
                        return;
                    }
                } else {
                    int diff_x = target_x - position_x;
                    int diff_y = target_y - position_y;

                    // Wykonaj ruch w kierunku celu
                    if (diff_y < 0 && position_y > 0 && !isTree(position_x, position_y - 1)) {
                        moveLeft();
                        if(repeatedMovesCount>0)
                        repeatedMovesCount--;
                        return;
                    }
                    if (diff_y > 0 && position_y < size_y - 1 && !isTree(position_x, position_y + 1)) {
                        moveRight();
                        if(repeatedMovesCount>0)
                        repeatedMovesCount--;
                        return;
                    }
                    if (diff_x < 0 && position_x > 0 && !isTree(position_x - 1, position_y)) {
                        moveUp();
                        if(repeatedMovesCount>0)
                        repeatedMovesCount--;
                        return;
                    }
                    if (diff_x > 0 && position_x < size_x - 1 && !isTree(position_x + 1, position_y)) {
                        moveDown();
                        if(repeatedMovesCount>0)
                        repeatedMovesCount--;
                        return;
                    }
                }
            }
            // Jeśli nie znaleziono celu, zakończ symulację
            else {
                allItemsCollected = true;
                endWindow();
                Main.setEndWindowDisplayed();
                return;
            }
            // Sprawdź, czy przekroczono maksymalną liczbę powtórzeń ruchu do tego samego miejsca
            if (position_x == lastLastPositionX && position_y == lastLastPositionY) {
                repeatedMovesCount++;
                if (repeatedMovesCount >= MAX_REPEATED_MOVES) {
                    Main.updateInfoLabel("AutoMove OFF - przeszkoda");
                    Main.setAutoMove();
                    endWindow();
                    Main.setEndWindowDisplayed();
                    return;
                }
            } else {
                lastLastPositionX = lastPositionX;
                lastLastPositionY = lastPositionY;
                lastPositionX = position_x;
                lastPositionY = position_y;
                repeatedMovesCount--;
            }

            // Jeśli nie można wykonać żadnego ruchu, wykonaj losowy ruch
            if (isStuck()) {
                Main.updateInfoLabel("AutoMove OFF - brak ruchów");
                Main.setAutoMove();
                endWindow();
                Main.setEndWindowDisplayed();
            } else {
                if(Stuck < 3) {
                    randomMove();
                    Stuck++;
                } else {
                    Main.updateInfoLabel("AutoMove OFF");
                    Main.setAutoMove();
                    endWindow();
                    Main.setEndWindowDisplayed();
                }
            }
        } else {
            Main.updateInfoLabel("AutoMove OFF");
            Main.setAutoMove();
            endWindow();
            Main.setEndWindowDisplayed();

        }
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy postać utknęła
    */

    private boolean isStuck() {
        return (position_x == 0 || position_x == forest.getSize_x() - 1) && (position_y == 0 || position_y == forest.getSize_y() - 1)
                && isTree(position_x - 1, position_y) && isTree(position_x + 1, position_y)
                && isTree(position_x, position_y - 1) && isTree(position_x, position_y + 1);
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy na naszej drodze jest drzewo
    */

    private boolean isTree(int x, int y) {
        if (x < 0 || y < 0 || x >= forest.getSize_x() || y >= forest.getSize_y()) {
            return false;
        }

        String cell = forest.getCell(x, y);
        return cell.equals("D") || cell.equals("R") || cell.equals("S");
    }

    /*
        Metoda odpowiedzialna za znalezienie alternatywnej ścieżki
    */

    private List<Point> findAlternativePaths(int x, int y) {
        List<Point> paths = new ArrayList<>();

        // Sprawdź dostępne sąsiednie komórki
        if (canMoveLeft(x, y)) {
            paths.add(new Point(x, y - 1));
        }
        if (canMoveRight(x, y)) {
            paths.add(new Point(x, y + 1));
        }
        if (canMoveUp(x, y)) {
            paths.add(new Point(x - 1, y));
        }
        if (canMoveDown(x, y)) {
            paths.add(new Point(x + 1, y));
        }

        return paths;
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy postać może się poruszyć w lewo
    */

    private boolean canMoveLeft(int x, int y) {
        return y > 0 && !isTree(x, y - 1);
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy postać może się poruszyć w prawo
    */

    private boolean canMoveRight(int x, int y) {
        return y < forest.getSize_y() - 1 && !isTree(x, y + 1);
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy postać może się poruszyć w górę
    */

    private boolean canMoveUp(int x, int y) {
        return x > 0 && !isTree(x - 1, y);
    }

    /*
        Metoda odpowiedzialna za sprawdzenie, czy postać może się poruszyć w dół
    */

    private boolean canMoveDown(int x, int y) {
        return x < forest.getSize_x() - 1 && !isTree(x + 1, y);
    }

    /*
        Metoda odpowiedzialna za znalezeinie optymalnej ścieżki
    */

    private Point findOptimalPath(List<Point> paths) {
        Point optimalPath = null;
        int minDistance = Integer.MAX_VALUE;

        for (Point path : paths) {
            int distance = Math.abs(position_x - path.x) + Math.abs(position_y - path.y);
            if (distance < minDistance) {
                minDistance = distance;
                optimalPath = path;
            }
        }

        return optimalPath;
    }

    /*
        Metoda odpowiedzialna za wykonanie losowego ruchu
    */

    private void randomMove() {
        // Wykonaj losowy ruch
        Random random = new Random();
        int direction = random.nextInt(4); // 0 - lewo, 1 - prawo, 2 - góra, 3 - dół

        switch (direction) {
            case 0: // lewo
                if (position_y > 0 && !isTree(position_x, position_y - 1)) {
                    moveLeft();
                    return;
                }
                break;
            case 1: // prawo
                if (position_y < forest.getSize_y() - 1 && !isTree(position_x, position_y + 1)) {
                    moveRight();
                    return;
                }
                break;
            case 2: // góra
                if (position_x > 0 && !isTree(position_x - 1, position_y)) {
                    moveUp();
                    return;
                }
                break;
            case 3: // dół
                if (position_x < forest.getSize_x() - 1 && !isTree(position_x + 1, position_y)) {
                    moveDown();
                    return;
                }
                break;
        }
    }

    /*
        Metoda odpowiedzialna za przechowanie zawartości pola przed stanięciem tam postacią
    */

    private void handleCell(String targetCell, int new_x, int new_y) {
        if (targetCell.equals("M") || targetCell.equals("I") || targetCell.equals("O") || targetCell.equals("P") || targetCell.equals("C") || targetCell.equals("T")) {
            targetCell = "L";
        } else if (targetCell.equals("B") || targetCell.equals("J") || targetCell.equals("E") || targetCell.equals("A")) {
            for (ForestFruits forestFruits : Data.ListFruits) {
                if (forestFruits.getPosition_x() == new_x && forestFruits.getPosition_y() == new_y) {
                    forestFruits.setIfCollected(true);
                    break;
                }
            }
        }
        forest.setCell(position_x, position_y, previousCell);
        position_x = new_x;
        position_y = new_y;
        previousCell = targetCell;
        forest.setCell(position_x, position_y, "☺");

        if (targetCell.equals("W")) {
            forest.setCell(position_x, position_y, "☹");
        }
    }
}
