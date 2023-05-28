package org.example;

import java.util.Scanner;

public class Forest {
    private int size_x;
    private int size_y;
    private Data Data;

    public Forest() {
        this.size_x = size_x;
        this.size_y = size_y;
        Data = new Data();
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

    public void getForestSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wysokość lasu: ");
        int height = scanner.nextInt();
        System.out.println("Podaj szerokość lasu: ");
        int width = scanner.nextInt();

        setSize_x(width);
        setSize_y(height);
    }
    /*public void generateMap() {

    }*/
}
