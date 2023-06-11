package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel[][] cells;
    private Forest forest;
    private Character character;
    private JPanel forestPanel;

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
        JPanel controlPanel = new JPanel();
        JLabel sizeLabel = new JLabel("Enter forest size: ");
        JTextField sizeField = new JTextField(5);
        JButton generateButton = new JButton("Generate Forest");
        JButton moveUpButton = new JButton("Move Up");
        JButton moveDownButton = new JButton("Move Down");
        JButton moveLeftButton = new JButton("Move Left");
        JButton moveRightButton = new JButton("Move Right");

        controlPanel.add(sizeLabel);
        controlPanel.add(sizeField);
        controlPanel.add(generateButton);
        controlPanel.add(moveUpButton);
        controlPanel.add(moveDownButton);
        controlPanel.add(moveLeftButton);
        controlPanel.add(moveRightButton);

        controlPanel.setLayout(new GridLayout(0, 1));

        forestPanel = new JPanel();
        forestPanel.setBackground(new Color(135, 237, 147, 255));
        forestPanel.setLayout(new GridLayout(1, 1));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(controlPanel, BorderLayout.EAST);
        mainPanel.add(forestPanel, BorderLayout.CENTER);

        add(mainPanel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(sizeField.getText());
                forest = new Forest(size);
                character = new Character(forest);
                generateForestCells(size);
            }
        });

        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveUp();
                    updateForestDisplay();
                }
            }
        });

        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveDown();
                    updateForestDisplay();
                }
            }
        });

        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveLeft();
                    updateForestDisplay();
                }
            }
        });

        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (character != null) {
                    character.moveRight();
                    updateForestDisplay();
                }
            }
        });
    }

    private Color getColorForCell(String cell) {
        switch (cell) {
            case "☺":
                return new Color(76, 64, 65, 255);
            case "L":
                return new Color(11, 170, 11, 255);
            case "B":
                return Color.YELLOW;
            case "J":
                return Color.RED;
            case "E":
                return Color.YELLOW;
            case "A":
                return Color.MAGENTA;
            case "R":
                return Color.WHITE;
            case "S":
                return Color.GREEN;
            case "D":
                return Color.YELLOW;
            case "I":
                return Color.CYAN;
            case "M":
                return Color.GREEN;
            case "O":
                return Color.YELLOW;
            case "P":
                return Color.YELLOW;
            case "C":
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

    private void generateForestCells(int size) {
        forestPanel.removeAll();
        forestPanel.setLayout(new GridLayout(size, size));
        cells = new JLabel[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new JLabel("", SwingConstants.CENTER);
                cells[i][j].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
                cells[i][j].setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 255)));
                cells[i][j].setText("L");
                forestPanel.add(cells[i][j]);
            }
        }

        updateForestDisplay();
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Ustawienie okna na tryb pełnoekranowy
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}