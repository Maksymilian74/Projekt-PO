package org.example;

import java.util.Random;

public class Forest {
    private String[][] cells;
    private Data data;

    public Forest(int size) {
        cells = new String[size][size];
        generateForest();
    }

    public int getSize_x() {
        return cells.length;
    }

    public int getSize_y() {
        return cells[0].length;
    }

    public String getCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, String value) {
        cells[x][y] = value;
    }

    public void generateForest() {
        data = new Data();
        cells = new String[getSize_x()][getSize_y()];
        Random random = new Random();
        int wolfId=0;
        for (int x = 0; x < getSize_x(); x++) {
            for (int y = 0; y < getSize_y(); y++) {
                double randomNumber = random.nextDouble();

                if ((x == 0 && y == 0) || (x == getSize_x() - 1) || (y == getSize_y() - 1)) {
                    // Obszar brzegowy lasu
                    if (randomNumber < 0.25 || (randomNumber >= 0.7 && randomNumber < 0.995)) {
                        {
                            cells[x][y] = "L";

                        }
                    } else if ((randomNumber >= 0.25 && randomNumber < 0.4) || (randomNumber >= 0.5 && randomNumber < 0.65)) {
                        if (randomNumber >= 0.25 && randomNumber < 0.3) {
                            cells[x][y] = "I";

                        }
                        else if (randomNumber >= 0.3 && randomNumber < 0.35) {
                            cells[x][y] = "M";

                        }
                        else if (randomNumber >= 0.35 && randomNumber < 0.4) {
                            cells[x][y] = "O";

                        }
                        else if (randomNumber >= 0.5 && randomNumber < 0.55) {
                            cells[x][y] = "P";

                        }
                        else if (randomNumber >= 0.55 && randomNumber < 0.6) {
                            cells[x][y] = "C";

                        }
                        else if (randomNumber >= 0.6 && randomNumber < 0.65) {
                            cells[x][y] = "T";

                        }
                    } else if ((randomNumber >= 0.4 && randomNumber < 0.5) || (randomNumber >= 0.65 && randomNumber < 0.7)) {
                        if (randomNumber >= 0.4 && randomNumber < 0.45) {
                            cells[x][y] = "E";

                        }
                        else if (randomNumber >= 0.45 && randomNumber < 0.5) {
                            cells[x][y] = "J";

                        }
                        else if (randomNumber >= 0.65 && randomNumber < 0.68) {
                            cells[x][y] = "B";

                        }
                        else if (randomNumber >= 0.68 && randomNumber < 0.7) {
                            cells[x][y] = "A";

                        }
                    } else if (randomNumber >= 0.995) {
                        cells[x][y] = "W";
                        Wolf wolf = new Wolf(x,y,wolfId,true);
                        Data.ListWolf.add(wolf);
                        wolfId++;
                    }
                } else {
                    // Obszar niebrzegowy lasu
                    if (randomNumber < 0.25 || (randomNumber >= 0.85 && randomNumber < 0.995)) {
                        {
                            cells[x][y] = "L";

                        }

                    } else if ((randomNumber >= 0.25 && randomNumber < 0.45) || (randomNumber >= 0.55 && randomNumber < 0.65)) {
                        if (randomNumber >= 0.25 && randomNumber < 0.3) {
                            cells[x][y] = "I";

                        }
                        else if (randomNumber >= 0.3 && randomNumber < 0.35) {
                            cells[x][y] = "M";

                        }
                        else if (randomNumber >= 0.35 && randomNumber < 0.4) {
                            cells[x][y] = "O";

                        }
                        else if (randomNumber >= 0.4 && randomNumber < 0.45) {
                            cells[x][y] = "P";

                        }
                        else if (randomNumber >= 0.55 && randomNumber < 0.6) {
                            cells[x][y] = "C";

                        }
                        else if (randomNumber >= 0.6 && randomNumber < 0.65) {
                            cells[x][y] = "T";

                        }
                    } else if ((randomNumber >= 0.45 && randomNumber < 0.5) || (randomNumber >= 0.65 && randomNumber < 0.8)) {
                        if (randomNumber >= 0.45 && randomNumber < 0.5) {
                            cells[x][y] = "E";

                        }
                        else if (randomNumber >= 0.65 && randomNumber < 0.70) {
                            cells[x][y] = "J";

                        }
                        else if (randomNumber >= 0.7 && randomNumber < 0.75) {
                            cells[x][y] = "B";

                        }
                        else if (randomNumber >= 0.75 && randomNumber < 0.8) {
                            cells[x][y] = "A";

                        }
                    } else if ((randomNumber >= 0.5 && randomNumber < 0.55) || (randomNumber >= 0.8 && randomNumber < 0.85)) {
                        if (randomNumber >= 0.5 && randomNumber < 0.54) {
                            cells[x][y] = "D";

                        }
                        else if (randomNumber >= 0.8 && randomNumber < 0.84) {
                            cells[x][y] = "S";

                        }
                        else if ((randomNumber >= 0.54 && randomNumber < 0.55) || (randomNumber >= 0.84 && randomNumber < 0.85)) {
                            cells[x][y] = "R";

                        }
                    } else if (randomNumber >= 0.995) {
                        cells[x][y] = "W";
                        Wolf wolf = new Wolf(x,y,wolfId,true);
                        Data.ListWolf.add(wolf);
                        wolfId++;
                    }
                }
            }
        }
        //System.out.println(Data.ListWolf);
    }
}