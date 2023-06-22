package org.example;

/*
    Klasa abstrakcyjna Mushroom, dziedziczy po klasie Object
*/

public class Mushroom extends Object{
    private int id;
    private boolean IfCollected;
    private String name;
    public Mushroom(int position_x, int position_y, int id, boolean IfCollected, String name) {
        super(position_x, position_y);
        this.id = id;
        this.IfCollected = IfCollected;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIfCollected() {
        return IfCollected;
    }

    public void setIfCollected() {
        this.IfCollected = IfCollected;
    }
}
