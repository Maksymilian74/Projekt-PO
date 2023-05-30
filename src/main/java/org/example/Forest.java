package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Forest {
    private int size_x;
    private int size_y;
    private Data Data;
    private ArrayList<ArrayList<String>> forestMap;
    public Forest() {
        this.size_x = size_x;
        this.size_y = size_y;
        Data = new Data();
        this.forestMap = new ArrayList<>();
    }

    public int getSize_x() {
        return size_x;
    }

    public void setSize_x(int size_x) {
        this.size_x = size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public void setSize_y(int size_y) {
        this.size_y = size_y;
    }

    public ArrayList<ArrayList<String>> getForestMap() {
        return forestMap;
    }

    public void getForestSize() {
        Scanner scanner = new Scanner(System.in);
        int width, height;

        while (true) {
            System.out.println("Podaj szerokość lasu (do 50): ");
            width = scanner.nextInt();
            if (width <= 50)
                break;
            else
                System.out.println("Nieprawidłowa szerokość. Podaj wartość do 50.");
        }

        while (true) {
            System.out.println("Podaj wysokość lasu (do 50): ");
            height = scanner.nextInt();
            if (height <= 50)
                break;
            else
                System.out.println("Nieprawidłowa wysokość. Podaj wartość do 50.");
        }

        setSize_x(width);
        setSize_y(height);
    }
    public void generateForest() {
        //forestMap.clear();
        Random random = new Random();
        System.out.println("x: " + getSize_x());
        System.out.println("y: " + getSize_y());
        for (int y = 0; y < getSize_y(); y++) {
            ArrayList<String> row = new ArrayList<>();
            for (int x = 0; x < getSize_x(); x++) {
                //tutaj trzeba zrobić mechanizm generowania poszczególnych obiektów w lesie
                double randomNumber = random.nextDouble();
                if ((x == 0) || (x == getSize_x()-1) || (y == 0) || (y == getSize_y()-1)) {
                    //1 przypadek obrzeża lasu
                    if (randomNumber < 0.4) {
                        row.add("L");
                    } else if (randomNumber < 0.75) {
                        row.add("G");
                    } else if (randomNumber < 0.97) {
                        row.add("K");
                    } else {
                        row.add("W");
                    }
                } else {
                        if (randomNumber < 0.2) {
                            row.add("D");
                        } else if (randomNumber < 0.5) {
                            row.add("G");
                        } else if (randomNumber < 0.75) {
                            row.add("L");
                        } else if (randomNumber < 0.98) {
                            row.add("K");
                        } else {
                            row.add("W");
                        }

                        }
                    }

                forestMap.add(row);
            }
        }

    public void displayForest() {
        for (ArrayList<String> row : forestMap) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
