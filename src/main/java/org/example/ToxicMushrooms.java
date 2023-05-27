package org.example;

public class ToxicMushrooms extends Mushroom{
    private String poisoningEffects;
    public ToxicMushrooms(int position_x, int position_y, int id, boolean IfCollected, String name, String poisoningEffects) {
        super(position_x, position_y, id, IfCollected, name);
        this.poisoningEffects = poisoningEffects;
    }

    public String getPoisoningEffects() {
        return poisoningEffects;
    }
}
