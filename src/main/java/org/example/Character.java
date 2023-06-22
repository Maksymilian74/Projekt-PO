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

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = "L";
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

    public void autoMove() {
        int size_x = forest.getSize_x();
        int size_y = forest.getSize_y();
        boolean ifCollected = false;
        int distance;
        int target_x = -1;
        int target_y = -1;
        int minDistance = Integer.MAX_VALUE;
        int repeatedMovesCount = 0;
        final int MAX_REPEATED_MOVES = 3;
        int lastPositionX = position_x;
        int lastPositionY = position_y;
        int Stuck = 0;

        if (canMoveDown(position_x,position_y) || canMoveUp(position_x,position_y) || canMoveLeft(position_x,position_y) || canMoveRight(position_x,position_y)) {
            // Przeszukiwanie lasu w poszukiwaniu celu
            for (int i = 0; i < size_x; i++) {
                for (int j = 0; j < size_y; j++) {
                    String cell = forest.getCell(i, j);

                    // Jeśli cel jest grzybem, sprawdź czy został już zebrany
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

                            // Sprawdź, czy istnieje inny drzewo na kratkę powyżej postaci
                            boolean isTreeAbove = isTree(position_x, position_y - 1);
                            if (isTreeAbove && position_y > 1 && isTree(position_x, position_y - 2)) {
                                // Jeśli istnieje drzewo powyżej i na odległość 2 kratki, wykonaj inny ruch
                                // Ominięcie drzewa wykonując ruch w lewo, jeśli to możliwe
                                if (position_x > 0 && !isTree(position_x - 1, position_y - 1)) {
                                    moveUp();
                                    moveLeft();
                                    return;
                                }
                                // Ominięcie drzewa wykonując ruch w prawo, jeśli to możliwe
                                if (position_x < size_x - 1 && !isTree(position_x + 1, position_y - 1)) {
                                    moveUp();
                                    moveRight();
                                    return;
                                }
                            }

                            // Wykonaj ruch w kierunku optymalnej ścieżki
                            if (diff_y < 0 && position_y > 0 && !isTree(position_x, position_y - 1)) {
                                moveLeft();
                                return;
                            }
                            if (diff_y > 0 && position_y < size_y - 1 && !isTree(position_x, position_y + 1)) {
                                moveRight();
                                return;
                            }
                            if (diff_x < 0 && position_x > 0 && !isTree(position_x - 1, position_y)) {
                                moveUp();
                                return;
                            }
                            if (diff_x > 0 && position_x < size_x - 1 && !isTree(position_x + 1, position_y)) {
                                moveDown();
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
                        return;
                    }
                    if (diff_y > 0 && position_y < size_y - 1 && !isTree(position_x, position_y + 1)) {
                        moveRight();
                        return;
                    }
                    if (diff_x < 0 && position_x > 0 && !isTree(position_x - 1, position_y)) {
                        moveUp();
                        return;
                    }
                    if (diff_x > 0 && position_x < size_x - 1 && !isTree(position_x + 1, position_y)) {
                        moveDown();
                        return;
                    }
                }
            }
            // Jeśli nie znaleziono celu, zakończ grę
            else {
                allItemsCollected = true;
                endWindow();
                Main.setEndWindowDisplayed();
                return;
            }

            // Sprawdź, czy przekroczono maksymalną liczbę powtórzeń ruchu do tego samego miejsca
            if (position_x == lastPositionX && position_y == lastPositionY) {
                repeatedMovesCount++;
                if (repeatedMovesCount >= MAX_REPEATED_MOVES) {
                    Main.updateInfoLabel("AutoMove OFF - przeszkoda");
                    return;
                }
            } else {
                lastPositionX = position_x;
                lastPositionY = position_y;
                repeatedMovesCount = 0;
            }

            // Jeśli nie można wykonać żadnego ruchu, wykonaj losowy ruch
            if (isStuck()) {
                Main.updateInfoLabel("AutoMove OFF - brak ruchów");
                System.out.println("Tutaj!!!!!!!!!!");
            } else {
                if(Stuck < 3) {
                    randomMove();
                    Stuck++;
                } else {
                    System.out.println("To użycie!!!!!!!!!!");
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



    private boolean isStuck() {
        return (position_x == 0 || position_x == forest.getSize_x() - 1) && (position_y == 0 || position_y == forest.getSize_y() - 1)
                && isTree(position_x - 1, position_y) && isTree(position_x + 1, position_y)
                && isTree(position_x, position_y - 1) && isTree(position_x, position_y + 1);
    }

    private boolean isTree(int x, int y) {
        if (x < 0 || y < 0 || x >= forest.getSize_x() || y >= forest.getSize_y()) {
            return false;
        }

        String cell = forest.getCell(x, y);
        return cell.equals("D") || cell.equals("R") || cell.equals("S");
    }

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

    private boolean canMoveLeft(int x, int y) {
        return y > 0 && !isTree(x, y - 1);
    }

    private boolean canMoveRight(int x, int y) {
        return y < forest.getSize_y() - 1 && !isTree(x, y + 1);
    }

    private boolean canMoveUp(int x, int y) {
        return x > 0 && !isTree(x - 1, y);
    }

    private boolean canMoveDown(int x, int y) {
        return x < forest.getSize_x() - 1 && !isTree(x + 1, y);
    }

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
