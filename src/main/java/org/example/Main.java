package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel[][] cells;
    private Forest forest;
    private Character character;

    public Main() {
        setTitle("Forest Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
        pack();
        setVisible(true);
    }

    private void updateForestDisplay() {
        int size = forest.getSize_x();

        // Aktualizuj wyświetlanie komórek lasu
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String symbol = forest.getCell(i, j);
                Color color = getColorForCell(symbol);
                cells[i][j].setText(symbol);
                cells[i][j].setForeground(color);
            }
        }
    }

    private void initializeUI() {
        // Tworzenie panelu sterowania
        JPanel controlPanel = new JPanel();
        JLabel sizeLabel = new JLabel("Enter forest size: ");
        JTextField sizeField = new JTextField(5);
        JButton generateButton = new JButton("Generate Forest");
        //JButton placeCharacterButton = new JButton("Place Character");
        JButton moveUpButton = new JButton("Move Up");
        JButton moveDownButton = new JButton("Move Down");
        JButton moveLeftButton = new JButton("Move Left");
        JButton moveRightButton = new JButton("Move Right");

        // Dodawanie komponentów do panelu sterowania
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeField);
        controlPanel.add(generateButton);
        //controlPanel.add(placeCharacterButton);
        controlPanel.add(moveUpButton);
        controlPanel.add(moveDownButton);
        controlPanel.add(moveLeftButton);
        controlPanel.add(moveRightButton);

        // Ustalanie menedżera rozkładu dla panelu sterowania
        controlPanel.setLayout(new FlowLayout());

        // Dodawanie panelu sterowania do ramki
        add(controlPanel, BorderLayout.NORTH);

        // Dodawanie słuchacza do przycisku "Generate Forest"
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(sizeField.getText());
                forest = new Forest(size);
                character = new Character(forest);
                generateForestCells(size);
            }
        });

        // Dodawanie słuchacza do przycisku "Place Character"
        /*placeCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null && cells[0][0] != null) {
                    String icon = "☺"; // Uśmiech jako znak tekstowy
                    cells[0][0].setText(icon); // Ustaw uśmiech jako tekst na komórce (0, 0)
                    cells[0][0].setIcon(null); // Usuń ikonę z komórki (0, 0)
                    cells[0][0].setHorizontalAlignment(SwingConstants.CENTER); // Wyśrodkuj tekst
                }
            }
        });*/
        // Dodawanie słuchacza do przycisku "Move Up"
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveUp();
                    updateForestDisplay();
                }
            }
        });

        // Dodawanie słuchacza do przycisku "Move Down"
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveDown();
                    updateForestDisplay();
                }
            }
        });

        // Dodawanie słuchacza do przycisku "Move Left"
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveLeft();
                    updateForestDisplay();
                }
            }
        });

        // Dodawanie słuchacza do przycisku "Move Right"
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveRight();
                    updateForestDisplay();
                }
            }
        });

        // Tworzenie panelu z komórkami lasu
        JPanel forestPanel = new JPanel();
        forestPanel.setBackground(new Color(135, 237, 147, 255)); // Ustawienie ciemnozielonego tła
        forestPanel.setLayout(new GridLayout(1, 1));

        // Dodawanie panelu lasu do ramki
        add(forestPanel, BorderLayout.CENTER);
    }

    private Color getColorForCell(String cell) {
        switch (cell) {
            case "L":
                return new Color(255, 255, 255, 255);
            case "B":
                return Color.YELLOW; // główny bohater
            case "J":
                return Color.RED; // jagody
            case "E":
                return Color.YELLOW; // jeżyny
            case "A":
                return Color.MAGENTA; // maliny
            case "R":
                return Color.WHITE; // brzoza
            case "S":
                return Color.GREEN; // sosna
            case "D":
                return Color.YELLOW; // dąb
            case "I":
                return Color.CYAN; // pieprznik
            case "M":
                return Color.GREEN; // maitake
            case "O":
                return Color.YELLOW; // borowik
            case "P":
                return Color.YELLOW; // podgrzybek
            case "C":
                return Color.RED; // muchomor czerwony
            default:
                return Color.BLACK; // domyślny kolor
        }
    }

    private void generateForestCells(int size) {
        JPanel forestPanel = (JPanel) getContentPane().getComponent(1);
        forestPanel.removeAll();
        forestPanel.setLayout(new GridLayout(size, size));
        cells = new JLabel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cells[i][j].setText("L");
                forestPanel.add(cells[i][j]);
            }
        }

        updateForestDisplay();
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
