package org.example;

public class Trees extends Object{
    private int id;
    private String name;
    private int type;
    private boolean IfCollected;
    public Trees(int id, String name, int type, boolean IfCollected) {
        this.id = id;
        this.name = name;
        this.type = type;
        this. IfCollected = IfCollected;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
