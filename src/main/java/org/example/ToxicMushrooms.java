package org.example;

/*
    Klasa ToxicMushrooms inicjalizuje obiekty grzybów trujących, dziedziczy po klasie Mushroom
*/

public class ToxicMushrooms extends Mushroom{
    private String poisoningEffects;
    public ToxicMushrooms(int position_x, int position_y, int id, String name, String poisoningEffects) {
        super(position_x, position_y, id, name);
        this.poisoningEffects = poisoningEffects;
    }

    public String getPoisoningEffects() {
        return poisoningEffects;
    }
}
