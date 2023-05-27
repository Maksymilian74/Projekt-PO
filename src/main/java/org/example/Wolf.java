package org.example;

public class Wolf extends Object{
    private int id;
    private boolean IfHungry;

    public Wolf(int position_x, int position_y, int id, boolean IfHungry) {
        super(position_x, position_y);
        this.id = id;
        this. IfHungry = IfHungry;
    }

    public int getId() {
        return id;
    }

    public boolean getIfHungry() {
        return IfHungry;
    }

    public void setIfHungry(boolean IfHungry) {
        this.IfHungry = IfHungry;
    }
}
