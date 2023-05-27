package org.example;

public class ForestFruits extends Object{
    private int id;
    private String name;
    private int type;
    private boolean IfCollected;
    private String eatingEffects;


    public ForestFruits(int position_x, int position_y, int id, String name, int type, boolean IfCollected, String eatingEffects) {
        super(position_x, position_y);
        this.id = id;
        this. type = type;
        this.IfCollected = IfCollected;
        this.eatingEffects = eatingEffects;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean getIfCollected() {
        return IfCollected;
    }

    public void setIfCollected(boolean IfCollected) {
        this.IfCollected = IfCollected;
    }

    public String getName() {
        return name;
    }

    public String getEatingEffects() {
        return eatingEffects;
    }
}
