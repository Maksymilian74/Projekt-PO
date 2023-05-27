package org.example;

import java.util.HashMap;
import java.util.Map;

public class Character extends Object {
    private int id;
    private Map<String, Integer> Basket;

    public Character(int position_x, int position_y, int id, Map<String, Integer> Basket) {
        super(position_x, position_y);
        Basket = new HashMap<>();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Map<String,Integer> getBasket() {
        return Basket;
    }
}
