package org.example;

public class Trees extends Object{
    private int id;
    private String name;
    private int type;
    public Trees(int id, String name, int type, int position_x, int position_y) {
        super(position_x, position_y);
        this.id = id;
        this.name = name;
        this.type = type;
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


}
