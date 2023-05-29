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
        System.out.println("y: "+ getSize_y());
        for (int y = 0; y < getSize_y(); y++) {
            ArrayList<String> row = new ArrayList<>();
            for (int x = 0; x < getSize_x(); x++) {
                //tutaj trzeba zrobić mechanizm generowania poszczególnych obiektów w lesie
                double randomNumber = random.nextDouble();
                //if(x > 0 && x < (getSize_x()-1) && y > 0 && y < (getSize_y()-1))
                {
                    //if(forestMap.get(y).get(x-1).equals("D") || forestMap.get(y).get(x+1).equals("D") || forestMap.get(y-1).get(x).equals("D") || forestMap.get(y+1).get(x).equals("D")) {
                    //1 przypadek sprawdzenie czy obok nie ma innego drzewa
                    if(randomNumber < 0.3) {
                        row.add("D");
                    } else if(randomNumber < 0.6) {
                        row.add("G");
                    } else if (randomNumber < 0.9) {
                        row.add("K");
                    } else {
                        row.add("W");
                    }


                    // } else {
                    //2 przypadek nie obrzeża lasu i z drzewem obok
                    if(randomNumber < 0.1) {
                        row.add("D");
                    } else if(randomNumber < 0.5) {
                        row.add("G");
                    } else if (randomNumber < 0.9) {
                        row.add("K");
                    } else {
                        row.add("W");
                    }

                }

                //}
                /*else {
                    //3 przypadek obrzeża lasu
                    if(randomNumber < 0.6) {
                        row.add("G");
                    } else if (randomNumber < 0.9) {
                        row.add("K");
                    } else {
                        row.add("W");
                    }


                }*/


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
