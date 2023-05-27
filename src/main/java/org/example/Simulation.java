package org.example;

public class Simulation {
    private int Round;
    private int CharacterMove;
    private Forest Forest;

    public Simulation(int Round, int CharacterMove, Forest Forest) {
        this.Round = Round;
        this.CharacterMove = CharacterMove;
        Forest = new Forest();
    }

    public int getRound() {
        return Round;
    }

    public int getCharacterMove() {
        return CharacterMove;
    }

    public void setCharacterMove(int CharacterMove) {
        this.CharacterMove = CharacterMove;
    }

    public void runSimulation() {
        System.out.println("Wszystko działa");
    }
}
