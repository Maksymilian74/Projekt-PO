package org.example;

import java.util.HashMap;
import java.util.Map;

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
        // Sprawdź najbliższy grzyb lub owoc
        int target_x = -1;
        int target_y = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++) {
                String cell = forest.getCell(i, j);
                if (cell.equals("C") || cell.equals("T") || cell.equals("P") || cell.equals("O") || cell.equals("M") || cell.equals("I")) {
                    distance = Math.abs(position_x - i) + Math.abs(position_y - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                        target_x = i;
                        target_y = j;
                    }
                } else if ((cell.equals("B") || cell.equals("J") || cell.equals("E") || cell.equals("A"))) {
                    for (ForestFruits forestFruits : Data.ListFruits) {
                        if (forestFruits.getPosition_x() == i && forestFruits.getPosition_y() == j) {
                            ifCollected = forestFruits.getIfCollected();
                            break;
                        }
                    }
                    if (ifCollected == false) {
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
        } else {
            // Jeśli nie znaleziono celu, wykonaj losowy ruch
            randomMove();
            return;
        }

        // Jeśli nie udało się znaleźć bezpośredniego celu, sprawdź możliwość obejścia drzewa
        boolean canMoveLeft = position_y > 0 && !isTree(position_x, position_y - 1);
        boolean canMoveRight = position_y < size_y - 1 && !isTree(position_x, position_y + 1);
        boolean canMoveUp = position_x > 0 && !isTree(position_x - 1, position_y);
        boolean canMoveDown = position_x < size_x - 1 && !isTree(position_x + 1, position_y);

        // Jeśli możliwe jest poruszanie się w lewo
        if (canMoveLeft) {
            // Sprawdź czy postać może ominąć drzewo idąc w górę
            if (canMoveUp && !isTree(position_x - 1, position_y - 1)) {
                moveUp();
                return;
            }
            // Sprawdź czy postać może ominąć drzewo idąc w dół
            if (canMoveDown && !isTree(position_x + 1, position_y - 1)) {
                moveDown();
                return;
            }
        }

        // Jeśli możliwe jest poruszanie się w prawo
        if (canMoveRight) {
            // Sprawdź czy postać może ominąć drzewo idąc w górę
            if (canMoveUp && !isTree(position_x - 1, position_y + 1)) {
                moveUp();
                return;
            }
            // Sprawdź czy postać może ominąć drzewo idąc w dół
            if (canMoveDown && !isTree(position_x + 1, position_y + 1)) {
                moveDown();
                return;
            }
        }

        // Jeśli możliwe jest poruszanie się w górę
        if (canMoveUp) {
            // Sprawdź czy postać może ominąć drzewo idąc w lewo
            if (canMoveLeft && !isTree(position_x - 1, position_y - 1)) {
                moveLeft();
                return;
            }
            // Sprawdź czy postać może ominąć drzewo idąc w prawo
            if (canMoveRight && !isTree(position_x - 1, position_y + 1)) {
                moveRight();
                return;
            }
        }

        // Jeśli możliwe jest poruszanie się w dół
        if (canMoveDown) {
            // Sprawdź czy postać może ominąć drzewo idąc w lewo
            if (canMoveLeft && !isTree(position_x + 1, position_y - 1)) {
                moveLeft();
                return;
            }
            // Sprawdź czy postać może ominąć drzewo idąc w prawo
            if (canMoveRight && !isTree(position_x + 1, position_y + 1)) {
                moveRight();
                return;
            }
        }

        // Jeśli postać nie może wykonać żadnego ruchu, wykonaj losowy ruch
        randomMove();
    }


    private boolean canMoveLeft(int x, int y) {
        return y > 0 && !isTree(x, y - 1);
    }

    private boolean canMoveRight(int x, int y, int size_y) {
        return y < size_y - 1 && !isTree(x, y + 1);
    }

    private boolean canMoveUp(int x, int y) {
        return x > 0 && !isTree(x - 1, y);
    }

    private boolean canMoveDown(int x, int y, int size_x) {
        return x < size_x - 1 && !isTree(x + 1, y);
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
