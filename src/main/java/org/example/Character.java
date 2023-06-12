package org.example;

import javax.swing.JLabel;
import javax.swing.text.DefaultStyledDocument;

public class Character {
    private int position_x;
    private int position_y;
    private String previousCell;
    private Forest forest;

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = "L";
        forest.setCell(position_x, position_y, "☺");
    }

    public void moveUp() {
        if (position_x > 0) {
            String targetCell = forest.getCell(position_x - 1, position_y);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                handleCell(targetCell, position_x - 1, position_y);
            } else {
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
                handleCell(targetCell, position_x + 1, position_y);
            } else {
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        }
    }

    public void moveLeft() {
        if (position_y > 0) {
            String targetCell = forest.getCell(position_x, position_y - 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                handleCell(targetCell, position_x, position_y - 1);
            } else {
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        }
    }

    public void moveRight() {
        if (position_y < forest.getSize_y() - 1) {
            String targetCell = forest.getCell(position_x, position_y + 1);
            if (!targetCell.equals("D") && !targetCell.equals("R") && !targetCell.equals("S")) {
                handleCell(targetCell, position_x, position_y + 1);
            } else {
                forest.setCell(position_x, position_y, "\uD83D\uDE21");
            }
        }
    }

    private void handleCell(String targetCell, int new_x, int new_y) {
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
