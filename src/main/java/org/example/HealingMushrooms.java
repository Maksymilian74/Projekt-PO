package org.example;

public class HealingMushrooms extends Mushroom{
    private String healingEffects;
    public HealingMushrooms(int position_x, int position_y, int id, boolean IfCollected, String name, String healingEffects) {
        super(position_x, position_y, id, IfCollected, name);
        this.healingEffects = healingEffects;
    }

    public String getHealingEffects() {
        return healingEffects;
    }
}
