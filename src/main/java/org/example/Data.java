package org.example;

import java.util.ArrayList;

public class Data {
    public ArrayList<Wolf> ListWolf;
    public ArrayList<Character> ListCharacter;
    public ArrayList<Trees> ListTrees;
    public ArrayList<Mushroom> ListMushroom;
    public ArrayList<ForestFruits> ListFruits;

    public Data(ArrayList<Wolf> ListWolf, ArrayList<Character> ListCharacter, ArrayList<Trees> ListTrees, ArrayList<Mushroom> ListMushroom, ArrayList<ForestFruits> ListFruits) {
        ListWolf = new ArrayList<>();
        ListCharacter = new ArrayList<>();
        ListTrees = new ArrayList<>();
        ListMushroom = new ArrayList<>();
        ListFruits = new ArrayList<>();
    }
}
