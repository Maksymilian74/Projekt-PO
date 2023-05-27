package org.example;

public class Object {
    private int position_x;
    private int position_y;

    public Object(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public int getPosition_x () {
        return position_x;
    }

    public void setPosition_x () {
        this.position_x = position_x;
    }

    public int getPosition_y () {
        return position_y;
    }

    public void setPosition_y () {
        this.position_y = position_y;
    }
}
