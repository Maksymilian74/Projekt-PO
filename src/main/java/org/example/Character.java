package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Character extends Object {
    private int id;
    private Map<String, Integer> Basket;

    public Character(int position_x, int position_y, int id, Map<String, Integer> Basket) {
        super(position_x, position_y);
        Basket = new HashMap<>();
        this.id = id;
    }
    public void move() {
        Scanner scanner = new Scanner(System.in);
        String direction;

        do {
            System.out.println("Wybierz kierunek ruchu (lewo, prawo, tył, przód):");
            direction = scanner.nextLine();
        } while (!direction.equalsIgnoreCase("lewo") &&
                !direction.equalsIgnoreCase("prawo") &&
                !direction.equalsIgnoreCase("góra") &&
                !direction.equalsIgnoreCase("dół"));

        switch (direction.toLowerCase()) {
            case "lewo":
                break;
            case "prawo":
                break;
            case "góra":
                break;
            case "dół":
                break;
        }
    }

    public int getId() {
        return id;
    }

    public Map<String,Integer> getBasket() {
        return Basket;
    }
}
