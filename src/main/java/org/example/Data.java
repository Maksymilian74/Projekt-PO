package org.example;

import java.util.ArrayList;

/*
    Klasa Data przechowuje listy wszystkich obiektów znajdujących się na mapie
*/

public class Data {
    public static ArrayList<Wolf> ListWolf;
    public static ArrayList<Trees> ListTrees;
    public static ArrayList<Mushroom> ListMushroom;
    public static ArrayList<ForestFruits> ListFruits;

    public Data() {
        ListWolf = new ArrayList<Wolf>();
        ListTrees = new ArrayList<Trees>();
        ListMushroom = new ArrayList<Mushroom>();
        ListFruits = new ArrayList<ForestFruits>();
    }
}
