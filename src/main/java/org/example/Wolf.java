package org.example;

public class Wolf extends Object{
    private int id;

    public Wolf(int position_x, int position_y, int id) {
        super(position_x, position_y);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
