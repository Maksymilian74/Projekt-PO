package org.example;

public class Character {
    private int position_x;
    private int position_y;
    private String previousCell;
    private Forest forest;

    public Character(Forest forest) {
        this.forest = forest;
        position_x = 0;
        position_y = 0;
        previousCell = " ";
        forest.setCell(position_x, position_y, "☺");
    }

    public void moveUp() {
        if (position_x > 0) {
            forest.setCell(position_x, position_y, previousCell);
            position_x--;
            previousCell = forest.getCell(position_x, position_y);
            forest.setCell(position_x, position_y, "☺");
        }
    }

    public void moveDown() {
        if (position_x < forest.getSize_x() - 1) {
            forest.setCell(position_x, position_y, previousCell);
            position_x++;
            previousCell = forest.getCell(position_x, position_y);
            forest.setCell(position_x, position_y, "☺");
        }
    }

    public void moveLeft() {
        if (position_y > 0) {
            forest.setCell(position_x, position_y, previousCell);
            position_y--;
            previousCell = forest.getCell(position_x, position_y);
            forest.setCell(position_x, position_y, "☺");
        }
    }

    public void moveRight() {
        if (position_y < forest.getSize_y() - 1) {
            forest.setCell(position_x, position_y, previousCell);
            position_y++;
            previousCell = forest.getCell(position_x, position_y);
            forest.setCell(position_x, position_y, "☺");
        }
    }
}
