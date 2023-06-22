package org.example;

/*
    Klasa EatableMushrooms inicjalizuje obiekty grzybów jadalnych, dziedziczy po klasie Mushroom
*/

public class EatableMushrooms extends Mushroom{
    private String eatingEffects;
    public EatableMushrooms(int position_x, int position_y, int id, boolean IfCollected, String name, String eatingEffects) {
        super(position_x, position_y, id, IfCollected, name);
        this.eatingEffects = eatingEffects;
    }

    public String getEatingEffects() {
        return eatingEffects;
    }
}
