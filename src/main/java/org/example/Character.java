package org.example;

import java.util.HashMap;
import java.util.Map;

public class Character {
    private int position_x;
    private int position_y;
    private String previousCell;
    private Forest forest;
    private HashMap<String, Integer> Basket;

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = "L";
        forest.setCell(position_x, position_y, "☺");
        Basket = new HashMap<>();
    }

    public Map<String,Integer> getBasket() {
        return Basket;
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
        }
        /*else {

        }*/
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
        }
    }

    private void collectItem(String targetCell,int new_x, int new_y) {
        String nazwa="";
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

        if(!targetCell.equals("L") && !targetCell.equals("W")) {
            if (targetCell.equals("B") || targetCell.equals("J") || targetCell.equals("E") || targetCell.equals("A")) {
                //sprawdzenie czy jest zebrany
                for(ForestFruits forestFruits: Data.ListFruits) {
                    if(forestFruits.getPosition_x() == new_x && forestFruits.getPosition_y() == new_y) {
                        ifCollected = forestFruits.getIfCollected();
                        break;
                    }

                }

                if(ifCollected == true) {//jak krzak jest zebrany
                    Main.updateInfoLabel("Krzak był już zebrany");
                } else { //jak krzak nie jest zebrany
                    Main.updateInfoLabel("Zebrałeś z owoc");
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

        for (Map.Entry<String, Integer> entry : Basket.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            System.out.println(word + ": " + count);
        }
        System.out.println();
    }

    private void handleCell(String targetCell, int new_x, int new_y) {
        if(targetCell.equals("M") || targetCell.equals("I") || targetCell.equals("O") || targetCell.equals("P") || targetCell.equals("C") || targetCell.equals("T")) {
            targetCell = "L";
        } else if (targetCell.equals("B") || targetCell.equals("J") || targetCell.equals("E") || targetCell.equals("A")) {
            for(ForestFruits forestFruits: Data.ListFruits) {
                if(forestFruits.getPosition_x() == new_x && forestFruits.getPosition_y() == new_y) {
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
